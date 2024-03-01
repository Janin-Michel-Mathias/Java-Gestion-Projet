package controller;

import modeles.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import GlobalVariables.EnvironmentVariable;

public class TeamController {
    private static final String DATABASE_URL = EnvironmentVariable.getDatabaseUrl();
    static final String STACKS_TABLE = "team";

    public static List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();
        Connection conn = EnvironmentVariable.getConnection();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT t.*, p.name AS project_name, d.name AS developer_name " +
                     "FROM " + STACKS_TABLE + " t " +
                     "JOIN project p ON t.id_project = p.id " +
                     "JOIN developers d ON t.id_developer = d.id")) {

            while (rs.next()) {
                Team team = new Team();
                team.setId_project(rs.getInt("id_project"));
                team.setId_developer(rs.getInt("id_developer"));
                team.setProject_name(rs.getString("project_name"));
                team.setDeveloper_name(rs.getString("developer_name"));
                teams.add(team);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return teams;
    }

    public static List<Team> getTeamByIdProject(int id) {
        List<Team> teams = new ArrayList<>();
        Connection conn = EnvironmentVariable.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement("SELECT t.*, p.name AS project_name, d.name AS developer_name " +
                     "FROM " + STACKS_TABLE + " t " +
                     "JOIN project p ON t.id_project = p.id " +
                     "JOIN developers d ON t.id_developer = d.id " +
                     "WHERE t.id_project = ?")) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Team team = new Team();
                team.setId_project(rs.getInt("id_project"));
                team.setId_developer(rs.getInt("id_developer"));
                team.setProject_name(rs.getString("project_name"));
                team.setDeveloper_name(rs.getString("developer_name"));
                teams.add(team);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return teams;
    }

    public static List<Team> getTeamByNameProject(String name) {
        List<Team> teams = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement stmt = conn.prepareStatement("SELECT t.*, p.name AS project_name, d.name AS developer_name " +
                     "FROM " + STACKS_TABLE + " t " +
                     "JOIN project p ON t.id_project = p.id " +
                     "JOIN developers d ON t.id_developer = d.id " +
                     "WHERE p.name = ?")) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Team team = new Team();
                team.setId_project(rs.getInt("id_project"));
                team.setId_developer(rs.getInt("id_developer"));
                team.setProject_name(rs.getString("project_name"));
                team.setDeveloper_name(rs.getString("developer_name"));
                teams.add(team);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return teams;
    }

    public static Team createTeam(Team newTeam) {
        Connection conn = EnvironmentVariable.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + STACKS_TABLE + " (id_developer, id_project) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, newTeam.getId_developer());
            stmt.setInt(2, newTeam.getId_project());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        newTeam.setId_project(generatedKeys.getInt(1));
                        return newTeam;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Team updateTeam(Team updatedTeam) {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement stmt = conn.prepareStatement("UPDATE " + STACKS_TABLE + " SET id_developer = ? WHERE id_project = ?")) {

            stmt.setInt(1, updatedTeam.getId_developer());
            stmt.setInt(2, updatedTeam.getId_project());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return updatedTeam;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void deleteDevTeam(int devId, String nameProject) {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM " + STACKS_TABLE + " WHERE id_developer = ? AND id_project = ?")) {

            stmt.setInt(1, devId);
            stmt.setInt(2, Objects.requireNonNull(ProjectController.getProjectByName(nameProject)).getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteTeam(int id) {
        Connection conn = EnvironmentVariable.getConnection();
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM " + STACKS_TABLE + " WHERE id_project = ?")) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}