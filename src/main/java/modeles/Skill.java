package modeles;

public class Skill {
    private int id;
    private String skillName;

    public Skill() {
        // Constructeur par défaut requis pour la désérialisation JSON
    }

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

}