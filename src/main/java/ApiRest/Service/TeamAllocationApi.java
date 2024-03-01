package ApiRest.Service;

import controller.DevelopersController;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import modeles.Developer;
import modeles.FullProject;
import service.GetFullProject;

import java.util.List;

import service.TeamAllocationService;

public class TeamAllocationApi {

    // Méthode principale pour configurer les endpoints de l'API
    public static void setupEndpoints(Javalin app) {
        // Endpoint pour récupérer toutes les projects avec toutes les informations
        app.get("/allocation/team/{id}", getAvaibleDevelopersForProject);

    }

    // Handler pour récupérer toutes les developer libre pour les dates d'un projet
    private static Handler getAvaibleDevelopersForProject = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        List<Developer> developers = TeamAllocationService.allocateTeamForProject(id);

        ctx.json(developers);
    };

}

