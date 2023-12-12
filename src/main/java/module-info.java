module com.example.supermarketcashiersimulator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.supermarketqueuesimulator to javafx.fxml;
    exports com.example.supermarketqueuesimulator;
}