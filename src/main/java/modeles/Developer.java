package modeles;

import modeles.Skill;

import java.util.List;

public class Developer {
    private int id;
    private String name;
    private String email;
    private List<Skill> skills; // Liste des compétences du développeur

    public Developer() {
        // Constructeur par défaut requis pour la désérialisation JSON
    }

    public Developer(String name, String email, List<Skill> skills) {
        this.name = name;
        this.email = email;
        this.skills = skills;
    }

    public Developer(int developerId, Object o, Object o1, List<Skill> updatedSkills) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
