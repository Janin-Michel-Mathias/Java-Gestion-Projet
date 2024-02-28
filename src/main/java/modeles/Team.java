package modeles;

public class Team extends TeamCore{
    private String project_name;
    private String developer_name;

    public Team() {
        // Default constructor required for JSON deserialization
    }

    public Team(int id_developer, int project_id, String project_name, String developer_name) {
        super(id_developer, project_id);
        this.developer_name = developer_name;
        this.project_name = project_name;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getDeveloper_name() {
        return developer_name;
    }

    public void setDeveloper_name(String developer_name) {
        this.developer_name = developer_name;
    }

}
