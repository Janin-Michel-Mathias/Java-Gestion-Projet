package modeles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SkillStacksTests {
    String skillName = "skillName";
    String projectName = "projectName";
    int numberOfDevelopers = 1;

    SkillStacks skillStacks;

    @BeforeEach
    public void createSkillStacks() {
        skillStacks = new SkillStacks(skillName, projectName, numberOfDevelopers);
    }

    @Test
    public void shouldGetProjectName() {
        assert(skillStacks.getProjectName().equals(projectName));
    }

    @Test
    public void shouldSetProjectName() {
        String newProjectName = "newProjectName";
        skillStacks.setProjectName(newProjectName);
        assert(skillStacks.getProjectName().equals(newProjectName));
    }

    @Test
    public void shouldGetNumberOfDevelopers() {
        assert(skillStacks.getNumberOfDevelopers() == numberOfDevelopers);
    }

    @Test
    public void shouldSetNumberOfDevelopers() {
        int newNumberOfDevelopers = 2;
        skillStacks.setNumberOfDevelopers(newNumberOfDevelopers);
        assert(skillStacks.getNumberOfDevelopers() == newNumberOfDevelopers);
    }

   /* @Test
    public void shouldNotSetNumberOfDevelopersNegative() {
        int newNumberOfDevelopers = -1;
        skillStacks.setNumberOfDevelopers(newNumberOfDevelopers);
        assert(skillStacks.getNumberOfDevelopers() == numberOfDevelopers);
    }

    @Test
    public void shouldNotSetProjectNameEmpty() {
        skillStacks.setProjectName("");
        assert(skillStacks.getProjectName().equals(projectName));
    }*/

    @Test
    public void shouldGetSkillName() {
        assert(skillStacks.getSkillName().equals(skillName));
    }

    @Test
    public void shouldSetSkillName() {
        String newSkillName = "newSkillName";
        skillStacks.setSkillName(newSkillName);
        assert(skillStacks.getSkillName().equals(newSkillName));
    }

    /*@Test
    public void shouldNotSetSkillNameEmpty() {
        skillStacks.setSkillName("");
        assert(skillStacks.getSkillName().equals(skillName));
    }

    @Test
    public void shouldNotSetSkillNameNull() {
        skillStacks.setSkillName(null);
        assert(skillStacks.getSkillName().equals(skillName));
    }

    @Test
    public void shouldNotSetProjectNameNull() {
        skillStacks.setProjectName(null);
        assert(skillStacks.getProjectName().equals(projectName));
    }*/
}
