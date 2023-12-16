/**
 * Represents the graphical user interface for the supermarket simulation.
 * This class displays the simulation components and allows user interaction.
 */

package com.supermarket.simulation.ui;

import com.supermarket.simulation.service.ServicePoint;
import com.supermarket.simulation.main.TotalSum;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.supermarket.simulation.core.CustomerQueue;

/**
 * Represents the graphical user interface for the supermarket simulation.
 * This class displays the simulation components and allows user interaction.
 */
public class SuperMarketGui extends Application implements Runnable {

    private final String name;
    private final int desks;
    private final CustomerQueue queue;

    /**
     * Constructor for the SuperMarketGui class.
     * @param name The name of the supermarket.
     * @param desks The number of service points in the supermarket.
     * @param queue The queue of customers in the supermarket.
     */
    public SuperMarketGui(String name, int desks, CustomerQueue queue) {
        this.name = name;
        this.desks = desks;
        this.queue = queue;
    }

    /**
     * The start method for the JavaFX application.
     * This method sets up the GUI and starts the simulation.
     * @param primaryStage The primary stage for this application.
     */

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(name);
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 650, 350);
        primaryStage.setScene(scene);

        //  background color for the whole scene
        scene.getRoot().setStyle("-fx-background-color: #008000;");  // Green

        TotalSum totalSum = new TotalSum( queue);
        borderPane.setTop(totalSum);

        javafx.scene.layout.HBox hboxDesks = new javafx.scene.layout.HBox();
        borderPane.setCenter(hboxDesks);

        // background color and spacing for desks area
        hboxDesks.setStyle("-fx-background-color: #7db6c6");  // Blue
        hboxDesks.setSpacing(20);


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

    /**
     * The run method for the Runnable interface.
     * This method launches the JavaFX application.
     */
    @Override
    public void run() {
        launch();
    }
}


