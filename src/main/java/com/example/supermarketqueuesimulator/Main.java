package com.example.supermarketqueuesimulator;

import javafx.application.Application;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(javafx.stage.Stage primaryStage) {
        CustomerQueue queue = new CustomerQueue();
        new Thread(queue).start();

        SuperMarketGui superMarketGui = new SuperMarketGui("Supermarket Queue Simulator", 4, queue);

        superMarketGui.start(primaryStage);
    }
}