package com.example.supermarketqueuesimulator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SuperMarketGui extends Application implements Runnable {

    private final String name;
    private final int desks;
    private final CustomerQueue queue;

    public SuperMarketGui(String name, int desks, CustomerQueue queue) {
        this.name = name;
        this.desks = desks;
        this.queue = queue;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(name);
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 650, 350);
        primaryStage.setScene(scene);

        // Set background color for the whole scene
        scene.getRoot().setStyle("-fx-background-color: #008000;");  // Green

        TotalSum totalSum = new TotalSum( queue);
        borderPane.setTop(totalSum);

        javafx.scene.layout.HBox hboxDesks = new javafx.scene.layout.HBox();
        borderPane.setCenter(hboxDesks);

        // Set background color and spacing for desks area
        hboxDesks.setStyle("-fx-background-color: #7db6c6");  // Blue
        hboxDesks.setSpacing(10);


        ServicePoint servicePoint;
        for (int i = 1; i <= desks; i++) {
            servicePoint = new ServicePoint(i, queue, totalSum);

            // Set background color for each cashier
            servicePoint.setStyle("-fx-background-color: Green; -fx-border-color: black; -fx-border-width: 1px; -fx-padding: 5px; -fx-spacing: 5px; ");



            hboxDesks.getChildren().add(servicePoint);
            new Thread(servicePoint).start();
        }

        // Set background color for the queue area
        borderPane.setBottom(queue);
        queue.setStyle("-fx-background-color: #222222;");  // Grey

        primaryStage.show();
    }

    @Override
    public void run() {
        launch();
    }
}


