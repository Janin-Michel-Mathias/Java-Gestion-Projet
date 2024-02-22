package service;

import GlobalVariables.EnvironmentVariable;

import java.sql.Connection;
import java.sql.SQLException;

import static service.SQLiteConnection.connect;

public class DatabaseManager {
    public static void createDevelopersTable(Connection conn) {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS developers (\n"
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + " name TEXT NOT NULL,\n"
                    + " email TEXT NOT NULL UNIQUE,\n"
                    + " start_date DATE NOT NULL,\n"
                    + " end_date DATE\n"
                    + ");";


            conn.createStatement().execute(sql);
            System.out.println("Table 'developers' créée avec succès.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void createSkillsTable(Connection conn) {
        try {
        String sql = "CREATE TABLE IF NOT EXISTS skills (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " skill_name TEXT NOT NULL\n"
                + ");";

            conn.createStatement().execute(sql);
            System.out.println("Table 'skills' créée avec succès.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void createDeveloper_skillsTable(Connection conn) {
        try {
        String sql = "CREATE TABLE IF NOT EXISTS developer_skills (\n"
                + " developer_id INTEGER,\n"
                + " skill_id INTEGER,\n"
                + " level_id INTEGER,\n"
                + " PRIMARY KEY (developer_id, skill_id),\n"
                + " FOREIGN KEY (developer_id) REFERENCES developers(id),\n"
                + " FOREIGN KEY (skill_id) REFERENCES skills(id)\n"
                + ");";

            conn.createStatement().execute(sql);
            System.out.println("Table 'developer_skills' créée avec succès.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void createlevel_skillsTable(Connection conn) {
        try {
        String sql = "CREATE TABLE IF NOT EXISTS level_skills (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " level TEXT NOT NULL\n"
                + ");";

            conn.createStatement().execute(sql);
            System.out.println("Table 'level_skills' créée avec succès.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createPriorityTable(Connection conn){
        try {
        String sql = "CREATE TABLE IF NOT EXISTS priority (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " type TEXT NOT NULL\n"
                + ");";

            conn.createStatement().execute(sql);
            System.out.println("Table 'priority' créée avec succès.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createProjectTable(Connection conn){
        try {
        String sql = "CREATE TABLE IF NOT EXISTS project (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " name TEXT,\n"
                + " id_priority INTEGER NOT NULL,\n"
                + " description TEXT,\n"
                + " date_start DATE,\n"
                + " date_end DATE,\n"
                + " FOREIGN KEY (id_priority) REFERENCES Priority(id)"
                + ");";

            conn.createStatement().execute(sql);
            System.out.println("Table 'project' créée avec succès.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createTeamTable(Connection conn){
        try {
        String sql = "CREATE TABLE IF NOT EXISTS team (\n"
                + " id_developer INTEGER NOT NULL,\n"
                + " id_project INTEGER NOT NULL,\n"
                + " FOREIGN KEY (id_project) REFERENCES project(id),\n"
                + " FOREIGN KEY (id_developer) REFERENCES developers(id)"
                + ");";

            conn.createStatement().execute(sql);
            System.out.println("Table 'team' créée avec succès.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createStackTable(Connection conn){
        try {
        String sql = "CREATE TABLE IF NOT EXISTS stack (\n"
                + " name_project TEXT PRIMARY KEY,\n"
                + " id_skill INTEGER NOT NULL,\n"
                + " numberDev INTEGER NOT NULL,\n"
                + " FOREIGN KEY (id_skill) REFERENCES skills(id)"
                + ");";

            conn.createStatement().execute(sql);
            System.out.println("Table 'stack' créée avec succès.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
