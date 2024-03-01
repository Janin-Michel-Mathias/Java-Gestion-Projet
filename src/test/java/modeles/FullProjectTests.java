package modeles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

public class FullProjectTests {
    Project project;
    List<SkillStacks> skillStacks;
    List<Team> team;

    FullProject fullProject;

    @BeforeEach
    public void createFullProject() {
        project = new Project();
        skillStacks = new ArrayList<SkillStacks>();
        team = new ArrayList<Team>();
        fullProject = new FullProject(project, skillStacks, team);
    }

    @Test
    public void shouldGetProject() {
        assert(fullProject.getProject().equals(project));
    }

    @Test
    public void shouldSetProject() {
        Project newProject = new Project();
        fullProject.setProject(newProject);
        assert(fullProject.getProject().equals(newProject));
    }

    @Test
    public void shouldGetTeam() {
        assert(fullProject.getTeam().equals(team));
    }

    @Test
    public void shouldSetTeam() {
        List<Team> newTeam = new ArrayList<Team>();
        fullProject.setTeam(newTeam);
        assert(fullProject.getTeam().equals(newTeam));
    }

    @Test
    public void shouldGetSkillStacks() {
        assert(fullProject.getSkillStacks().equals(skillStacks));
    }

    @Test
    public void shouldSetSkillStacks() {
        List<SkillStacks> newSkillStacks = new ArrayList<SkillStacks>();
        fullProject.setSkillStacks(newSkillStacks);
        assert(fullProject.getSkillStacks().equals(newSkillStacks));
    }

  /*  @Test
    public void shouldNotSetSkillStacksNull() {
        fullProject.setSkillStacks(null);
        assert(fullProject.getSkillStacks().equals(skillStacks));
    }

    @Test
    public void shouldNotSetTeamNull() {
        fullProject.setTeam(null);
        assert(fullProject.getTeam().equals(team));
    }

    @Test
    public void shouldNotSetProjectNull() {
        fullProject.setProject(null);
        assert (fullProject.getProject().equals(project));
    }*/
}
