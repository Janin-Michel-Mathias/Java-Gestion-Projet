package modeles;

public class Skill {
    private int id;
    private String skillName;
    private String level;

    public Skill() {
        // Constructeur par défaut requis pour la désérialisation JSON
    }

    public Skill(String skillName, String level) {
        this.skillName = skillName;
        this.level = level;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
