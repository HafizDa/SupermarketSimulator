/**
 * Represents a customer in the supermarket simulation.
 * Each customer has a unique identifier, arrival time, and number of items in their cart.
 */
package com.supermarket.simulation.core;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Represents a customer in the supermarket simulation.
 * Each customer has a unique identifier, arrival time, and number of items in their cart.
 */
public class Customer extends VBox {

    private final int number;
    private final double arrivalTime;
    private final List<Double> products;

    /**
     * Constructor for the Customer class.
     * @param number The unique identifier for the customer.
     */
    public Customer(int number) {
        this.number = number;
        this.arrivalTime = System.currentTimeMillis();
        this.products = new ArrayList<>();
        createListOfProducts();

        setSpacing(5);

        Label clientNumber = new Label("Customer " + number);
        clientNumber.setTextFill(Color.BLACK);
        clientNumber.setStyle("-fx-font-weight: bold");
        clientNumber.setStyle("-fx-font-size: 14");
        getChildren().add(clientNumber);

        Label numberOfItems = new Label("Items: " + products.size());
        numberOfItems.setTextFill(Color.BLACK);
        getChildren().add(numberOfItems);

        setStyle("-fx-background-color: #DC3030");  // Anger
        setColor(products.size());

        // Insert customer data into the database
        insertCustomerData();
    }



    /**
     * Method to insert customer data into the database.
     * Currently empty and needs to be implemented.
     */
    void insertCustomerData() {
    }

    /**
     * Method to create a list of products for the customer.
     * The number of products is randomly generated.
     */
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

    /**
     * Method to set the color of the customer based on the number of items they have.
     * @param size The number of items the customer has.
     */
    private void setColor(int size) {
        switch (size) {
            case 1:
                setStyle("-fx-background-color: #FF0000");  // Red
                break;
            case 2:
                setStyle("-fx-background-color: #FF4500");  // Orange
                break;
            case 3:
                setStyle("-fx-background-color: #FFA500");  // Orange
                break;
            case 4:
                setStyle("-fx-background-color: #FFFF00");  // Yellow
                break;
            case 5:
                setStyle("-fx-background-color: #ADFF2F");  // GreenYellow
                break;
            case 6:
                setStyle("-fx-background-color: #00FF00");  // Green
                break;
            case 7:
                setStyle("-fx-background-color: #00FA9A");  // MediumSpringGreen
                break;
            case 8:
                setStyle("-fx-background-color: #00BFFF");  // DeepSkyBlue
                break;
            case 9:
                setStyle("-fx-background-color: #1E90FF");  // DodgerBlue
                break;
            case 10:
                setStyle("-fx-background-color: #0000FF");  // Blue
                break;
            case 11:
                setStyle("-fx-background-color: #8A2BE2");  // BlueViolet
                break;
            case 12:
                setStyle("-fx-background-color: #FF00FF");  // Magenta
                break;
            case 13:
                setStyle("-fx-background-color: #FF1493");  // DeepPink
                break;
            case 14:
                setStyle("-fx-background-color: #FF69B4");  // HotPink
                break;
            case 15:
                setStyle("-fx-background-color: #FFC0CB");  // Pink
                break;
            case 16:
                setStyle("-fx-background-color: #FFD700");  // Gold
                break;
            case 17:
                setStyle("-fx-background-color: #FF8C00");  // DarkOrange
                break;
            case 18:
                setStyle("-fx-background-color: #FF4500");  // OrangeRed
                break;
            case 19:
                setStyle("-fx-background-color: #FF0000");  // Red


        }
        }

    /**
     * Method to get the arrival time of the customer.
     * @return The arrival time of the customer.
     */
    public double getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Method to get the entry time of the customer.
     * @return The entry time of the customer.
     */
    public double getEntryTime() {
        return arrivalTime;
    }

    /**
     * Method to get the number of items the customer has.
     * @return The number of items the customer has.
     */
    public int getNumberOfItems() {
        return products.size();
    }

    /**
     * Method to get the customer number.
     * @return The customer number.
     */
    public String getCustomerNumber() {
        return "Customer " + number;
    }

    /**
     * Method to print customer data from the database.
     */
    public static void printCustomerData() {
        // Implementation of printCustomerData method
    }

    /**
     * Method to get the customer number.
     * @return The customer number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Method to get an iterator for the products the customer has.
     * @return An iterator for the products.
     */
    public Iterator<Double> getProducts() {
        return products.iterator();
    }



}