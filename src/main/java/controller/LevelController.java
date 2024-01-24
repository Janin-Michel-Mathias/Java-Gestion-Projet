package controller;

import GlobalVariables.EnvironmentVariable;
import modeles.Level;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LevelController {

    private static final String DATABASE_URL = EnvironmentVariable.getDatabaseUrl();
    private static final String LEVEL_TABLE = "level";

    public static List<Level> getAllLevel(){
        List<Level> levels = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + LEVEL_TABLE)) {

            while (resultSet.next()) {
                Level level = mapResultSetToLevel(resultSet);
                levels.add(level);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return levels;
    }

    public static Level getLevelById(int levelId) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + LEVEL_TABLE + " WHERE id = ?")) {

            preparedStatement.setInt(1, levelId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToLevel(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Level createLevel(Level newLevel) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + LEVEL_TABLE + " (id, level) VALUES (?, ?)")) {

            preparedStatement.setInt(1, newLevel.getId());
            preparedStatement.setString(2, newLevel.getLevelName());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return newLevel;
            } else {
                throw new SQLException("Échec de la création du niveau, aucune ligne affectée.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Méthode pour mettre à jour un level par son ID dans la base de données
    public static Level updateLevel(int levelId, Level updatedLevel) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + LEVEL_TABLE + " SET id = ?, level = ? WHERE id = ?")) {

            preparedStatement.setInt(1, updatedLevel.getId());
            preparedStatement.setString(2, updatedLevel.getLevelName());
            preparedStatement.setInt(3, levelId);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                return updatedLevel;
            } else {
                throw new SQLException("Échec de l'update du niveau, aucune ligne affectée.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedLevel;
    }

    // Méthode pour supprimer un développeur par son ID de la base de données
    public static void deleteLevel(int levelId) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + LEVEL_TABLE + " WHERE id = ?")) {

            preparedStatement.setInt(1, levelId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Level mapResultSetToLevel(ResultSet resultSet) throws SQLException {
        Level level = new Level();
        level.setId(resultSet.getInt("id"));
        level.setLevelName(resultSet.getString("level"));
        return level;
    }


}
