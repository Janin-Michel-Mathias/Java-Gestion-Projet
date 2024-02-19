package ApiRest;

import controller.LevelController;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.NotFoundResponse;
import modeles.Level;

import java.util.List;

public class LevelApiCRUD {

    public static void setupEndpoints(Javalin app) {
        // Endpoint pour récupérer tous les niveaux
        app.get("/level", getAllLevel);

        // Endpoint pour récupérer un niveau par son ID
        app.get("/level/{id}", getLevelById);

        // Endpoint pour créer un nouveau niveau
        app.post("/level", createLevel);

        // Endpoint pour mettre à jour un niveau par son ID
        app.put("/levelupdate/", updateLevel);

        // Endpoint pour supprimer un niveau par son ID
        app.delete("/level/{id}", deleteLevel);
    }

    private static final Handler getAllLevel = ctx -> {
        List<Level> level = LevelController.getAllLevel();
        ctx.json(level);
    };

    private static final Handler getLevelById = ctx -> {
        int levelId = Integer.parseInt(ctx.pathParam("id"));
        Level level = LevelController.getLevelById(levelId);
        if (level == null) {
            throw new NotFoundResponse("Niveau non trouve");
        }
        ctx.json(level);
    };

    private static final Handler createLevel = ctx -> {
        Level newLevel = ctx.bodyAsClass(Level.class);
        // Logique pour créer un nouveau niveau dans la base de données
        LevelController.createLevel(newLevel);
        ctx.status(201).json(newLevel);
    };

    private static final Handler updateLevel = ctx -> {
        Level updatedLevel = ctx.bodyAsClass(Level.class);
        // Logique pour mettre à jour un niveau par son ID dans la base de données
        LevelController.updateLevel(updatedLevel);
        ctx.json(updatedLevel);
    };

    private static final Handler deleteLevel = ctx -> {
        int levelId = Integer.parseInt(ctx.pathParam("id"));
        LevelController.deleteLevel(levelId);
        ctx.status(204);
    };

}
