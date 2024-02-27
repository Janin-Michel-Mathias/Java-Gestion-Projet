package controller;

import GlobalVariables.EnvironmentVariable;
import modeles.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProjectController {

    private static final String DATABASE_URL = EnvironmentVariable.getDatabaseUrl();
    private static final String TABLE_NAME = "project";

    public static List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String query = "SELECT * FROM " + TABLE_NAME;
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Project project = new Project();
                        project.setId(resultSet.getInt("id"));
                        project.setName(resultSet.getString("name"));
                        project.setPriorityId(resultSet.getInt("id_priority"));
                        project.setPriorityType(Objects.requireNonNull(PriorityController.getPriorityById(project.getPriorityId())).getType());
                        project.setDescription(resultSet.getString("description"));
                        project.setStartDate(resultSet.getDate("date_start"));
                        project.setEndDate(resultSet.getDate("date_end"));

                        projects.add(project);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public static Project getProjectById(int id) {
        Project project = null;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        project = new Project();
                        project.setId(resultSet.getInt("id"));
                        project.setName(resultSet.getString("name"));
                        project.setPriorityId(resultSet.getInt("id_priority"));
                        project.setPriorityType(Objects.requireNonNull(PriorityController.getPriorityById(project.getPriorityId())).getType());
                        project.setDescription(resultSet.getString("description"));
                        project.setStartDate(resultSet.getDate("date_start"));
                        project.setEndDate(resultSet.getDate("date_end"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    public static Project getProjectByName(String name) {
        Project project = null;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        project = new Project();
                        project.setId(resultSet.getInt("id"));
                        project.setName(resultSet.getString("name"));
                        project.setPriorityId(resultSet.getInt("id_priority"));
                        project.setPriorityType(Objects.requireNonNull(PriorityController.getPriorityById(project.getPriorityId())).getType());
                        project.setDescription(resultSet.getString("description"));
                        project.setStartDate(resultSet.getDate("date_start"));
                        project.setEndDate(resultSet.getDate("date_end"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }


    public static Project createProject(Project project) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String query = "INSERT INTO " + TABLE_NAME + " (name, id_priority, description, date_start, date_end) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, project.getName());
                preparedStatement.setInt(2, Objects.requireNonNull(PriorityController.getPriorityByType(project.getPriorityType())).getId());
                preparedStatement.setString(3, project.getDescription());
                preparedStatement.setDate(4, new Date(project.getStartDate().getTime()));
                preparedStatement.setDate(5, new Date(project.getEndDate().getTime()));

                preparedStatement.executeUpdate();

                return getProjectByName(project.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    public static Project updateProject(Project project) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String query = "UPDATE " + TABLE_NAME + " SET name = ?, id_priority = ?, description = ?, date_start = ?, date_end = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, project.getName());
                preparedStatement.setInt(2, Objects.requireNonNull(PriorityController.getPriorityByType(project.getPriorityType())).getId());
                preparedStatement.setString(3, project.getDescription());
                preparedStatement.setDate(4, new Date(project.getStartDate().getTime()));
                preparedStatement.setDate(5, new Date(project.getEndDate().getTime()));
                preparedStatement.setInt(6, project.getId());

                preparedStatement.executeUpdate();

                return getProjectById(project.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    public static void deleteProject(int id) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String query = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}