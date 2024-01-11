package modeles;

import java.util.HashMap;
import java.util.Map;

public class Developer {
    private int id;
    private String name;
    private String email;
    private Map<String, String> skills; // Compétences avec leurs niveaux techniques

    public Developer(String name, String email) {
        this.name = name;
        this.email = email;
        this.skills = new HashMap<>(); // Initialisation de la map
    }

    // ... (Getters et Setters pour id, name, email)

    // Méthode pour ajouter une compétence avec un niveau technique pour ce développeur
    public void addSkill(String skillName, String level) {
        skills.put(skillName, level);
    }

    // Méthode pour obtenir le niveau technique d'une compétence spécifique pour ce développeur
    public String getSkillLevel(String skillName) {
        return skills.get(skillName);
    }
}
