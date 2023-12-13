package com.example.supermarketqueuesimulator;

import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Customer extends VBox {

    private final int number;
    private final List<Double> products = new ArrayList<>();

    public Customer(int number) {
        this.number = number;
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

        // Set green background
        setStyle("-fx-background-color: #DC3030");  // Anger

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



}
