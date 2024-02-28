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
            SkillStacks skillStacks = StackController.getStackByNameProject(project.getName());

            FullProject fullProject = new FullProject(project, skillStacks, teams);
            fullprojectList.add(fullProject);
        }

        return fullprojectList;
    }
}
