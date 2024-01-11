package GlobalVariables;

public class EnvironmentVariable {
    static String DATABASE_URL = "jdbc:sqlite:src/main/resources/bdd_SQLlite.db";

    public static String getDatabaseUrl() {
        return DATABASE_URL;
    }
}