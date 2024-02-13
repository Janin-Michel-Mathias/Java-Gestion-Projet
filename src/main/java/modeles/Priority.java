package modeles;

public class Priority {
    private int id;
    private String type;

    public Priority() {

    }

    public Priority(String type) {
        this.type = type;
    }

    // getters et setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
