package project.main;

import io.javalin.Javalin;
import service.SQLiteConnection;
import ApiRest.ApiRest;
import ApiRest.DevelopersApiCRUD;
import ApiRest.SkillsApiCRUD;
import service.DatabaseManager;

public class Main {
    public static void main(String[] args) {
        SQLiteConnection.connect();
        DatabaseManager.createDevelopersTable();
        DatabaseManager.createSkillsTable();
        DatabaseManager.createDeveloper_skillsTable();


        Javalin app = Javalin.create().start(8343);
        ApiRest apiRest = new ApiRest(app);
        DevelopersApiCRUD.setupEndpoints(app);
        SkillsApiCRUD.setupEndpoints(app);
        apiRest.setupEndpoints();
    }
}