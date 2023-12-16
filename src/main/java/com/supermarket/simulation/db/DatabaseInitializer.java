/*
 * This class is used to initialize the database.
 * It creates the 'customer' table if it doesn't exist.
 * The 'customer' table has the following columns:
 * - id: INT, AUTO_INCREMENT, PRIMARY KEY
 * - number: INT, NOT NULL
 * - arrivalTime: DOUBLE, NOT NULL
 * - numberOfItems: INT, NOT NULL
 */
package com.supermarket.simulation.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is used to initialize the database.
 * It creates the 'customer' table if it doesn't exist.
 * The 'customer' table has the following columns:
 * - id: INT, AUTO_INCREMENT, PRIMARY KEY
 * - number: INT, NOT NULL
 * - arrivalTime: DOUBLE, NOT NULL
 * - numberOfItems: INT, NOT NULL
 */

public class DatabaseInitializer {

    /**
     * The JDBC URL of the MySQL server.
     */
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/supermarket_db";

    /**
     * The username for the database connection.
     */
    private static final String USER = "root";

    /**
     * The password for the database connection.
     */
    private static final String PASSWORD = "2003";


    /**
     * The main method for the DatabaseInitializer class.
     * It registers the JDBC driver, establishes a connection to the database,
     * and creates the 'customer' table if it doesn't exist.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        try {
            // Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            // Open a connection
            try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
                // Create the 'customer' table if it doesn't exist
                createCustomerTable(connection);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Creates the 'customer' table in the database if it doesn't exist.
     * The 'customer' table has the following columns:
     * - id: INT, AUTO_INCREMENT, PRIMARY KEY
     * - number: INT, NOT NULL
     * - arrivalTime: DOUBLE, NOT NULL
     * - numberOfItems: INT, NOT NULL
     * @param connection The Connection object for the database connection.
     */

    private static void createCustomerTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            // Your table creation SQL
            String createTableSQL = "CREATE TABLE IF NOT EXISTS customer ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "number INT NOT NULL,"
                    + "arrivalTime DOUBLE NOT NULL,"
                    + "numberOfItems INT NOT NULL),";
            statement.executeUpdate(createTableSQL);
            System.out.println("Table 'customer' created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating 'customer' table:");
            e.printStackTrace();
        }
    }
}
