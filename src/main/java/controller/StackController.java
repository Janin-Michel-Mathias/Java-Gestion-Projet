package controller;

import modeles.Skill;
import modeles.SkillStacks;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import GlobalVariables.EnvironmentVariable;

public class StackController {
    private static final String DATABASE_URL = EnvironmentVariable.getDatabaseUrl();
    static final String STACKS_TABLE = "stack";

    public static List<SkillStacks> getAllStacks() {
        List<SkillStacks> stacks = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + STACKS_TABLE)) {
            while (rs.next()) {
                SkillStacks stack = new SkillStacks();
                stack.setProjectName(rs.getString("name_project"));
                stack.setId(rs.getInt("id_skill"));
                // Join with skills table to get the skill_name
                Skill skill = SkillsController.getSkillById(stack.getId());
                if (skill != null) {
                    stack.setSkillName(skill.getSkillName());
                }
                stack.setNumberOfDevelopers(rs.getInt("numberDev"));
                stacks.add(stack);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return stacks;
    }

    public static SkillStacks getStackByNameProject(String nameProject) {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + STACKS_TABLE + " WHERE name_project = ?")) {
            stmt.setString(1, nameProject);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                SkillStacks stack = new SkillStacks();
                stack.setProjectName(rs.getString("name_project"));
                stack.setId(rs.getInt("id_skill"));
                // Join with skills table to get the skill_name
                Skill skill = SkillsController.getSkillById(stack.getId());
                if (skill != null) {
                    stack.setSkillName(skill.getSkillName());
                }
                stack.setNumberOfDevelopers(rs.getInt("numberDev"));
                return stack;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static SkillStacks createStack(SkillStacks newStack) {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO " + STACKS_TABLE + " (name_project, id_skill, numberDev) VALUES (?, ?, ?)")) {
            stmt.setString(1, newStack.getProjectName());
            stmt.setInt(2, newStack.getId());
            stmt.setInt(3, newStack.getNumberOfDevelopers());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return newStack;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static SkillStacks updateStack(SkillStacks updatedStack) {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement stmt = conn.prepareStatement("UPDATE " + STACKS_TABLE + " SET name_project = ?, id_skill = ?, numberDev = ? WHERE id_skill = ? AND name_project = ?")) {
            stmt.setString(1, updatedStack.getProjectName());
            stmt.setInt(2, updatedStack.getId());
            stmt.setInt(3, updatedStack.getNumberOfDevelopers());
            stmt.setInt(4, updatedStack.getId());
            stmt.setString(5, updatedStack.getProjectName());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return updatedStack;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void deleteSkillStack(int skillId, String nameProject) {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM " + STACKS_TABLE + " WHERE id_skill = ? AND name_project = ?")) {
            stmt.setInt(1, skillId);
            stmt.setString(2, nameProject);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteStack(String nameProject) {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM " + STACKS_TABLE + " WHERE name_project = ?")) {
            stmt.setString(1, nameProject);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}