package com.example.supermarketqueuesimulator;

import javafx.application.Platform;
import javafx.scene.layout.HBox;

import java.util.LinkedList;
import java.util.Queue;

public class CustomerQueue extends HBox implements Runnable {

    private final Queue<Customer> customers = new LinkedList<>();
    private int clientNumber = 10;

    public CustomerQueue() {
        setSpacing(5);

        for (int i = 1; i <= clientNumber; i++) {
            addClient(i);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                int random = (int) (Math.random() * 10);
                Thread.sleep(random * 1000L);
                addClient(++clientNumber);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void addClient(int i) {
        Customer customer = new Customer(i);
        customers.add(customer);
        Platform.runLater(() -> getChildren().add(customer));
        updateUI();
    }

    private Customer removeClient() {
        Customer customer = customers.remove();
        Platform.runLater(() -> getChildren().remove(customer));
        updateUI();
        return customer;
    }

    public synchronized Customer callClient() throws InterruptedException {
        while (customers.isEmpty()) {
            Thread.sleep(900);
        }
        return removeClient();
    }

    private void updateUI() {

        setStyle("-fx-background-color: lightgrey");



    }

}
