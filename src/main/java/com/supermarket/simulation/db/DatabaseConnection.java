/**
 * This class is used to manage the database connection.
 * It provides methods to establish and close the connection to the database.
 */
package com.supermarket.simulation.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is used to manage the database connection.
 * It provides methods to establish and close the connection to the database.
 */
public class DatabaseConnection {
    /**
     * The URL of the database.
     */
    private static final String URL = "jdbc:mariadb://localhost:3306/supermarket_db";

    /**
     * The username for the database connection.
     */
    private static final String USER = "root";

    /**
     * The password for the database connection.
     */
    private static final String PASSWORD = "2003";

    /**
     * Establishes a connection to the database.
     * @return A Connection object representing the database connection.
     * @throws SQLException If a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Closes the provided database connection.
     * @param connection The Connection object to be closed.
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}