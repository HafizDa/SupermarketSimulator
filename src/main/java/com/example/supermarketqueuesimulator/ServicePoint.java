/**
 * Represents a service point in the supermarket where customers are served.
 * Each service point has an associated queue and serves customers in a FIFO manner.
 */

package com.example.supermarketqueuesimulator;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.text.NumberFormat;
import java.util.Iterator;

/**
 * Represents a service point in the supermarket where customers are served.
 * Each service point has an associated queue and serves customers in a FIFO manner.
 */
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
    private final Button plusButton;
    private final Button minusButton;
    private int speedMultiplier = 1;
    private boolean isOpen = true;

    /**
     * Constructor for the ServicePoint class.
     * @param number The number of the service point.
     * @param queue The queue associated with the service point.
     * @param totalSum The total sum of the money spent by the customers.
     */
    public ServicePoint(int number, CustomerQueue queue, TotalSum totalSum) {
        this.queue = queue;
        this.totalSum = totalSum;
        defaultFormat = NumberFormat.getCurrencyInstance();

        setStyle("-fx-background-color: #00FF00;"); // Green (Open)
        setSpacing(10);

        deskNumberLabel = new Label("Service point " + number);
        deskNumberLabel.setTextFill(Color.BLACK);
        deskNumberLabel.setStyle("-fx-font-weight: bold");
        deskNumberLabel.setStyle("-fx-background-color: Orange; -fx-border-color: black; -fx-border-width: 2px; -fx-padding: 5px; -fx-spacing: 5px; -fx-alignment: center;");
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

        controlButton = new Button("Open");
        controlButton.setOnAction(event -> toggleStatus());

        plusButton = new Button("+");
        minusButton = new Button("-");
        HBox buttonBox = new HBox(controlButton, plusButton, minusButton);
        buttonBox.setSpacing(5);
        getChildren().add(buttonBox);

        plusButton.setOnAction(event -> increaseSpeed());
        minusButton.setOnAction(event -> decreaseSpeed());
    }

    /**
     * The run method for the Runnable interface.
     * Continuously serves customers from the queue if the service point is open.
     */
    @Override
    public void run() {
        try {
            Thread.sleep((int) (Math.random() * 10) * 1000);
            while (!Thread.currentThread().isInterrupted()) {
                if (customer == null && isOpen) {
                    // Customer arrival event
                    System.out.println("Time: "+ Clock.getInstance().getClock() + " New customer arrived at  " + deskNumberLabel.getText());
                    customer = queue.callClient();
                    Thread.sleep(5000 / speedMultiplier); // Wait for the client to arrive
                    // Starting new service event
                    System.out.println("Time: " + Clock.getInstance().getClock() + " Starting new service for Customer " + customer.getNumber() + " at " + deskNumberLabel.getText());
                    process();
                }
                Thread.sleep(500 / speedMultiplier);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    /**
     * Processes the items of the current customer.
     * @throws InterruptedException If the thread is interrupted.
     */
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

            Thread.sleep(1000 / speedMultiplier); // This will adjust the speed of the service point
        }

        // Departure event
        System.out.println(" - Customer " + customer.getNumber() + " departed from  " + deskNumberLabel.getText());
        System.out.println(" - Customer " + customer.getNumber() + " spent " + defaultFormat.format(money) + " at  " + deskNumberLabel.getText());

        double exitTime = Clock.getInstance().getClock();
        double waitingTime = exitTime - customer.getArrivalTime();
        double servingTime = exitTime - customer.getEntryTime();

        totalSum.setOperation(buy, waitingTime, servingTime);

        Platform.runLater(() -> {
            clientLabel.setText("Customer?");
            numItemLabel.setText("Item: 0");
        });
        customer = null;
    }

    /**
     * Toggles the open/close status of the service point.
     */
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

    /**
     * Increases the processing speed of the service point.
     */
    private void increaseSpeed() {
        speedMultiplier++;
    }
    /**
     * Decreases the processing speed of the service point.
     */

    private void decreaseSpeed() {
        if (speedMultiplier > 1) {
            speedMultiplier--;
        }
    }

    /**
     * Adds a customer to the service point.
     * @param  customer The customer to be added.
     */

    public void addCustomer(Customer customer) {
        this.customer = customer;
    }
}
