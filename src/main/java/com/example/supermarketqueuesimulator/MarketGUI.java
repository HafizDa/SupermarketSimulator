package com.example.supermarketqueuesimulator;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MarketGUI extends Application implements Runnable {

    private final String name;
    private final int desks;
    private final ClientQueue queue;

    public MarketGUI(String name, int desks, ClientQueue queue) {
        this.name = name;
        this.desks = desks;
        this.queue = queue;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(name);
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 650, 250);
        primaryStage.setScene(scene);

        // Set background color for the whole scene
        scene.getRoot().setStyle("-fx-background-color: #008000;");  // Green

        Summary summary = new Summary();
        borderPane.setTop(summary);

        javafx.scene.layout.HBox hboxDesks = new javafx.scene.layout.HBox();
        borderPane.setCenter(hboxDesks);

        // Set background color and spacing for desks area
        hboxDesks.setStyle("-fx-background-color: #800080;");  // Purple
        hboxDesks.setSpacing(10);

        Cashier cashier;
        for (int i = 1; i <= desks; i++) {
            cashier = new Cashier(i, queue, summary);

            // Set background color for each cashier
            cashier.setStyle("-fx-background-color: #FFFFF;"); // White

            hboxDesks.getChildren().add(cashier);
            new Thread(cashier).start();
        }

        // Set background color for the queue area
        borderPane.setBottom(queue);
        queue.setStyle("-fx-background-color: #FF6347;");  // Anger

        primaryStage.show();
    }

    @Override
    public void run() {
        launch();
    }



}
