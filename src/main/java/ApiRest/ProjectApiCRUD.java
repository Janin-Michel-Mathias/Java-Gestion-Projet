package ApiRest;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.NotFoundResponse;
import modeles.Project;
import controller.ProjectController;

import java.util.List;

public class ProjectApiCRUD {

    // Méthode principale pour configurer les endpoints de l'API
    public static void setupEndpoints(Javalin app) {
        // Endpoint pour récupérer tous les Projects
        app.get("/project", getAllProject);

        // Endpoint pour récupérer un Project par son ID
        app.get("/project/{id}", getProjectById);

        // Endpoint pour créer un nouveau Project
        app.post("/project", createProject);

        // Endpoint pour mettre à jour un Project par son ID
        app.put("/projectupdate", updateProject);

        // Endpoint pour supprimer un Project par son ID
        app.delete("/project/{id}", deleteProject);
    }

    // Handler pour récupérer tous les Projects
    private static final Handler getAllProject = ctx -> {
        List<Project> projects = ProjectController.getAllProjects();
        ctx.json(projects);
    };

    // Handler pour récupérer un Project par son ID
    private static final Handler getProjectById = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Project project = ProjectController.getProjectById(id);
        if (project == null) {
            throw new NotFoundResponse("Project non trouve");
        } else {
            ctx.json(project);
        }
    };

    // Handler pour créer un nouveau Project
    private static final Handler createProject = ctx -> {
        Project project = ctx.bodyAsClass(Project.class);
        project = ProjectController.createProject(project);
        ctx.status(201).json(project);
    };

    // Handler pour mettre à jour un Project par son ID
    private static final Handler updateProject = ctx -> {
        Project project = ctx.bodyAsClass(Project.class);
        project = ProjectController.updateProject(project);
        ctx.status(204).json(project);
    };

    // Handler pour supprimer un Project par son ID
    private static final Handler deleteProject = ctx -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        ProjectController.deleteProject(id);
        ctx.status(204).result("Project supprime avec succes");
    };
}
