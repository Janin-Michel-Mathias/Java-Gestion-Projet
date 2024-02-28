package service;

import controller.ProjectController;
import controller.StackController;
import controller.TeamController;

import modeles.FullProject;
import modeles.Project;
import modeles.SkillStacks;
import modeles.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetFullProject {

    public static List<FullProject> getAllFullProjects() {
        List<FullProject> fullprojectList = new ArrayList<>();
        List<Project> projects = ProjectController.getAllProjects();

        for (Project project : projects) {
            List<Team> teams = TeamController.getTeamByIdProject(project.getId());
            List<SkillStacks> skillStacks = StackController.getStackByNameProject(project.getName());

            FullProject fullProject = new FullProject(project, skillStacks, teams);
            fullprojectList.add(fullProject);
        }

        return fullprojectList;
    }

    public static FullProject getFullProjectById(int id) {
        Project project = ProjectController.getProjectById(id);
        List<Team> teams = TeamController.getTeamByIdProject(id);
        List<SkillStacks> skillStacks = StackController.getStackByNameProject(project.getName());

        return new FullProject(project, skillStacks, teams);
    }

    public static FullProject getFullProjectByName(String name) {
        Project project = ProjectController.getProjectByName(name);
        List<Team> teams = TeamController.getTeamByIdProject(project.getId());
        List<SkillStacks> skillStacks = StackController.getStackByNameProject(project.getName());

        return new FullProject(project, skillStacks, teams);
    }
}
