package com.example.supermarketqueuesimulator;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.text.NumberFormat;
import java.util.Iterator;

public class ServicePoint extends VBox implements Runnable {

    private double money = 0;
    private Customer customer;
    private final CustomerQueue queue;
    private final TotalSum totalSum;
    private final Label deskNumberLabel;
    private final Label moneyLabel;
    private final Label clientLabel;
    private final Label numItemLabel;
    private final NumberFormat defaultFormat;
    private final Button controlButton;
    private int speedMultiplier = 1;
    private boolean isOpen = true;

    public ServicePoint(int number, CustomerQueue queue, TotalSum totalSum) {
        this.queue = queue;
        this.totalSum = totalSum;
        defaultFormat = NumberFormat.getCurrencyInstance();

        setStyle("-fx-background-color: #00FF00;"); // Green (Open)
        setSpacing(10);

        deskNumberLabel = new Label("Service point " + number);
        deskNumberLabel.setTextFill(Color.ORANGE);
        deskNumberLabel.setStyle("-fx-font-weight: bold");
        getChildren().add(deskNumberLabel);

        moneyLabel = new Label(defaultFormat.format(money));
        moneyLabel.setTextFill(Color.WHITE);
        getChildren().add(moneyLabel);

        clientLabel = new Label("Customer?");
        clientLabel.setTextFill(Color.WHITE);
        getChildren().add(clientLabel);

        numItemLabel = new Label("Item: 0");
        numItemLabel.setTextFill(Color.LIGHTGRAY);
        getChildren().add(numItemLabel);

        // Add control button
        controlButton = new Button("Open");
        controlButton.setOnAction(event -> toggleStatus());
        HBox buttonBox = new HBox(controlButton);
        buttonBox.setSpacing(5);
        getChildren().add(buttonBox);
    }


    @Override
    public void run() {
        try {
            Thread.sleep((int) (Math.random() * 10) * 1000);
            while (!Thread.currentThread().isInterrupted()) {
                if (customer == null && isOpen) {
                    customer = queue.callClient();
                    // Wait for the client to arrive
                    Thread.sleep(5000 / speedMultiplier); // Adjust speed here
                    process();
                }
                Thread.sleep(500 / speedMultiplier); // Adjust speed here
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // Log the exception using a logging framework
        }
    }

    private void process() throws InterruptedException {
        double money = 0;
        int countItem = 0;
        double buy = 0;
        Platform.runLater(() -> clientLabel.setText("Customer " + customer.getNumber()));
        for (Iterator<Double> it = customer.getProducts(); it.hasNext(); ) {
            double item = it.next();
            buy = money += item;
            countItem++;
            int finalCountItem = countItem;
            double finalMoney = money;
            Platform.runLater(() -> {
                moneyLabel.setText(defaultFormat.format(finalMoney));
                numItemLabel.setText("Item: " + finalCountItem);
            });
            Thread.sleep(1000 / speedMultiplier); // Adjust speed here
        }
        totalSum.setOperation(buy);
        Platform.runLater(() -> {
            clientLabel.setText("Customer?");
            numItemLabel.setText("Item: 0");
        });
        customer = null;
    }


    private void toggleStatus() {
        isOpen = !isOpen;
        if (isOpen) {
            setStyle("-fx-background-color: green;"); // Green
            controlButton.setText("Open");
        } else {
            setStyle("-fx-background-color: #FF0000;"); // Red
            controlButton.setText("Closed");
        }
    }
}
