import modeles.Developer;
import modeles.Skill;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DeveloperTests {
    String name = "nom";
    String email = "example@example.com";
    List<Skill> skills = new ArrayList<>();

    @Before
    public void setSkills() {
        skills.add(new Skill("skill1"));
        skills.add(new Skill("skill2"));
        skills.add(new Skill("skill3"));
        skills.add(new Skill("skill4"));
    }

    Developer dev;
    @BeforeEach
    public void createDeveloper() {
        dev = new Developer(name, email, skills);
    }

    @Test
    public void shouldGetName() {
        assert(dev.getName().equals(name));
    }

    @Test
    public void shouldGetEmail() {
        assert(dev.getEmail().equals(email));
    }

    @Test
    public void shouldGetSkills() {
        assert(dev.getSkills().equals(skills));
    }

    @Test
    public void shouldSetName() {
        String newName = "new name";
        dev.setName(newName);
        assert(dev.getName().equals(newName));
    }

    @Test
    public void shouldNotSetEmptyName() {
        dev.setName("");
        assert(dev.getName().equals(name));
    }

    @Test
    public void shouldSetEmail() {
        String newEmail = "newEmail@email.com";
        dev.setEmail(newEmail);
        assert(dev.getEmail().equals(newEmail));
    }

    @Test
    public void shouldNotSetEmailWithoutExtension() {
        dev.setEmail("newEmail@email");
        assert(dev.getEmail().equals(email));
    }

    @Test
    public void shouldNotSetEmailWithoutDomain() {
        dev.setEmail("newEmail@.com");
        assert(dev.getEmail().equals(email));
    }

    @Test
    public void shouldNotSetEmailWithoutFirstPart() {
        dev.setEmail("@email.com");
        assert(dev.getEmail().equals(email));
    }

    @Test
    public void shouldNotSetEmailWithoutSecondPart() {
        dev.setEmail("aaa@");
        assert(dev.getEmail().equals(email));
    }

    @Test
    public void shouldNotSetEmailEmpty() {
        dev.setEmail("");
        assert(dev.getEmail().equals(email));
    }
}
