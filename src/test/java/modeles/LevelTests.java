package modeles;

import modeles.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LevelTests {
    String name = "name";

    Level level;
    @BeforeEach
    public void createLevel() {
        level = new Level(name);
    }

    @Test
    public void shouldGetName() {
        assert(level.getLevel().equals(name));
    }

    @Test
    public void shouldSetName() {
        String newName = "newName";
        level.setLevel(newName);
        assert(level.getLevel().equals(newName));
    }

    @Test
    public void shouldNotSetNameEmpty() {
        level.setLevel("");
        assert(level.getLevel().equals(name));
    }
}
