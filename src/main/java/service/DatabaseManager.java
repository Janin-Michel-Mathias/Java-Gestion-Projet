package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static service.SQLiteConnection.connect;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:src/main/resources/bdd_SQLlite.db";

    public static void createDevelopersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS developers (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " name TEXT NOT NULL,\n"
                + " email TEXT NOT NULL UNIQUE\n"
                + ");";

        try (Connection conn = connect()) {
            conn.createStatement().execute(sql);
            System.out.println("Table 'developers' créée avec succès.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void createSkillsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS skills (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " skill_name TEXT NOT NULL,\n"
                + " level TEXT NOT NULL\n"
                + ");";

        try (Connection conn = connect()) {
            conn.createStatement().execute(sql);
            System.out.println("Table 'skills' créée avec succès.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void createDeveloper_skillsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS developer_skills (\n"
                + " developer_id INTEGER,\n"
                + " skill_id INTEGER,\n"
                + " PRIMARY KEY (developer_id, skill_id),\n"
                + " FOREIGN KEY (developer_id) REFERENCES developers(id),\n"
                + " FOREIGN KEY (skill_id) REFERENCES skills(id)\n"
                + ");";

        try (Connection conn = connect()) {
            conn.createStatement().execute(sql);
            System.out.println("Table 'developer_skills' créée avec succès.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
