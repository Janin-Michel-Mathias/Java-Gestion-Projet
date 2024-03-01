package GlobalVariables;

import java.sql.Connection;

public class EnvironmentVariable {
    static String DATABASE_URL = "jdbc:sqlite:src/main/resources/bdd_SQLlite.db";
    static Connection connection;
    public static String getDatabaseUrl() {
        return DATABASE_URL;
    }
    public static Connection getConnection() {
        return connection;
    }
    public static void setConnection(Connection connection) {
        EnvironmentVariable.connection = connection;
    }
}