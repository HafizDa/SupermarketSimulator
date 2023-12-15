/**
 * Represents a queue of customers in the supermarket simulation.
 * Customers are added to this queue for processing by service points.
 */
package com.example.supermarketqueuesimulator;

import javafx.application.Platform;
import javafx.scene.layout.HBox;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a queue of customers in the supermarket simulation.
 * Customers are added to this queue for processing by service points.
 */
public class CustomerQueue extends HBox implements Runnable {

    private final Queue<Customer> customers = new LinkedList<>();
    private int clientNumber = 10;
    private final Logger logger = Logger.getLogger(CustomerQueue.class.getName());
    /**
     * Constructor for the CustomerQueue class.
     * @param dbConnection The database connection.
     */
    public CustomerQueue(Connection dbConnection) {
        setSpacing(5);

        for (int i = 1; i <= clientNumber; i++) {
            addClient(i);
        }
    }

    /**
     * The run method for the Runnable interface.
     * Continuously adds customers to the queue and inserts their data into the database.
     */

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                int random = (int) (Math.random() * 10);
                Thread.sleep(random * 1000L);
                addClient(++clientNumber);

                // This will insert customer data into the database
                customers.peek().insertCustomerData();

                // This will print customer data from the database
                printCustomerData();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.log(Level.SEVERE, "Thread was interrupted", e);
        }
    }

    /**
     * The run method for the Runnable interface.
     * Continuously adds customers to the queue and inserts their data into the database.
     */
    void addClient(int i) {
        Customer customer = new Customer(i);
        customers.add(customer);
        Platform.runLater(() -> {
            getChildren().add(customer);
            updateUI();
        });
    }

    /**
     * Removes a customer from the queue.
     * @return The removed customer.
     */
    private synchronized Customer removeClient() {
        Customer customer = customers.remove();
        Platform.runLater(() -> {
            if (!getChildren().isEmpty()) {
                getChildren().remove(customer);
                updateUI();
            }
        });
        return customer;
    }

    /**
     * Calls a customer from the queue.
     * @return The called customer.
     * @throws InterruptedException If the thread is interrupted.
     */
    public synchronized Customer callClient() throws InterruptedException {
        while (customers.isEmpty() && !Thread.currentThread().isInterrupted()) {
            Thread.sleep(900);
        }

        // Record arrival time
        double arrivalTime = Clock.getInstance().getClock();

        Customer customer = removeClient();

        // Record departure time
        double departureTime = Clock.getInstance().getClock();

        // Calculate waiting time
        double waitTime = departureTime - arrivalTime;

        // Accumulate total wait time
        double totalWaitTime = getTotalWaitTime() + waitTime;


        // Log: New customer arrived at time
        System.out.println("New customer arrived at #" + arrivalTime);

        // Log: Adding to event list ARRIVAL time
        System.out.println("Adding to event list ARRIVAL at #: " + arrivalTime);

        // Log: Starting new service for customer
        System.out.println("Starting new service for customer:  #" + customer.getNumber());

        // Log: Adding to event list DEPARTURE time
        System.out.println("Adding to event list DEPARTURE at: #" + departureTime);

        // Log: Customer removed at time
        System.out.println("Customer removed at: #" + departureTime);

        System.out.println();

        return customer;
    }

    void updateUI() {
        setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px; -fx-padding: 5px; -fx-spacing: 5px; -fx-alignment: center;");
    }
    /**
     * Gets the customer number.
     * @return The customer number.
     */
    public int getCustomerNumber() {
        return clientNumber;
    }


    /**
     * Gets the total wait time.
     * @return The total wait time.
     */
    public double getTotalWaitTime() {
        // Total wait time in the queue
        double totalWaitTime = 0;
        return totalWaitTime;
    }

    /**
     * Prints customer data from the database.
     */
    private void printCustomerData() {
        Customer.printCustomerData();
    }


}
