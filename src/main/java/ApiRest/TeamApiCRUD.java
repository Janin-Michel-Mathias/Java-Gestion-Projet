package ApiRest;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.NotFoundResponse;

import controller.TeamController;
import modeles.Team;

import java.util.List;

public class TeamApiCRUD {

    // Méthode principale pour configurer les endpoints de l'API
    public static void setupEndpoints(Javalin app) {
        // Endpoint pour récupérer toutes les teams
        app.get("/team", getAllTeams);

        // Endpoint pour récupérer une team par son project_id
        app.post("/team/id", getTeamByIdProject);

        // Endpoint pour récupérer une team par son project_name
        app.post("/team/name", getTeamByNameProject);

        // Endpoint pour créer une nouvelle team
        app.post("/team", createTeam);

        // Endpoint pour mettre à jour une team par son project_id
        app.put("/teamupdate", updateTeam);

        // Endpoint pour supprimer un dev d'une team avec son id et le nom du projet
        app.delete("/team/skill", deleteDevTeam);

        // Endpoint pour supprimer une team par le id du projet
        app.delete("/team", deleteTeam);
    }

    // Handler pour récupérer toutes les teams
    private static Handler getAllTeams = ctx -> {
        List<Team> teams = TeamController.getAllTeams();
        ctx.json(teams);
    };

    // Handler pour récupérer une team par son project_id
    private static Handler getTeamByIdProject = ctx -> {
        int id = Integer.parseInt(ctx.body());
        List<Team> teams = TeamController.getTeamByIdProject(id);
        ctx.json(teams);
    };

    // Handler pour récupérer une team par son project_name
    private static Handler getTeamByNameProject = ctx -> {
        String name = ctx.body();
        List<Team> teams = TeamController.getTeamByNameProject(name);
        ctx.json(teams);
    };

    // Handler pour créer une nouvelle team
    private static Handler createTeam = ctx -> {
        Team newTeam = ctx.bodyAsClass(Team.class);
        // Logique pour créer une nouvelle team dans la base de données
        newTeam = TeamController.createTeam(newTeam);
        assert newTeam != null;
        ctx.status(201).json(newTeam);
    };

    // Handler pour mettre à jour une team par son project_id
    private static Handler updateTeam = ctx -> {
        Team updatedTeam = ctx.bodyAsClass(Team.class);
        // Logique pour mettre à jour une team par son project_id dans la base de données
        updatedTeam = TeamController.updateTeam(updatedTeam);
        assert updatedTeam != null;
        ctx.json(updatedTeam);
    };

    // Handler pour supprimer un dev d'une team avec son id et le nom du projet
    private static Handler deleteDevTeam = ctx -> {
        int devId = Integer.parseInt(ctx.body());
        String nameProject = ctx.body();
        // Logique pour supprimer un dev d'une team avec son id et le nom du projet de la base de données
        TeamController.deleteDevTeam(devId, nameProject);
        ctx.status(204).result("Dev " + devId + " removed from the team " + nameProject);
    };

    // Handler pour supprimer une team par le id du projet
    private static Handler deleteTeam = ctx -> {
        int id = Integer.parseInt(ctx.body());
        // Logique pour supprimer une team par le id du projet de la base de données
        TeamController.deleteTeam(id);
        ctx.status(204).result("Team removed from project " + id);
    };


}

