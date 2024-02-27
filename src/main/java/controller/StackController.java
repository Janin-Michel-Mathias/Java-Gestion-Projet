package controller;

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
                stack.setSkillName(Objects.requireNonNull(SkillsController.getSkillById(rs.getInt("id_skill"))).getSkillName());
                stack.setNumberOfDevelopers(rs.getInt("numberDev"));
                stacks.add(stack);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return stacks;
    }

}