package modeles;

public class SkillStacks extends Skill {

    private String projectName;
    private int numberOfDevelopers;

    public SkillStacks() {
        // Default constructor required for JSON deserialization
    }

    public SkillStacks(String skillName, String projectName , int numberOfDevelopers) {
        super(skillName);
        this.projectName = projectName;
        this.numberOfDevelopers = numberOfDevelopers;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getNumberOfDevelopers() {
        return numberOfDevelopers;
    }

    public void setNumberOfDevelopers(int numberOfDevelopers) {
        this.numberOfDevelopers = numberOfDevelopers;
    }
}
