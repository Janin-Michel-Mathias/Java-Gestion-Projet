package ApiRest;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.NotFoundResponse;
import modeles.SkillStacks;
import controller.StackController;

import java.util.List;

public class StackApiCRUD {

    // Méthode principale pour configurer les endpoints de l'API
    public static void setupEndpoints(Javalin app) {
        // Endpoint pour récupérer toutes les compétences
        app.get("/stack", getAllStacks);

        // Endpoint pour récupérer une compétence par son ID
        app.get("/stack/{id}", getStackById);

        // Endpoint pour créer une nouvelle compétence
        app.post("/stack", createStack);

        // Endpoint pour mettre à jour une compétence par son ID
        app.put("/stackupdate", updateStack);

        // Endpoint pour supprimer une compétence par son ID
        app.delete("/stack/{id}", deleteStack);
    }

    // Handler pour récupérer toutes les Stack
    private static final Handler getAllStacks = ctx -> {
        List<SkillStacks> stacks = StackController.getAllStacks();
        ctx.json(stacks);
    };

    // Handler pour récupérer une Stack par son ID
    private static final Handler getStackById = ctx -> {
    };

    // Handler pour créer une nouvelle Stack
    private static final Handler createStack = ctx -> {
    };

    // Handler pour mettre à jour une Stack par son ID
    private static final Handler updateStack = ctx -> {
    };

    // Handler pour supprimer une Stack par son ID
    private static final Handler deleteStack = ctx -> {
    };
}

