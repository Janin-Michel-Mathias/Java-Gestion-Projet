package ApiRest;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.NotFoundResponse;
import modeles.Priority;
import controller.PriorityController;

import java.util.List;

public class PriorityApiCRUD {

    // Méthode principale pour configurer les endpoints de l'API
    public static void setupEndpoints(Javalin app) {
        // Endpoint pour récupérer toutes les compétences
        app.get("/priority", getAllPriority);

        // Endpoint pour récupérer une compétence par son ID
        app.get("/priority/{id}", getPriorityById);

        // Endpoint pour créer une nouvelle compétence
        app.post("/priority", createPriority);

        // Endpoint pour mettre à jour une compétence par son ID
        app.put("/priorityupdate", updatePriority);

        // Endpoint pour supprimer une compétence par son ID
        app.delete("/priority/{id}", deletePriority);
    }

    // Handler pour récupérer toutes les compétences
    private static final Handler getAllPriority = ctx -> {
        List<Priority> priorities = PriorityController.getAllPriorities();
        ctx.json(priorities);
    };

    // Handler pour récupérer une compétence par son ID
    private static final Handler getPriorityById = ctx -> {
        int priorityId = Integer.parseInt(ctx.pathParam("id"));
        Priority priority = PriorityController.getPriorityById(priorityId);
        if (priority == null) {
            throw new NotFoundResponse("Prioritee non trouvee");
        }
        ctx.json(priority);
    };

    // Handler pour créer une nouvelle compétence
    private static final Handler createPriority = ctx -> {
        Priority newPriority = ctx.bodyAsClass(Priority.class);
        // Logique pour créer une nouvelle compétence dans la base de données
        PriorityController.createPriority(newPriority);
        ctx.status(201).json(newPriority);
    };

    // Handler pour mettre à jour une compétence par son ID
    private static final Handler updatePriority = ctx -> {
        Priority updatedPriority = ctx.bodyAsClass(Priority.class);
        // Logique pour mettre à jour une compétence par son ID dans la base de données
        PriorityController.updatePriority(updatedPriority);
        ctx.json(updatedPriority);
    };

    // Handler pour supprimer une compétence par son ID
    private static final Handler deletePriority = ctx -> {
        PriorityController.deletePriority(Integer.parseInt(ctx.pathParam("id")));
        ctx.status(204).result("Prioritee supprimee avec succes");
    };
}
