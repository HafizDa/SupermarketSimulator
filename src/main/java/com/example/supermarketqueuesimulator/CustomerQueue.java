package com.example.supermarketqueuesimulator;

import javafx.application.Platform;
import javafx.scene.layout.HBox;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerQueue extends HBox implements Runnable {

    private final Queue<Customer> customers = new LinkedList<>();
    private int clientNumber = 10;
    private final Logger logger = Logger.getLogger(CustomerQueue.class.getName());



    public CustomerQueue() {
        setSpacing(5);

        for (int i = 1; i <= clientNumber; i++) {
            addClient(i);
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                int random = (int) (Math.random() * 10);
                Thread.sleep(random * 1000L);
                addClient(++clientNumber);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.log(Level.SEVERE, "Thread was interrupted", e);
        }
    }

    void addClient(int i) {
        Customer customer = new Customer(i);
        customers.add(customer);
        Platform.runLater(() -> {
            getChildren().add(customer);
            updateUI();
        });
    }

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

    public synchronized Customer callClient() throws InterruptedException {
        while (customers.isEmpty() && !Thread.currentThread().isInterrupted()) {
            Thread.sleep(900);
        }
        return removeClient();
    }

    private void updateUI() {
        setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px; -fx-padding: 5px; -fx-spacing: 5px; -fx-alignment: center;");
    }



}