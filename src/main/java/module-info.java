module com.example.playgroundalarm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.app to javafx.fxml;
    exports com.example.app;
    exports com.example.playground;
    opens com.example.playground to javafx.fxml;
}