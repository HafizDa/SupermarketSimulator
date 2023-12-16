/**
 * Main class to start the supermarket simulation.
 * It initializes the customer queue, market simulation, and the graphical user interface.
 */

package com.supermarket.simulation.main;

import javafx.application.Application;
import javafx.stage.Stage;
import com.supermarket.simulation.core.CustomerQueue;
import com.supermarket.simulation.ui.SuperMarketGui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Main class to start the supermarket simulation.
 * It initializes the customer queue, market simulation, and the graphical user interface.
 */
public class Main extends Application {

    /**
     * The main method that launches the JavaFX application.
     * @param args Command-line arguments.
     */

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method for the JavaFX application.
     * This method initializes the customer queue, market simulation, and the graphical user interface.
     * @param primaryStage The primary stage for this application.
     * @throws IOException If an I/O error occurs.
     */

    @Override
    public void start(Stage primaryStage) throws IOException {

        Connection dbConnection = establishDatabaseConnection();

        CustomerQueue queue = new CustomerQueue(dbConnection);
        new Thread(queue).start();

        MarketSimulation simulation = new MarketSimulation(4, queue, 1, dbConnection);

        SuperMarketGui superMarketGui = new SuperMarketGui("Supermarket Queue Simulator", 4, queue);

        new Thread(simulation).start();
        superMarketGui.start(primaryStage);
    }

    /**
     * Establishes a connection to the database.
     * @return The database connection.
     * @throws RuntimeException If a database access error occurs.
     */
    private Connection establishDatabaseConnection() {
        try {
            String url = "jdbc:mariadb://localhost:3306/supermarket_db";
            String username = "root";
            String password = "2003";
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database");
        }
    }
}
