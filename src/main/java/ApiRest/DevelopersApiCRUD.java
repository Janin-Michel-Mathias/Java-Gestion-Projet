package ApiRest;

import controller.DevelopersController;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.NotFoundResponse;
import modeles.Developer;

import java.util.List;

public class DevelopersApiCRUD {

    public static void setupEndpoints(Javalin app) {
        // Endpoint pour récupérer tous les développeurs
        app.get("/developers", getAllDevelopers);

        // Endpoint pour récupérer un développeur par son ID
        app.get("/developers/{id}", getDeveloperById);

        // Endpoint pour créer un nouveau développeur
        app.post("/developers", createDeveloper);

        // Endpoint pour mettre à jour un développeur par son ID
        app.put("/developers/{id}", updateDeveloper);

        // Endpoint pour supprimer un développeur par son ID
        app.delete("/developers/{id}", deleteDeveloper);
    }

    // Handler pour récupérer tous les développeurs
    private static final Handler getAllDevelopers = ctx -> {
        List<Developer> developers = DevelopersController.getAllDevelopers();
        ctx.json(developers);
    };

    // Handler pour récupérer un développeur par son ID
    private static final Handler getDeveloperById = ctx -> {
        int developerId = Integer.parseInt(ctx.pathParam("id"));
        Developer developer = DevelopersController.getDeveloperById(developerId);
        if (developer == null) {
            throw new NotFoundResponse("Developpeur non trouve");
        }
        ctx.json(developer);
    };

    // Handler pour créer un nouveau développeur
    private static final Handler createDeveloper = ctx -> {
        try {
            Developer newDeveloper = ctx.bodyAsClass(Developer.class);

            DevelopersController.createDeveloper(newDeveloper);

            ctx.status(201).json(newDeveloper);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Invalid JSON format or other error");
        }
    };

    // Handler pour mettre à jour un développeur par son ID
    private static final Handler updateDeveloper = ctx -> {
        int developerId = Integer.parseInt(ctx.pathParam("id"));
        Developer updatedDeveloper = ctx.bodyAsClass(Developer.class);
        // Logique pour mettre à jour un développeur par son ID dans la base de données
        DevelopersController.updateDeveloper(developerId, updatedDeveloper);
        ctx.json(updatedDeveloper);
    };

    // Handler pour supprimer un développeur par son ID
    private static final Handler deleteDeveloper = ctx -> {
        int developerId = Integer.parseInt(ctx.pathParam("id"));
        // Logique pour supprimer un développeur par son ID de la base de données
        DevelopersController.deleteDeveloper(developerId);
        ctx.status(204);
    };
}
