module com.example.playgroundalarm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.playgroundalarm to javafx.fxml;
    exports com.example.playgroundalarm;
}