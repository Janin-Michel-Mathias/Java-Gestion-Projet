package service;

import controller.ProjectController;
import controller.TeamController;
import controller.DevelopersController;
import controller.StackController;

import modeles.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeamAllocationService {
    private static final int MINIMUM_TEAM_SIZE = 3;
    private static final int MAXIMUM_TEAM_SIZE = 8;
    private static final int MAX_JUNIORS_IN_TEAM = 3;
    private static final int MIN_TEAM_SIZE_FOR_EXPERT = 5;
    private static final int MINIMUM_DAYS_BETWEEN_TEAM_CHANGE = 180;

    public static List<Developer> allocateTeamForProject(int projectId) {
        List<Team> teams = TeamController.getAllTeams();
        List<Developer> allDevelopers = DevelopersController.getAllDevelopers();
        Project project = ProjectController.getProjectById(projectId);

        TimeSlot projectTimeSlots = new TimeSlot(project.getStartDate(), project.getEndDate());

        List<Developer> availableDevelopers = getAvailableDevelopers(allDevelopers, teams, projectTimeSlots);
        List<Developer> filteredDevelopers = filterDevelopersBySkill(availableDevelopers, StackController.getStackByNameProject(project.getName()));

        return filteredDevelopers;
    }

    public static List<Developer> getAvailableDevelopers(List<Developer> allDevelopers, List<Team> allTeams, TimeSlot desiredTimeSlot) {
        List<Developer> availableDevelopers = new ArrayList<>();

        // Step 2: Identify developers who are not part of any team
        for (Developer developer : allDevelopers) {
            if (!isDeveloperInAnyTeam(developer, allTeams)) {
                availableDevelopers.add(developer);
            } else {
                // Step 3: Check the projects associated with the developer's teams
                List<Team> developerTeam = getTeamsForDeveloper(developer, allTeams);
                for (Team team : developerTeam) {
                    Project project = ProjectController.getProjectById(team.getId_project());
                    if (!isTimeSlotWithinProjectDates(desiredTimeSlot, project)) {
                        availableDevelopers.add(developer);
                    }
                }
            }
        }
        return availableDevelopers;
    }

    private static boolean isDeveloperInAnyTeam(Developer developer, List<Team> allTeams) {
        for (Team team : allTeams) {
            if (team.getId_developer() == developer.getId()) {
                return true;
            }
        }
        return false;
    }


    private static List<Team> getTeamsForDeveloper(Developer developer, List<Team> allTeams) {
        List<Team> developerTeams = new ArrayList<>();
        for (Team team : allTeams) {
            if (team.getId_developer() == developer.getId()) {
                developerTeams.add(team);
            }
            return developerTeams;
        }
        return developerTeams;
    }

    private static boolean isTimeSlotWithinProjectDates(TimeSlot desiredTimeSlot, Project project) {
        // Get the start and end dates of the project
        Date projectStartDate = project.getStartDate();
        Date projectEndDate = project.getEndDate();

        // Get the start and end dates of the desired time slot
        Date desiredStartDate = desiredTimeSlot.getStartTime();
        Date desiredEndDate = desiredTimeSlot.getEndTime();

        // Check if the desired start date is between project start and end dates
        boolean isStartDateWithinProject = desiredStartDate.after(projectStartDate) && desiredStartDate.before(projectEndDate);

        // Check if the desired end date is between project start and end dates
        boolean isEndDateWithinProject = desiredEndDate.after(projectStartDate) && desiredEndDate.before(projectEndDate);

        // Check if the desired start date is the same as project start date or after
        boolean isStartDateExactProject = desiredStartDate.equals(projectStartDate);

        // Check if the desired end date is the same as project end date or before
        boolean isEndDateExactProject = desiredEndDate.equals(projectEndDate);

        // Return true if any of the conditions is true
        return isStartDateWithinProject || isEndDateWithinProject || isStartDateExactProject || isEndDateExactProject;
    }

    public static List<Developer> filterDevelopersBySkill(List<Developer> developers, List<SkillStacks> projectStack) {
        List<Developer> filteredDevelopers = new ArrayList<>();

        for (Developer developer : developers) {
            if (hasSkillInProjectStack(developer, projectStack)) {
                filteredDevelopers.add(developer);
            }
        }

        return filteredDevelopers;
    }

    private static boolean hasSkillInProjectStack(Developer developer, List<SkillStacks> projectStack) {
        for (SkillStacks skillStack : projectStack) {
            if (developerHasSkill(developer, skillStack.getSkillName())) {
                return true;
            }
        }
        return false;
    }

    private static boolean developerHasSkill(Developer developer, String skillName) {
        for (SkillExperience skillExperience : developer.getSkills()) {
            if (skillExperience.getSkill().equals(skillName)) {
                return true;
            }
        }
        return false;
    }
}
