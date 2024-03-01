package modeles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PriorityTests {
    String type = "type";

    Priority priority;
    @BeforeEach
    public void createPriority() {
        priority = new Priority(type);
    }

    @Test
    public void shouldGetType() {
        assert(priority.getType().equals(type));
    }

    @Test
    public void shouldSetType() {
        String newType = "newType";
        priority.setType(newType);
        assert(priority.getType().equals(newType));
    }

    @Test
    public void shouldNotSetTypeEmpty() {
        priority.setType("");
        assert(priority.getType().equals(type));
    }
}
