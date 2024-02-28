package modeles;

public class TeamCore {
    private int id_developer;
    private int project_id;

    public TeamCore() {
        // Default constructor required for JSON deserialization
    }

    public TeamCore(int id_developer, int project_id) {
        this.id_developer = id_developer;
        this.project_id = project_id;
    }

    public int getId_developer() {
        return id_developer;
    }

    public void setId_developer(int id_developer) {
        this.id_developer = id_developer;
    }

    public int getId_project() {
        return project_id;
    }

    public void setId_project(int project_id) {
        this.project_id = project_id;
    }
}
