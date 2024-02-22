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

        // Endpoint pour récupérer un développeur par son email
        app.post("/developers/email", getDeveloperByEmail);

        // Endpoint pour créer un nouveau développeur
        app.post("/developers", createDeveloper);

        // Endpoint pour virer un développeur
        app.post("/developers/fire", fireDeveloper);

        // Endpoint pour mettre à jour un développeur par son ID
        app.put("/developers/", updateDeveloper);

        // Endpoint pour supprimer un développeur par son ID
        app.delete("/developers/", deleteDeveloper);

        // Endpoint pour ajouter une compétence à un développeur
        app.post("/developers/skills", addSkillToDeveloper);

        // Endpoint pour modifier l'expérience d'un développeur sur un skill
        app.put("/developers/skills", updateDeveloperSkill);

        // Endpoint pour récupérer supprimer un skill d'un développeur
        app.delete("/developers/skills", deleteDeveloperSkill);
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

    private static final Handler getDeveloperByEmail = ctx -> {
        try {
            Developer developer = ctx.bodyAsClass(Developer.class);
            Developer developerByEmail = DevelopersController.getDeveloperByEmail(developer.getEmail());
            if (developerByEmail == null) {
                throw new NotFoundResponse("Developpeur non trouve");
            }
            ctx.json(developerByEmail);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Invalid JSON format or other error");
        }
    };

    // Handler pour créer un nouveau développeur
    private static final Handler createDeveloper = ctx -> {
        try {
            Developer newDeveloper = ctx.bodyAsClass(Developer.class);

            newDeveloper = DevelopersController.createDeveloper(newDeveloper);

            if (newDeveloper == null) {
                throw new NotFoundResponse("Developpeur non creer");
            }
            ctx.status(201).json(newDeveloper);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Invalid JSON format or other error");
        }
    };

    // Handler pour virer un développeur
    private static final Handler fireDeveloper = ctx -> {
        try {
            Developer developer = ctx.bodyAsClass(Developer.class);
            developer = DevelopersController.fireDeveloper(developer);
            if (developer == null) {
                throw new NotFoundResponse("Developpeur non creer");
            }
            ctx.status(201).json(developer);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Invalid JSON format or other error");
        }
    };

    // Handler pour mettre à jour un développeur par son ID
    private static final Handler updateDeveloper = ctx -> {
        Developer updatedDeveloper = ctx.bodyAsClass(Developer.class);
        // Logique pour mettre à jour un développeur par son ID dans la base de données
        updatedDeveloper = DevelopersController.updateDeveloper(updatedDeveloper);
        ctx.json(updatedDeveloper);
    };

    // Handler pour supprimer un développeur par son ID
    private static final Handler deleteDeveloper = ctx -> {
        try {
            Developer deleteDeveloper = ctx.bodyAsClass(Developer.class);
            // Logique pour supprimer un développeur par son ID de la base de données
            DevelopersController.deleteDeveloper(deleteDeveloper);
            //retourner un message de confirmation
            ctx.status(201).result("Developpeur supprime avec succes");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Developpeur non trouve");
        }
    };

    // Handler pour ajouter une compétence à un développeur
    private static final Handler addSkillToDeveloper = ctx -> {
        try {
            Developer developer = ctx.bodyAsClass(Developer.class);
            // Logique pour ajouter une compétence à un développeur dans la base de données
            developer = DevelopersController.addSkillToDeveloper(developer);
            if (developer == null) {
                throw new NotFoundResponse("Erreur lors de l'ajout");
            }
            ctx.status(201).json(developer);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Competence non ajoute");
        }
    };

    // Handler pour modifier l'expérience d'un développeur sur un skill
    private static final Handler updateDeveloperSkill = ctx -> {
        try {
            Developer developer = ctx.bodyAsClass(Developer.class);
            // Logique pour modifier l'expérience d'un développeur sur un skill dans la base de données
            developer = DevelopersController.updateDeveloperSkill(developer);
            if (developer == null) {
                throw new NotFoundResponse("Erreur lors de la modification");
            }
            ctx.status(201).json(developer);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Erreur lors de la modification");
        }
    };

    // Handler pour supprimer un skill d'un développeur
    private static final Handler deleteDeveloperSkill = ctx -> {
        try {
            Developer developer = ctx.bodyAsClass(Developer.class);
            // Logique pour supprimer un skill d'un développeur dans la base de données
            developer = DevelopersController.deleteDeveloperSkill(developer);
            if (developer == null) {
                throw new NotFoundResponse("Erreur lors de la suppression");
            }
            ctx.status(201).json(developer);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Erreur lors de la suppression");
        }
    };
}
