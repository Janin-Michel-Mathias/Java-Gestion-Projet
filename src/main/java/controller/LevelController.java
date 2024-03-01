package controller;

import GlobalVariables.EnvironmentVariable;
import modeles.Level;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LevelController {

    private static final String DATABASE_URL = EnvironmentVariable.getDatabaseUrl();
    private static final String LEVEL_TABLE = "level_skills";

    public static List<Level> getAllLevel(){
        List<Level> levels = new ArrayList<>();
        Connection connection = EnvironmentVariable.getConnection();
        try (Statement statement = connection.createStatement();
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
        Connection connection = EnvironmentVariable.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + LEVEL_TABLE + " WHERE id = ?")) {

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
        Connection connection = EnvironmentVariable.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + LEVEL_TABLE + " (id, level) VALUES (?, ?)")) {

            preparedStatement.setInt(1, newLevel.getId());
            preparedStatement.setString(2, newLevel.getLevel());

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
    public static Level updateLevel(Level updatedLevel) {
        Connection connection = EnvironmentVariable.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + LEVEL_TABLE + " SET level = ? WHERE id = ?")) {

            preparedStatement.setString(1, updatedLevel.getLevel());
            preparedStatement.setInt(2, updatedLevel.getId());

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
        Connection connection = EnvironmentVariable.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + LEVEL_TABLE + " WHERE id = ?")) {

            preparedStatement.setInt(1, levelId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Level mapResultSetToLevel(ResultSet resultSet) throws SQLException {
        Level level = new Level();
        level.setId(resultSet.getInt("id"));
        level.setLevel(resultSet.getString("level"));
        return level;
    }


}
