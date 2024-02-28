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
        // Endpoint pour récupérer toutes les stack
        app.get("/stack", getAllStacks);

        // Endpoint pour récupérer une stack par son nom de projet
        app.post("/stack/name", getStackByNameProject);

        // Endpoint pour créer une nouvelle stack
        app.post("/stack", createStack);

        // Endpoint pour mettre à jour une stack par son ID
        app.put("/stackupdate", updateStack);

        // Endpoint pour supprimer une compétence de la Stack par son ID et le nom du projet
        app.delete("/stack/skill", deleteSkillStack);

        // Endpoint pour supprimer une Stack par son nom de projet
        app.delete("/stack", deleteStack);
    }

    // Handler pour récupérer toutes les Stack
    private static final Handler getAllStacks = ctx -> {
        List<SkillStacks> stacks = StackController.getAllStacks();
        ctx.json(stacks);
    };

    // Handler pour récupérer une Stack par son name_project
    private static final Handler getStackByNameProject = ctx -> {
        String nameProject = ctx.body();
        List<SkillStacks> stacks = StackController.getStackByNameProject(nameProject);
        ctx.json(stacks);
    };

    // Handler pour créer une nouvelle Stack
    private static final Handler createStack = ctx -> {
        SkillStacks newStack = ctx.bodyAsClass(SkillStacks.class);
        // Logique pour créer une nouvelle Stack dans la base de données
        newStack = StackController.createStack(newStack);
        assert newStack != null;
        ctx.status(201).json(newStack);
    };

    // Handler pour mettre à jour une Stack par son nom et l'ID de la compétence
    private static final Handler updateStack = ctx -> {
        SkillStacks updatedStack = ctx.bodyAsClass(SkillStacks.class);
        // Logique pour mettre à jour une Stack par son ID dans la base de données
        updatedStack = StackController.updateStack(updatedStack);
        assert updatedStack != null;
        ctx.json(updatedStack);
    };

    // Handler pour supprimer une compétence de la Stack par son ID et le nom du projet
    private static final Handler deleteSkillStack = ctx -> {
        int skillId = Integer.parseInt(ctx.body());
        String nameProject = ctx.body();
        // Logique pour supprimer une compétence de la Stack par son ID et le nom du projet de la base de données
        StackController.deleteSkillStack(skillId, nameProject);
        ctx.status(204).result("Competence " + skillId + " supprimee de la Stack " + nameProject);
    };

    // Handler pour supprimer une Stack par le nom du projet
    private static final Handler deleteStack = ctx -> {
        String nameProject = ctx.body();
        // Logique pour supprimer une Stack par le nom du projet de la base de données
        StackController.deleteStack(nameProject);
        ctx.status(204).result("Stack " + nameProject + " supprimee");
    };
}

