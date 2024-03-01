package project.main;

import GlobalVariables.EnvironmentVariable;
import io.javalin.Javalin;
import service.SQLiteConnection;
import ApiRest.ApiRest;
import ApiRest.DevelopersApiCRUD;
import ApiRest.SkillsApiCRUD;
import ApiRest.LevelApiCRUD;
import ApiRest.PriorityApiCRUD;
import ApiRest.ProjectApiCRUD;
import ApiRest.StackApiCRUD;
import ApiRest.TeamApiCRUD;
import ApiRest.Service.ServiceGetFullProjectApiCRUD;
import service.DatabaseManager;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        Connection conn = SQLiteConnection.connect();
        DatabaseManager.createDevelopersTable(conn);
        DatabaseManager.createSkillsTable(conn);
        DatabaseManager.createDeveloper_skillsTable(conn);
        DatabaseManager.createlevel_skillsTable(conn);
        DatabaseManager.createPriorityTable(conn);
        DatabaseManager.createProjectTable(conn);
        DatabaseManager.createStackTable(conn);
        DatabaseManager.createTeamTable(conn);

        try {
            EnvironmentVariable.setConnection(DriverManager.getConnection(EnvironmentVariable.getDatabaseUrl()));
        } catch (Exception e) {
            e.printStackTrace();
        }



        Javalin app = Javalin.create().start(8343);
        ApiRest apiRest = new ApiRest(app);
        DevelopersApiCRUD.setupEndpoints(app);
        SkillsApiCRUD.setupEndpoints(app);
        LevelApiCRUD.setupEndpoints(app);
        PriorityApiCRUD.setupEndpoints(app);
        ProjectApiCRUD.setupEndpoints(app);
        StackApiCRUD.setupEndpoints(app);
        TeamApiCRUD.setupEndpoints(app);
        ServiceGetFullProjectApiCRUD.setupEndpoints(app);
        apiRest.setupEndpoints();
    }
}