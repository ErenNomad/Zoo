package org.example.DataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataBaseConnection {
    private static final String url = "jdbc:postgresql://localhost:5433/Zoo";
    private static final String user = "postgres";
    private static final String password = "1903";
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            //System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println("Error message: " + e.getMessage());
        }
        return conn;
    }


}
