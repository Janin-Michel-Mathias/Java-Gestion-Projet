package modeles;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SkillExperience {
    private String skill;

    private int experience;
    private String rank;
    private int rankNb;

    @JsonCreator
    public SkillExperience(
            @JsonProperty("skill") String skill,
            @JsonProperty("experience") int experience,
            @JsonProperty("level") String rank,
            @JsonProperty("levelNb") int rankNb){
        this.skill = skill;
        this.experience = experience;
        this.rank = rank;
        this.rankNb = rankNb;
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

    public int getRankNb() {
        return rankNb;
    }
    public void setRankNb(int rankNb) {
        this.rankNb = rankNb;
    }

    // Other methods
}