package modeles;

import modeles.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamTests {
    int id_developer = 1;
    int project_id = 1;

    Team team;

    @BeforeEach
    public void createTeam() {
        team = new Team(id_developer, project_id, "project_name", "developer_name");
    }

    @Test
    public void shouldGetId_developer() {
        assert(team.getId_developer() == id_developer);
    }

    @Test
    public void shouldSetId_developer() {
        int newId_developer = 2;
        team.setId_developer(newId_developer);
        assert(team.getId_developer() == newId_developer);
    }

    @Test
    public void shouldGetId_project() {
        assert(team.getId_project() == project_id);
    }

    @Test
    public void shouldSetId_project() {
        int newId_project = 2;
        team.setId_project(newId_project);
        assert(team.getId_project() == newId_project);
    }
}
