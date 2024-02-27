package controller;

import GlobalVariables.EnvironmentVariable;
import modeles.Project;
import service.DatabaseManager;

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


}