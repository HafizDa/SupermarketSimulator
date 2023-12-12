package com.example.supermarketqueuesimulator;

import javafx.application.Platform;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.text.NumberFormat;

import javafx.scene.layout.BackgroundFill;

public class Summary extends VBox {

    private double total = 0;
    private int numClients = 0;
    private final String AVERAGE = "Average by customer: ";
    private final String TOTAL = "Total: ";
    private final String CLIENTS = "Number of customers: ";
    private final Label totalLabel;
    private final Label averageLabel;
    private final Label numClientsLabel;
    private final NumberFormat defaultFormat;

    public Summary() {
        defaultFormat = NumberFormat.getCurrencyInstance();

        setBackground(new Background(new BackgroundFill(Color.web("#DC3030"), null, null))); // Green

        setSpacing(10);
        setPadding(new javafx.geometry.Insets(10));

        totalLabel = new Label(TOTAL);
        totalLabel.setTextFill(Color.WHITE);
        getChildren().add(totalLabel);

        averageLabel = new Label(AVERAGE);
        averageLabel.setTextFill(Color.WHITE);
        getChildren().add(averageLabel);

        numClientsLabel = new Label(CLIENTS);
        numClientsLabel.setTextFill(Color.WHITE);
        getChildren().add(numClientsLabel);
    }

    public synchronized void setOperation(double value) {
        numClients++;
        total += value;
        Platform.runLater(() -> {
            totalLabel.setText(TOTAL + defaultFormat.format(total));
            averageLabel.setText(AVERAGE + defaultFormat.format(total / numClients));
            numClientsLabel.setText(CLIENTS + numClients);
        });
        updateUI();
    }

    private void updateUI() {
        // Your implementation for updating the UI goes here
        // You can add more UI updates as needed
    }
}
