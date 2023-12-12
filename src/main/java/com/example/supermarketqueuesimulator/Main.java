package com.example.supermarketqueuesimulator;

import javafx.application.Application;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(javafx.stage.Stage primaryStage) throws IOException {
        ClientQueue queue = new ClientQueue();
        new Thread(queue).start();

        MarketGUI marketGUI = new MarketGUI("Lidl Queueing Simulator", 4, queue);
        new Thread(marketGUI).start();
        marketGUI.start(primaryStage);
    }
}
