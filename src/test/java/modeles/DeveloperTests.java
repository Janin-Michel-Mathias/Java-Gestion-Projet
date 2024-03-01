package modeles;

import modeles.Developer;
import modeles.Skill;
import modeles.SkillExperience;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DeveloperTests {
    String name = "nom";
    String email = "example@example.com";
    static List<SkillExperience> skills = new ArrayList<>();

    Date startDate = new Date();
    Date endDate = new Date();

    int id = 1;

    Developer dev;
    @BeforeAll
    public static void createSkills() {
        SkillExperience skillExperience = new SkillExperience("Node", 100, "Beginner", 1);
        skills.add(skillExperience);
    }
    @BeforeEach
    public void createDeveloper() {
        dev = new Developer(id, name, email, startDate, endDate, skills);
    }

    @Test
    public void shouldGetId() {
        assert(dev.getId() == id);
    }

    @Test
    public void shouldSetId() {
        int newId = 2;
        dev.setId(newId);
        assert(dev.getId() == newId);
    }

    @Test
    public void shouldGetName() {
        assert(dev.getName().equals(name));
    }

    @Test
    public void shouldSetName() {
        String newName = "newName";
        dev.setName(newName);
        assert(dev.getName().equals(newName));
    }

    @Test
    public void shouldNotSetNameEmpty() {
        dev.setName("");
        assert(dev.getName().equals(name));
    }

    @Test
    public void shouldGetEmail() {
        assert(dev.getEmail().equals(email));
    }

    @Test
public void shouldSetEmail() {
        String newEmail = "newEmail";
        dev.setEmail(newEmail);
        assert(dev.getEmail().equals(newEmail));
    }

    @Test
    public void shouldNotSetEmailEmpty() {
        dev.setEmail("");
        assert(dev.getEmail().equals(email));
    }

    @Test
    public void shouldGetStartDate() {
        assert(dev.getStartDate().equals(startDate));
    }

    @Test
    public void shouldSetStartDate() {
        Date newStartDate = new Date();
        dev.setStartDate(newStartDate);
        assert(dev.getStartDate().equals(newStartDate));
    }

    @Test
    public void shouldGetEndDate() {
        assert(dev.getEndDate().equals(endDate));
    }

    @Test
    public void shouldSetEndDate() {
        Date newEndDate = new Date();
        dev.setEndDate(newEndDate);
        assert(dev.getEndDate().equals(newEndDate));
    }

    @Test
    public void shouldGetSkills() {
        assert(dev.getSkills().equals(skills));
    }

    @Test
    public void shouldSetSkills() {
        List<SkillExperience> newSkills = new ArrayList<>();
        SkillExperience skillExperience = new SkillExperience("Express", 200, "Intermediate", 2);
        newSkills.add(skillExperience);
        dev.setSkills(newSkills);
        assert(dev.getSkills().equals(newSkills));
    }

}
