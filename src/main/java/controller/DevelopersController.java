package controller;

import GlobalVariables.EnvironmentVariable;
import modeles.Developer;
import modeles.Skill;
import modeles.SkillExperience;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static controller.SkillsController.SKILLS_TABLE;
import static controller.SkillsController.mapResultSetToSkill;

public class DevelopersController {
    private static final String DEVELOPERS_TABLE = "developers";
    private static final String DEVELOPERS_SKILLS_TABLE = "developer_skills";

    // Méthode pour récupérer tous les développeurs depuis la base de données
    public static List<Developer> getAllDevelopers() {
        List<Developer> developers = new ArrayList<>();
        Connection connection = EnvironmentVariable.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + DEVELOPERS_TABLE)) {

            while (resultSet.next()) {
                Developer developer = mapResultSetToDeveloper(resultSet);
                developer.setSkills(getDeveloperSkills(developer.getId()));
                developers.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developers;
    }

    // Méthode pour récupérer un développeur par son ID depuis la base de données
    public static Developer getDeveloperById(int developerId) {
        Connection connection = EnvironmentVariable.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + DEVELOPERS_TABLE + " WHERE id = ?")) {

            preparedStatement.setInt(1, developerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Developer developer = mapResultSetToDeveloper(resultSet);
                developer.setSkills(getDeveloperSkills(developer.getId()));
                return developer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Developer getDeveloperByEmail(String developerEmail) {
        Connection connection = EnvironmentVariable.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + DEVELOPERS_TABLE + " WHERE email = ?")) {

            preparedStatement.setString(1, developerEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Developer developer = mapResultSetToDeveloper(resultSet);
                developer.setSkills(getDeveloperSkills(developer.getId()));
                return developer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    // Méthode pour mettre à jour un développeur par son ID dans la base de données
    public static Developer updateDeveloper(Developer updatedDeveloper) {
        Connection connection = EnvironmentVariable.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + DEVELOPERS_TABLE + " SET name = ?, email = ? WHERE id = ?")) {

            preparedStatement.setString(1, updatedDeveloper.getName());
            preparedStatement.setString(2, updatedDeveloper.getEmail());
            preparedStatement.setInt(3, updatedDeveloper.getId());

            preparedStatement.executeUpdate();

            updatedDeveloper = getDeveloperById(updatedDeveloper.getId());
            return updatedDeveloper;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedDeveloper;
    }

    // Méthode pour supprimer un développeur par son email de la base de données, en supprimant également ses compétences
    public static void deleteDeveloper(Developer deletedDeveloper) {
        deletedDeveloper = getDeveloperByEmail(deletedDeveloper.getEmail());
        if (deletedDeveloper != null) {
            Connection connection = EnvironmentVariable.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + DEVELOPERS_TABLE + " WHERE email = ?")) {

                preparedStatement.setString(1, deletedDeveloper.getEmail());
                preparedStatement.executeUpdate();

                // Suppression des compétences du développeur
                try (PreparedStatement deleteSkillsStatement = connection.prepareStatement("DELETE FROM " + DEVELOPERS_SKILLS_TABLE + " WHERE developer_id = ?")) {
                    deleteSkillsStatement.setInt(1, deletedDeveloper.getId());
                    deleteSkillsStatement.executeUpdate();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Méthode utilitaire pour mapper les résultats d'une requête SQL à un objet Developer
    private static Developer mapResultSetToDeveloper(ResultSet resultSet) throws SQLException {
        Developer developer = new Developer();
        developer.setId(resultSet.getInt("id"));
        developer.setName(resultSet.getString("name"));
        developer.setEmail(resultSet.getString("email"));
        developer.setStartDate(resultSet.getDate("start_date"));
        developer.setEndDate(resultSet.getDate("end_date"));
        return developer;
    }

    // Méthode utilitaire pour récupérer les compétences d'un développeur depuis la base de données
    private static List<SkillExperience> getDeveloperSkills(int developerId) {
        List<SkillExperience> skills = new ArrayList<>();
        Connection connection = EnvironmentVariable.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT s.*, ls.id as experience_id, ls.level, experience FROM " + DEVELOPERS_SKILLS_TABLE + " ds " +
                     "JOIN " + SKILLS_TABLE + " s ON ds.skill_id = s.id " +
                     "JOIN level_skills ls ON ds.level_id = ls.id WHERE ds.developer_id = ?")) {

            preparedStatement.setInt(1, developerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Skill skill = mapResultSetToSkill(resultSet);
                int experienceId = resultSet.getInt("experience_id");
                String level = resultSet.getString("level");

                SkillExperience skillExperience = new SkillExperience();
                skillExperience.setSkill(skill.getSkillName());
                skillExperience.setRankNb(experienceId);
                skillExperience.setRank(level);
                skillExperience.setExperience(resultSet.getInt("experience"));

                skills.add(skillExperience);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }

    // Méthode pour créer un nouveau développeur dans la base de données
    public static Developer createDeveloper(Developer newDeveloper) {
        Connection connection = EnvironmentVariable.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO " + DEVELOPERS_TABLE + " (name, email, start_date, end_date) VALUES (?, ?, datetime('now'), null)")) {

            preparedStatement.setString(1, newDeveloper.getName());
            preparedStatement.setString(2, newDeveloper.getEmail());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                // Retrieve the last inserted row's ID using a separate query
                int developerId = getLastInsertedId(connection);
                newDeveloper.setId(developerId);

                // Insertion des compétences du développeur
                insertDeveloperSkills(newDeveloper);
                newDeveloper = getDeveloperById(developerId);
                return newDeveloper;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Helper method to retrieve the last inserted row's ID
    private static int getLastInsertedId(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT last_insert_rowid()")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        }
        throw new SQLException("Failed to retrieve last inserted ID.");
    }


    // Méthode utilitaire pour insérer les compétences d'un développeur dans la base de données
    static void insertDeveloperSkills(Developer developer) throws SQLException {
        Connection connection = EnvironmentVariable.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO " + DEVELOPERS_SKILLS_TABLE + " (developer_id, skill_id, level_id, experience) VALUES (?, ?, ?, ?)")) {

            for (SkillExperience skillExperience : developer.getSkills()) {
                // Get or insert the skill and retrieve its id
                int skillId = getOrCreateSkillId(skillExperience.getSkill());

                // Map the experience to level_id
                int levelId = mapExperienceToLevelId(skillExperience.getExperience());

                preparedStatement.setInt(1, developer.getId());
                preparedStatement.setInt(2, skillId);
                preparedStatement.setInt(3, levelId);
                preparedStatement.setInt(4, skillExperience.getExperience());
                preparedStatement.executeUpdate();
            }
        }
    }

    // Méthode utilitaire pour récupérer l'id d'une compétence, l'ajouter si elle n'existe pas
    static int getOrCreateSkillId(String skillName) throws SQLException {
        Connection connection = EnvironmentVariable.getConnection();
        // Check if the skill exists in the database
        try (PreparedStatement checkStatement = connection.prepareStatement(
                "SELECT id FROM " + SKILLS_TABLE + " WHERE skill_name = ?")) {
            checkStatement.setString(1, skillName);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                // Skill exists, return its id
                return resultSet.getInt("id");
            } else {
                // Skill doesn't exist, insert it and return its id
                return insertSkillAndGetId(skillName);
            }
        }
    }

    // Méthode utilitaire pour insérer une nouvelle compétence et récupérer son id
    private static int insertSkillAndGetId(String skillName) throws SQLException {
        Connection connection = EnvironmentVariable.getConnection();
        try (PreparedStatement insertStatement = connection.prepareStatement(
                "INSERT INTO " + SKILLS_TABLE + " (skill_name) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS)) {

            insertStatement.setString(1, skillName);
            int affectedRows = insertStatement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    // Return the id of the newly inserted skill
                    return generatedKeys.getInt(1);
                }
            }

            throw new SQLException("Failed to insert skill and retrieve its id");
        }
    }

    // Méthode utilitaire pour mapper l'expérience à level_id
    private static int mapExperienceToLevelId(int experience) {
        if (experience >= 0 && experience <= 3) {
            return 1; // Junior
        } else if (experience > 3 && experience <= 5) {
            return 2; // Expérimenté
        } else {
            return 3; // Expert
        }
    }


    // Méthode utilitaire pour mettre à jour l'experience d'un développeur sur une compétence
    private static void updateDeveloperSkills(int developerId, List<SkillExperience> updatedSkills) {
            // Insert the updated skills
            Connection connection = EnvironmentVariable.getConnection();
            try (PreparedStatement insertStatement = connection.prepareStatement(
                    "UPDATE " + DEVELOPERS_SKILLS_TABLE + " SET experience = ?, level_id = ? WHERE developer_id = ? AND skill_id = ?")) {

                for (SkillExperience skillExperience : updatedSkills) {
                    // Get or insert the skill and retrieve its id
                    int skillId = getOrCreateSkillId(skillExperience.getSkill());

                    // Map the experience to level_id
                    int levelId = mapExperienceToLevelId(skillExperience.getExperience());

                    insertStatement.setInt(1, skillExperience.getExperience());
                    insertStatement.setInt(2, levelId);
                    insertStatement.setInt(3, developerId);
                    insertStatement.setInt(4, skillId);

                    insertStatement.executeUpdate();
                }
        } catch (SQLException e) {
            e.printStackTrace();
    }
}

    public static Developer fireDeveloper(Developer developer) {
        Connection connection = EnvironmentVariable.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE " + DEVELOPERS_TABLE + " SET end_date = datetime('now') WHERE email = ?")) {

            preparedStatement.setString(1, developer.getEmail());
            preparedStatement.executeUpdate();

            developer = getDeveloperByEmail(developer.getEmail());
            return developer;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //add skill to developer
    public static Developer addSkillToDeveloper(Developer newdeveloper) {
        Developer developer = getDeveloperByEmail(newdeveloper.getEmail());

        if (developer != null) {
            Connection connection = EnvironmentVariable.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + DEVELOPERS_SKILLS_TABLE + " (developer_id, skill_id, level_id, experience) VALUES (?, ?, ?, ?)")) {

                for (SkillExperience skillExperience : newdeveloper.getSkills()) {
                    // Get or insert the skill and retrieve its id
                    int skillId = getOrCreateSkillId(skillExperience.getSkill());

                    // Map the experience to level_id
                    int levelId = mapExperienceToLevelId(skillExperience.getExperience());

                    preparedStatement.setInt(1, developer.getId());
                    preparedStatement.setInt(2, skillId);
                    preparedStatement.setInt(3, levelId);
                    preparedStatement.setInt(4, skillExperience.getExperience());
                    preparedStatement.executeUpdate();
                }
                developer = getDeveloperByEmail(newdeveloper.getEmail());
                return developer;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //update developer experience on a skill
    public static Developer updateDeveloperSkill(Developer updatedDeveloper) {
        Developer developer = getDeveloperByEmail(updatedDeveloper.getEmail());
        if (developer != null) {
                updateDeveloperSkills(developer.getId(), updatedDeveloper.getSkills());
                developer = getDeveloperByEmail(updatedDeveloper.getEmail());
                return developer;
        }
        return null;
    }

    //delete developer skill
    public static Developer deleteDeveloperSkill(Developer updatedDeveloper) {
        Developer developer = getDeveloperByEmail(updatedDeveloper.getEmail());
        if (developer != null) {
            Connection connection = EnvironmentVariable.getConnection();
            try (
                 PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + DEVELOPERS_SKILLS_TABLE + " WHERE developer_id = ? AND skill_id = ?")) {

                for (SkillExperience skillExperience : updatedDeveloper.getSkills()) {
                    // Get or insert the skill and retrieve its id
                    int skillId = getOrCreateSkillId(skillExperience.getSkill());

                    preparedStatement.setInt(1, developer.getId());
                    preparedStatement.setInt(2, skillId);
                    preparedStatement.executeUpdate();
                }
                developer = getDeveloperByEmail(updatedDeveloper.getEmail());
                return developer;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}