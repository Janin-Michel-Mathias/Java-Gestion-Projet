package modeles;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ProjectTests {
    int id = 1;
    String name = "name";
    int priorityId = 1;
    String priorityType = "type";
    String description = "description";
    Date startDate = new Date();
    Date endDate = new Date();

    Project project;
    @BeforeEach
    public void createProject() {
        project = new Project(id, name, priorityId, priorityType, description, startDate, endDate);
    }

    @Test
    public void shouldGetId() {
        assert(project.getId() == id);
    }

    @Test
    public void shouldSetId() {
        int newId = 2;
        project.setId(newId);
        assert(project.getId() == newId);
    }

    @Test
    public void shouldGetName() {
        assert(project.getName().equals(name));
    }

    @Test
    public void shouldSetName() {
        String newName = "newName";
        project.setName(newName);
        assert(project.getName().equals(newName));
    }

    @Test
    public void shouldNotSetNameEmpty() {
        project.setName("");
        assert(project.getName().equals(name));
    }

    @Test
    public void shouldGetPriorityId() {
        assert(project.getPriorityId() == priorityId);
    }

    @Test
    public void shouldSetPriorityId() {
        int newPriorityId = 2;
        project.setPriorityId(newPriorityId);
        assert(project.getPriorityId() == newPriorityId);
    }

    @Test
    public void shouldGetPriorityType() {
        assert(project.getPriorityType().equals(priorityType));
    }

    @Test
    public void shouldSetPriorityType() {
        String newPriorityType = "newType";
        project.setPriorityType(newPriorityType);
        assert(project.getPriorityType().equals(newPriorityType));
    }

    @Test
    public void shouldGetDescription() {
        assert(project.getDescription().equals(description));
    }

    @Test
    public void shouldSetDescription() {
        String newDescription = "newDescription";
        project.setDescription(newDescription);
        assert(project.getDescription().equals(newDescription));
    }

    @Test
    public void shouldGetStartDate() {
        assert(project.getStartDate().equals(startDate));
    }

    @Test
    public void shouldSetStartDate() {
        Date newStartDate = new Date();
        project.setStartDate(newStartDate);
        assert(project.getStartDate().equals(newStartDate));
    }

    @Test
    public void shouldGetEndDate() {
        assert(project.getEndDate().equals(endDate));
    }

    @Test
    public void shouldSetEndDate() {
        Date newEndDate = new Date();
        project.setEndDate(newEndDate);
        assert(project.getEndDate().equals(newEndDate));
    }

    @Test
    public void shouldNotSetEndDateBeforeStartDate() {
        Date newEndDate = new Date(startDate.getTime() - 1);
        project.setEndDate(newEndDate);
        assert(project.getEndDate().equals(endDate));
    }
}
