/*
 * This class is used to display the total sum of the money spent by the customers in the supermarket.
 * It also displays the average payment by customer and the total number of customers served.
 */
package com.supermarket.simulation.main;

import javafx.application.Platform;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import com.supermarket.simulation.core.CustomerQueue;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


/**
 * This class is used to display the total sum of the money spent by the customers in the supermarket.
 * It also displays the average payment by customer and the total number of customers served.
 */
public class TotalSum extends VBox {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private double total = 0;
    private int numClients = 0;
    private final String AVERAGE = "Avg Payment By Customer: ";
    private final String TOTAL = "Total Payment: ";
    private final String CLIENTS = "Total Customers Served: ";
    private final Label totalLabel;
    private final Label averageLabel;
    private final Label numClientsLabel;
    private final NumberFormat defaultFormat;
    private final CustomerQueue queue;

    /**
     * Constructor for the TotalSum class.
     * @param queue The queue of customers in the supermarket.
     */
    public TotalSum(CustomerQueue queue) {
        this.setBackground(new Background(new BackgroundFill(Color.web("#222222"), null, null))); // Green
        this.setSpacing(10);
        this.setPadding(new javafx.geometry.Insets(10));

        totalLabel = new Label(TOTAL);
        totalLabel.setTextFill(Color.WHITE);
        this.getChildren().add(totalLabel);

        averageLabel = new Label(AVERAGE);
        averageLabel.setTextFill(Color.WHITE);
        this.getChildren().add(averageLabel);

        numClientsLabel = new Label(CLIENTS);
        numClientsLabel.setTextFill(Color.WHITE);
        this.getChildren().add(numClientsLabel);

        defaultFormat = NumberFormat.getCurrencyInstance();
        this.queue = queue;
    }
    /**
     * Updates the total sum and average payment by customer.
     * @param value The amount of money spent by the customer.
     */
    public synchronized void setOperation(double value) {
        numClients++;
        total += value;

        long arrivalTimeMillis = System.currentTimeMillis();
        double arrivalTimeSeconds = arrivalTimeMillis / 1000.0;
        String formattedArrivalTime = formatTimestamp(arrivalTimeMillis);

        logEvent("ARRIVAL time: New customer arrived at " + formattedArrivalTime);
        logEvent("Adding to event list ARRIVAL time: " + formattedArrivalTime);
        logEvent("Starting new service for customer");

        Platform.runLater(() -> {
            totalLabel.setText(TOTAL + formatCurrency(total));
            averageLabel.setText(AVERAGE + formatCurrency(total / numClients));
            numClientsLabel.setText(CLIENTS + numClients);
        });

        updateUI();
    }

    /**
     * Formats a timestamp into a string.
     * @param timestamp The timestamp to be formatted.
     * @return The formatted timestamp.
     */
    private String formatTimestamp(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()).format(formatter);
    }
    /**
     * Formats a currency value into a string.
     * @param value The currency value to be formatted.
     * @return The formatted currency value.
     */
    private String formatCurrency(double value) {
        return String.format("$%.2f", value);
    }

    /**
     * Logs an event with a timestamp.
     * @param message The message to be logged.
     */
    private void logEvent(String message) {
        long currentTimeMillis = System.currentTimeMillis();
        double currentTimeSeconds = currentTimeMillis / 1000.0;
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String formattedTime = decimalFormat.format(currentTimeSeconds);
        System.out.println("Time: " + formattedTime + " - " + message);
    }

    /**
     * Updates the user interface.
     */

    private void updateUI() {
        queue.updateUI();
    }

    /**
     * Updates the total sum, average payment by customer, and logs the departure time.
     * @param buy The amount of money spent by the customer.
     * @param waitingTime The waiting time of the customer.
     * @param servingTime The serving time of the customer.
     */
    public void setOperation(double buy, double waitingTime, double servingTime) {

        setOperation(buy);
        logEvent("DEPARTURE time: Customer removed at " + formatTimestamp(System.currentTimeMillis()));



    }
}
