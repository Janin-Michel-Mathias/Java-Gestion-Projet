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


    }

    // Handler pour récupérer toutes les projects avec toutes les informations
    private static Handler getAllFullProjects = ctx -> {
        List<FullProject> fullProjects = GetFullProject.getAllFullProjects();
        ctx.json(fullProjects);
    };


}

