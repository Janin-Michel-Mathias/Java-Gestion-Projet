package ApiRest;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.NotFoundResponse;

import modeles.FullProject;
import service.GetFullProject;

import java.util.List;

public class ServiceGetFullProjectApiCRUD {

    // Méthode principale pour configurer les endpoints de l'API
    public static void setupEndpoints(Javalin app) {
        // Endpoint pour récupérer toutes les projects avec toutes les informations
        app.get("/fullprojects", getAllFullProjects);

        // Endpoint pour récupérer un projet avec toutes les informations par son ID
        app.get("/fullprojects/id", getFullProjectById);

        // Endpoint pour récupérer un projet avec toutes les informations par son nom
        app.get("/fullprojects/name", getFullProjectByName);

    }

    // Handler pour récupérer toutes les projects avec toutes les informations
    private static Handler getAllFullProjects = ctx -> {
        List<FullProject> fullProjects = GetFullProject.getAllFullProjects();
        ctx.json(fullProjects);
    };

    // Handler pour récupérer un projet avec toutes les informations par son ID
    private static Handler getFullProjectById = ctx -> {
        int id = Integer.parseInt(ctx.body());
        FullProject fullProject = GetFullProject.getFullProjectById(id);
        ctx.json(fullProject);
    };

    // Handler pour récupérer un projet avec toutes les informations par son nom
    private static Handler getFullProjectByName = ctx -> {
        String name = ctx.body();
        FullProject fullProject = GetFullProject.getFullProjectByName(name);
        ctx.json(fullProject);
    };


}

