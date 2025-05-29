package main.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("H2 driver not found", e);
        }
    }
    private static final String URL = "jdbc:h2:mem:voting;DB_CLOSE_DELAY=-1";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, "sa", "");
    }
}