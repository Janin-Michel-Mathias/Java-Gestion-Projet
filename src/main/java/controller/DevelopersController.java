package controller;

import GlobalVariables.EnvironmentVariable;
import modeles.Developer;
import modeles.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static controller.SkillsController.SKILLS_TABLE;
import static controller.SkillsController.mapResultSetToSkill;

public class DevelopersController {
    private static final String DATABASE_URL = EnvironmentVariable.getDatabaseUrl();
    private static final String DEVELOPERS_TABLE = "developers";
    private static final String DEVELOPERS_SKILLS_TABLE = "developers_skills";

    // Méthode pour récupérer tous les développeurs depuis la base de données
    public static List<Developer> getAllDevelopers() {
        List<Developer> developers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + DEVELOPERS_TABLE)) {

            while (resultSet.next()) {
                Developer developer = mapResultSetToDeveloper(resultSet);
                developers.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developers;
    }

    // Méthode pour récupérer un développeur par son ID depuis la base de données
    public static Developer getDeveloperById(int developerId) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + DEVELOPERS_TABLE + " WHERE id = ?")) {

            preparedStatement.setInt(1, developerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToDeveloper(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Méthode pour créer un nouveau développeur dans la base de données
    public static Developer createDeveloper(Developer newDeveloper) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + DEVELOPERS_TABLE + " (name, email) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, newDeveloper.getName());
            preparedStatement.setString(2, newDeveloper.getEmail());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int developerId = generatedKeys.getInt(1);
                    newDeveloper.setId(developerId);
                    // Insertion des compétences du développeur
                    insertDeveloperSkills(connection, newDeveloper);
                    return newDeveloper;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Méthode pour mettre à jour un développeur par son ID dans la base de données
    public static Developer updateDeveloper(int developerId, Developer updatedDeveloper) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + DEVELOPERS_TABLE + " SET name = ?, email = ? WHERE id = ?")) {

            preparedStatement.setString(1, updatedDeveloper.getName());
            preparedStatement.setString(2, updatedDeveloper.getEmail());
            preparedStatement.setInt(3, developerId);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                // Mise à jour des compétences du développeur
                updateDeveloperSkills(connection, developerId, updatedDeveloper.getSkills());
                return updatedDeveloper;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Méthode pour supprimer un développeur par son ID de la base de données
    public static void deleteDeveloper(int developerId) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + DEVELOPERS_TABLE + " WHERE id = ?")) {

            preparedStatement.setInt(1, developerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode utilitaire pour mapper les résultats d'une requête SQL à un objet Developer
    private static Developer mapResultSetToDeveloper(ResultSet resultSet) throws SQLException {
        Developer developer = new Developer();
        developer.setId(resultSet.getInt("id"));
        developer.setName(resultSet.getString("name"));
        developer.setEmail(resultSet.getString("email"));
        developer.setSkills(getDeveloperSkills(developer.getId()));
        return developer;
    }

    // Méthode utilitaire pour récupérer les compétences d'un développeur depuis la base de données
    private static List<Skill> getDeveloperSkills(int developerId) {
        List<Skill> skills = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT s.* FROM " + DEVELOPERS_SKILLS_TABLE + " ds " +
                     "JOIN " + SKILLS_TABLE + " s ON ds.skill_id = s.id WHERE ds.developer_id = ?")) {

            preparedStatement.setInt(1, developerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Skill skill = mapResultSetToSkill(resultSet);
                skills.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }

    // Méthode utilitaire pour insérer les compétences d'un développeur dans la base de données
    private static void insertDeveloperSkills(Connection connection, Developer developer) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + DEVELOPERS_SKILLS_TABLE + " (developer_id, skill_id) VALUES (?, ?)")) {
            for (Skill skill : developer.getSkills()) {
                preparedStatement.setInt(1, developer.getId());
                preparedStatement.setInt(2, skill.getId());
                preparedStatement.executeUpdate();
            }
        }
    }

    // Méthode utilitaire pour mettre à jour les compétences d'un développeur dans la base de données
    private static void updateDeveloperSkills(Connection connection, int developerId, List<Skill> updatedSkills) throws SQLException {
        // Suppression des anciennes compétences du développeur
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + DEVELOPERS_SKILLS_TABLE + " WHERE developer_id = ?")) {
            preparedStatement.setInt(1, developerId);
            preparedStatement.executeUpdate();
        }
        // Insertion des nouvelles compétences du développeur
        insertDeveloperSkills(connection, new Developer(developerId, null, null, updatedSkills));
    }
}
