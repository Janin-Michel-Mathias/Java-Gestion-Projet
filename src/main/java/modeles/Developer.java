package modeles;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Developer {
    private int id;
    private String name;
    private String email;
    private Date startDate;
    private Date endDate;
    private List<SkillExperience> skills;

    @JsonCreator
    public Developer(
            @JsonProperty("id") int id,
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("startDate") Date startDate,
            @JsonProperty("endDate") Date endDate,
            @JsonProperty("skills") List<SkillExperience> skills) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
        this.skills = skills;
    }

    public Developer() {

    }

    // Getter and Setter methods

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<SkillExperience> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillExperience> skills) {
        this.skills = skills;
    }

    // Other methods
}
