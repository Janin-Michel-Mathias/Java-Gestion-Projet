package modeles;

import java.util.List;

public class FullProject{
    private Project project;
    private SkillStacks skillStacks;
    private List<Team> team;


    public FullProject() {
        // Default constructor required for JSON deserialization
    }

    public FullProject(Project project, SkillStacks skillStacks, List<Team> team) {
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

    public SkillStacks getSkillStacks() {
        return skillStacks;
    }

    public void setSkillStacks(SkillStacks skillStacks) {
        this.skillStacks = skillStacks;
    }

}
