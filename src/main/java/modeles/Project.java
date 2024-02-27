package modeles;

import java.util.Date;

public class Project {
    private int id;
    private String name;
    private int priorityId;
    private String priorityType;
    private String description;
    private Date startDate;
    private Date endDate;

    public Project() {
        // Constructeur par défaut
    }

    public Project(int id, String name, int priorityId, String priorityType, String description, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.priorityId = priorityId;
        this.priorityType = priorityType;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getter et Setter pour chaque attribut

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

    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    public String getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(String priorityType) {
        this.priorityType = priorityType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
