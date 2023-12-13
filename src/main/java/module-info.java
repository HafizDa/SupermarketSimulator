module com.example.supermarketqueuesimulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens com.example.supermarketqueuesimulator to javafx.fxml;
    exports com.example.supermarketqueuesimulator;
}