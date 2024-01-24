package modeles;

public class Level {
    private int id;
    private String levelName;

    public Level() {
        // Constructeur par défaut requis pour la désérialisation JSON
    }

    public Level(String levelName) {
        this.levelName = levelName;
    }

    public Level(int id, String levelName) {
        this.id = id;
        this.levelName = levelName;
    }

    public int getId() {
        return id;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
