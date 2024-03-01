package modeles;

import modeles.TeamCore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
public class TeamCoreTests {
    int id_developer = 1;
    int project_id = 1;
    TeamCore teamcore;

    @BeforeEach
    public void createTeamCore() {
        teamcore = new TeamCore(id_developer, project_id);
    }

    @Test
    public void shouldGetId_developer() {
        assert(teamcore.getId_developer() == id_developer);
    }

    @Test
    public void shouldSetId_developer() {
        int newId_developer = 2;
        teamcore.setId_developer(newId_developer);
        assert(teamcore.getId_developer() == newId_developer);
    }

    @Test
    public void shouldGetId_project() {
        assert(teamcore.getId_project() == project_id);
    }

    @Test
    public void shouldSetId_project() {
        int newId_project = 2;
        teamcore.setId_project(newId_project);
        assert(teamcore.getId_project() == newId_project);
    }
}
