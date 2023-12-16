/**
 * This class is used to insert data into the customer table in the database.
 * It contains a method that takes in the customer number, total spent and number of items
 * and inserts them into the customer table.
 */
package com.supermarket.simulation.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class is used to insert data into the customer table in the database.
 * It contains a method that takes in the customer number, total spent and number of items
 * and inserts them into the customer table.
 */
public class CustomerDAO {

    /**
     * Inserts a new customer into the customer table in the database.
     * @param customerNumber The unique identifier of the customer.
     * @param totalSpent The total amount of money spent by the customer.
     * @param numberOfItems The number of items purchased by the customer.
     */
    public static void insertCustomer(int customerNumber, double totalSpent, int numberOfItems) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO customer (customer_number, total_spent, number_of_items) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, customerNumber);
                statement.setDouble(2, totalSpent);
                statement.setInt(3, numberOfItems);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}