package service;

import controller.TeamController;
import modeles.Team;

import java.util.List;

public class TeamAllocationService {
    private static final int MINIMUM_TEAM_SIZE = 3;
    private static final int MAXIMUM_TEAM_SIZE = 8;
    private static final int MAX_JUNIORS_IN_TEAM = 3;
    private static final int MIN_TEAM_SIZE_FOR_EXPERT = 5;
    private static final int MINIMUM_DAYS_BETWEEN_TEAM_CHANGE = 180;

    public static List<Team> allocateTeamForProject(int projectId) {
        List<Team> teams = TeamController.getTeamByIdProject(projectId);

        return null;
    }
}
