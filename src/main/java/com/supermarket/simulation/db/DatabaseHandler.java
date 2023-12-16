/**
 * This class is used to connect to the database and show the tables in the database.
 * It is used to test the connection to the database.
 * It is not used in the actual program.
 */
package com.supermarket.simulation.db;

import java.sql.*;

/**
 * This class is used to connect to the database and show the tables in the database.
 * It is used to test the connection to the database.
 * It is not used in the actual program.
 */
public class DatabaseHandler {

    /**
     * The main method for testing the database connection.
     * It calls the showTables method to display the tables in the database.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        showTables();
    }

    /**
     * Connects to the database and prints the names of all tables.
     * This method is used to test the database connection.
     */
    public static void showTables() {
        try {
            // JDBC connection parameters (adjust based on your setup)
            String url = "jdbc:mariadb://localhost:3306/supermarket_db";
            String username = "root";
            String password = "2003";

            // Establishing a connection
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                // SQL query to show tables
                String query = "SHOW TABLES FROM supermarket_db";
                try (Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        String tableName = resultSet.getString(1);
                        System.out.println("Table: " + tableName);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error executing query: " + e.getMessage());
        }
    }
}