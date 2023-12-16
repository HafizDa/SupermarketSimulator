module com.supermarket.simulation {
    // This module requires javafx.controls for JavaFX GUI controls
    requires javafx.controls;

    // This module requires javafx.fxml for JavaFX FXML support
    requires javafx.fxml;

    // This module requires java.logging for logging events
    requires java.logging;

    // This module requires java.sql for SQL database management
    requires java.sql;

    // This module opens the com.supermarket.simulation.ui package to javafx.fxml
    opens com.supermarket.simulation.ui to javafx.fxml;

    // This module exports the com.supermarket.simulation.ui package
    exports com.supermarket.simulation.ui;

    // This module opens the com.supermarket.simulation.main package to javafx.fxml
    opens com.supermarket.simulation.main to javafx.fxml;

    // This module exports the com.supermarket.simulation.main package
    exports com.supermarket.simulation.main;

    // This module opens the com.supermarket.simulation.service package to javafx.fxml
    opens com.supermarket.simulation.service to javafx.fxml;

    // This module exports the com.supermarket.simulation.service package
    exports com.supermarket.simulation.service;

    // This module opens the com.supermarket.simulation.core package to javafx.fxml
    opens com.supermarket.simulation.core to javafx.fxml;

    // This module exports the com.supermarket.simulation.core package
    exports com.supermarket.simulation.core;

    // This module opens the com.supermarket.simulation.db package to javafx.fxml
    opens com.supermarket.simulation.db to javafx.fxml;

    // This module exports the com.supermarket.simulation.db package
    exports com.supermarket.simulation.db;
}
