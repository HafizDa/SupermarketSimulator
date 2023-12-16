/**
 * This class is used to configure the database connection.
 * It contains the URL, USER and PASSWORD of the database.
 * It also contains a main method to test the connection by showing the tables in the database.
 */
package com.supermarket.simulation.db;

/**
 * This class is used to configure the database connection.
 * It contains the URL, USER and PASSWORD of the database.
 * It also contains a main method to test the connection by showing the tables in the database.
 */
public class DatabaseConfig {
    /**
     * The URL of the database.
     */
    static final String URL = "jdbc:mariadb://localhost:3306/supermarket_db";

    /**
     * The username for the database connection.
     */
    static final String USER = "root";

    /**
     * The password for the database connection.
     */
    static final String PASSWORD = "2003";

    /**
     * The main method for testing the database connection.
     * It calls the showTables method of the DatabaseHandler class to display the tables in the database.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        DatabaseHandler.showTables();
    }
}