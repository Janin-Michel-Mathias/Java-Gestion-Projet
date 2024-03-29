package controller;

import modeles.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import GlobalVariables.EnvironmentVariable;

public class SkillsController {
    private static final String DATABASE_URL = EnvironmentVariable.getDatabaseUrl();
    static final String SKILLS_TABLE = "skills";

    // Méthode pour récupérer toutes les compétences depuis la base de données
    public static List<Skill> getAllSkills() {
        List<Skill> skills = new ArrayList<>();
        Connection connection = EnvironmentVariable.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + SKILLS_TABLE)) {

            while (resultSet.next()) {
                Skill skill = mapResultSetToSkill(resultSet);
                skills.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }

    // Méthode pour récupérer une compétence par son ID depuis la base de données
    public static Skill getSkillById(int skillId) {
        Connection connection = EnvironmentVariable.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + SKILLS_TABLE + " WHERE id = ?")) {

            preparedStatement.setInt(1, skillId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapResultSetToSkill(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Méthode pour créer une nouvelle compétence dans la base de données
    public static Skill createSkill(Skill newSkill) {
        Connection connection = EnvironmentVariable.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + SKILLS_TABLE + " (skill_name) VALUES (?)")) {

            preparedStatement.setString(1, newSkill.getSkillName());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                return newSkill;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Méthode pour mettre à jour une compétence par son ID dans la base de données
    public static Skill updateSkill(Skill updatedSkill) {
        Connection connection = EnvironmentVariable.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + SKILLS_TABLE + " SET skill_name = ? WHERE id = ?")) {

            preparedStatement.setString(1, updatedSkill.getSkillName());

            preparedStatement.setInt(2, updatedSkill.getId());

            // Exécution de la requête SQL, si le update echoue, on retourne null
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                return updatedSkill;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Méthode pour supprimer une compétence par son ID de la base de données
    public static void deleteSkill(int skillId) {
        Connection connection = EnvironmentVariable.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + SKILLS_TABLE + " WHERE id = ?")) {

            preparedStatement.setInt(1, skillId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode utilitaire pour mapper les résultats d'une requête SQL à un objet Skill
    static Skill mapResultSetToSkill(ResultSet resultSet) throws SQLException {
        Skill skill = new Skill();
        skill.setId(resultSet.getInt("id"));
        skill.setSkillName(resultSet.getString("skill_name"));
        return skill;
    }
}
