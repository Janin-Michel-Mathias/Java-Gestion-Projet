package ApiRest;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.NotFoundResponse;
import modeles.Skill;
import controller.SkillsController;

import java.util.List;

public class SkillsApiCRUD {

    // Méthode principale pour configurer les endpoints de l'API
    public static void setupEndpoints(Javalin app) {
        // Endpoint pour récupérer toutes les compétences
        app.get("/skills", getAllSkills);

        // Endpoint pour récupérer une compétence par son ID
        app.get("/skills/:id", getSkillById);

        // Endpoint pour créer une nouvelle compétence
        app.post("/skills", createSkill);

        // Endpoint pour mettre à jour une compétence par son ID
        app.put("/skills/:id", updateSkill);

        // Endpoint pour supprimer une compétence par son ID
        app.delete("/skills/:id", deleteSkill);
    }

    // Handler pour récupérer toutes les compétences
    private static final Handler getAllSkills = ctx -> {
        List<Skill> skills = SkillsController.getAllSkills();
        ctx.json(skills);
    };

    // Handler pour récupérer une compétence par son ID
    private static final Handler getSkillById = ctx -> {
        int skillId = Integer.parseInt(ctx.pathParam("id"));
        Skill skill = SkillsController.getSkillById(skillId);
        if (skill == null) {
            throw new NotFoundResponse("Competence non trouvee");
        }
        ctx.json(skill);
    };

    // Handler pour créer une nouvelle compétence
    private static final Handler createSkill = ctx -> {
        Skill newSkill = ctx.bodyAsClass(Skill.class);
        // Logique pour créer une nouvelle compétence dans la base de données
        SkillsController.createSkill(newSkill);
        ctx.status(201).json(newSkill);
    };

    // Handler pour mettre à jour une compétence par son ID
    private static final Handler updateSkill = ctx -> {
        int skillId = Integer.parseInt(ctx.pathParam("id"));
        Skill updatedSkill = ctx.bodyAsClass(Skill.class);
        // Logique pour mettre à jour une compétence par son ID dans la base de données
        SkillsController.updateSkill(skillId, updatedSkill);
        ctx.json(updatedSkill);
    };

    // Handler pour supprimer une compétence par son ID
    private static final Handler deleteSkill = ctx -> {
        int skillId = Integer.parseInt(ctx.pathParam("id"));
        // Logique pour supprimer une compétence par son ID de la base de données
        SkillsController.deleteSkill(skillId);
        ctx.status(204);
    };
}
