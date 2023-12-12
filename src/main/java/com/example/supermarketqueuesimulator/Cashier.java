package com.example.supermarketqueuesimulator;

import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.text.NumberFormat;
import java.util.Iterator;

public class Cashier extends VBox implements Runnable {

    private double money = 0;
    private Client client = null;
    private final ClientQueue queue;
    private final Summary summary;
    private final Label deskNumberLabel;
    private final Label moneyLabel;
    private final Label clientLabel;
    private final Label numItemLabel;
    private final NumberFormat defaultFormat;
    private final String NO_CLIENT = "Customer?";
    private final String NO_ITEM = "Item: 0";

    public Cashier(int number, ClientQueue queue, Summary summary) {
        this.queue = queue;
        this.summary = summary;
        defaultFormat = NumberFormat.getCurrencyInstance();

        setStyle("-fx-background-color: black;");
        setSpacing(10);

        deskNumberLabel = new Label("Service point " + number);
        deskNumberLabel.setTextFill(Color.YELLOW);
        getChildren().add(deskNumberLabel);

        moneyLabel = new Label(defaultFormat.format(money));
        moneyLabel.setTextFill(Color.WHITE);
        getChildren().add(moneyLabel);

        clientLabel = new Label(NO_CLIENT);
        clientLabel.setTextFill(Color.WHITE);
        getChildren().add(clientLabel);

        numItemLabel = new Label(NO_ITEM);
        numItemLabel.setTextFill(Color.LIGHTGRAY);
        getChildren().add(numItemLabel);
    }

    @Override
    public void run() {
        try {
            Thread.sleep((int) (Math.random() * 10) * 1000);
            while (true) {
                if (client == null) {
                    client = queue.callClient();
                    // Wait for the client to arrive
                    Thread.sleep(5000);
                    process();
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void process() throws InterruptedException {
        int countItem = 0;
        double buy = 0;
        Platform.runLater(() -> clientLabel.setText("Customer " + client.getNumber()));
        for (Iterator<Double> it = client.getProducts(); it.hasNext(); ) {
            double item = it.next();
            buy = money += item;
            countItem++;
            int finalCountItem = countItem;
            Platform.runLater(() -> {
                moneyLabel.setText(defaultFormat.format(money));
                numItemLabel.setText("Item: " + finalCountItem);
            });
            Thread.sleep(1000);
        }
        summary.setOperation(buy);
        Platform.runLater(() -> {
            clientLabel.setText(NO_CLIENT);
            numItemLabel.setText(NO_ITEM);
        });
        client = null;
    }
}
