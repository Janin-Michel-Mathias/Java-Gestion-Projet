package modeles;

public class Level {
    private int id;
    private String level;

    public Level() {
        // Constructeur par défaut requis pour la désérialisation JSON
    }

    public Level(String level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
