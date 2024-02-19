package modeles;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SkillExperience {
    private String skill;

    private int experience;

    private String rank;

    @JsonCreator
    public SkillExperience(
            @JsonProperty("skill") String skill,
            @JsonProperty("experience") int experience,
            @JsonProperty("level") String rank) {
        this.skill = skill;
        this.experience = experience;
        this.rank = rank;
    }

    public SkillExperience() {

    }

    // Getter and Setter methods

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }

    // Other methods
}