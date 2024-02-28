package modeles;

import java.util.List;

public class FullProject{
    private Project project;
    private List<SkillStacks> skillStacks;
    private List<Team> team;


    public FullProject() {
        // Default constructor required for JSON deserialization
    }

    public FullProject(Project project, List<SkillStacks> skillStacks, List<Team> team) {
        this.project = project;
        this.skillStacks = skillStacks;
        this.team = team;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Team> getTeam() {
        return team;
    }

    public void setTeam(List<Team> team) {
        this.team = team;
    }

    public List<SkillStacks> getSkillStacks() {
        return skillStacks;
    }

    public void setSkillStacks(List<SkillStacks> skillStacks) {
        this.skillStacks = skillStacks;
    }

}
