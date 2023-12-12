package com.example.supermarketqueuesimulator;

import javafx.application.Platform;
import javafx.scene.layout.HBox;

import java.util.LinkedList;
import java.util.Queue;

public class ClientQueue extends HBox implements Runnable {

    private final Queue<Client> clients = new LinkedList<>();
    private int clientNumber = 10;

    public ClientQueue() {
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
        Client client = new Client(i);
        clients.add(client);
        Platform.runLater(() -> getChildren().add(client));
        updateUI();
    }

    private Client removeClient() {
        Client client = clients.remove();
        Platform.runLater(() -> getChildren().remove(client));
        updateUI();
        return client;
    }

    public synchronized Client callClient() throws InterruptedException {
        while (clients.isEmpty()) {
            Thread.sleep(900);
        }
        return removeClient();
    }

    private void updateUI() {
        // Your implementation for updating the UI goes here
        // You can add more UI updates as needed
    }
}
