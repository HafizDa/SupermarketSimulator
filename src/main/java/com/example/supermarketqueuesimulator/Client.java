package com.example.supermarketqueuesimulator;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Client extends VBox {

    private final int number;
    private final List<Double> products = new ArrayList<>();

    public Client(int number) {
        this.number = number;
        createListOfProducts();

        setSpacing(5);

        Label clientNumber = new Label("Customer " + number);
        clientNumber.setTextFill(Color.WHITE);
        getChildren().add(clientNumber);

        Label numberOfItems = new Label("Items: " + products.size());
        numberOfItems.setTextFill(Color.WHITE);
        getChildren().add(numberOfItems);

        // Set green background
        setStyle("-fx-background-color: #008000;");  // Green

        setColor(products.size());
    }

    private void createListOfProducts() {
        Random rand = new Random();
        int MAX_ITEMS = 20;
        int MIN_ITEMS = 1;
        double MAX_VALUE = 20;
        double MIN_VALUE = 0.05;
        int random = rand.nextInt((MAX_ITEMS - MIN_ITEMS) + 1) + MIN_ITEMS;

        for (int i = 0; i < random; i++) {
            double value = MIN_VALUE + (MAX_VALUE - MIN_VALUE) * rand.nextDouble();
            products.add(value);
        }
        setColor(products.size());
    }

    public int getNumber() {
        return number;
    }

    public Iterator<Double> getProducts() {
        return products.iterator();
    }

    private void setColor(int size) {
        // Implement setColor method based on your needs
        // If needed, you can customize the colors further for different size ranges


    }

    // Implement the updateUI() method based on your needs
    private void updateUI() {
        // Your implementation for updating the UI goes here
    }
}
