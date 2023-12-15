/**
 * Defines the com.example.supermarketqueuesimulator module.
 * This module requires javafx.controls, javafx.fxml, java.logging, and java.sql.
 * It opens the com.example.supermarketqueuesimulator package to javafx.fxml and exports it.
 */
module com.example.supermarketqueuesimulator {
    // This module requires javafx.controls for JavaFX GUI controls
    requires javafx.controls;

    // This module requires javafx.fxml for JavaFX FXML support
    requires javafx.fxml;

    // This module requires java.logging for logging events
    requires java.logging;

    // This module requires java.sql for SQL database management
    requires java.sql;

    // This module opens the com.example.supermarketqueuesimulator package to javafx.fxml
    opens com.example.supermarketqueuesimulator to javafx.fxml;

    // This module exports the com.example.supermarketqueuesimulator package
    exports com.example.supermarketqueuesimulator;
}