package controller;

import GlobalVariables.EnvironmentVariable;
import modeles.Priority;
import service.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PriorityController {

    private static final String DATABASE_URL = EnvironmentVariable.getDatabaseUrl();
    private static final String TABLE_NAME = "priority";

    public static List<Priority> getAllPriorities() {
        List<Priority> priorities = new ArrayList<>();
        Connection connection = EnvironmentVariable.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME)) {

            while (resultSet.next()) {
                Priority priority = new Priority();
                priority.setId(resultSet.getInt("id"));
                priority.setType(resultSet.getString("type"));
                priorities.add(priority);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return priorities;
    }

    public static Priority getPriorityById(int id) {
        Connection connection = EnvironmentVariable.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Priority priority = new Priority();
                priority.setId(resultSet.getInt("id"));
                priority.setType(resultSet.getString("type"));
                return priority;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static Priority getPriorityByType(String type) {
        Connection connection = EnvironmentVariable.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE type = ?")) {

            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Priority priority = new Priority();
                priority.setId(resultSet.getInt("id"));
                priority.setType(resultSet.getString("type"));
                return priority;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static void createPriority(Priority priority) {
        Connection connection = EnvironmentVariable.getConnection();
        try {
            String sql = "INSERT INTO " + TABLE_NAME + " (id, type) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, priority.getId());
            preparedStatement.setString(2, priority.getType());
            preparedStatement.executeUpdate();
            System.out.println("Priority ajoutée avec succès.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updatePriority(Priority priority) {
        Connection connection = EnvironmentVariable.getConnection();
        try {
            String sql = "UPDATE " + TABLE_NAME + " SET type = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, priority.getType());
            preparedStatement.setInt(2, priority.getId());
            preparedStatement.executeUpdate();
            System.out.println("Priority mise à jour avec succès.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deletePriority(int id) {
        Connection connection = EnvironmentVariable.getConnection();
        try {
            String sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Priority supprimée avec succès.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
