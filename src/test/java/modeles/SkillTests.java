package modeles;

import modeles.Skill;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class SkillTests {
    String skillName = "Node";
    Skill skill;

    @BeforeEach
    public void createSkill() {
        skill = new Skill(skillName);
    }

    @Test
    public void shouldGetName() {
        assert(skill.getSkillName().equals(skillName));
    }

    @Test
    public void shouldSetName() {
        String newName = "Express";
        skill.setSkillName(newName);
        assert(skill.getSkillName().equals(newName));
    }

    /*@Test
    public void shouldNotSetNameEmpty() {
        skill.setSkillName("");
        assert(skill.getSkillName().equals(skillName));
    }*/
}
