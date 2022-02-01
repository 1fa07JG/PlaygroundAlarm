package com.example.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;

public class HelloApplication extends Application {

    public static ArrayList<LocalDateTime> delays = new ArrayList<>(Arrays.asList(Helper.giveTimeToday(14, 28, 0),
            Helper.giveTimeToday(14, 28, -1), Helper.giveTimeToday(14, 28, 70)));


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Timer");
        stage.setScene(scene);
        stage.show();
    }

    public static TimerTask createAlertTask() {
        return new TimerTask() {
            @Override

            public void run() {
                Platform.runLater(HelloApplication::showAlert);
            }
        };
    }

    private static void showAlert() {
        String message = "Alarm!!!" + Helper.timeFormatHMS(LocalDateTime.now());
        Alert a = new Alert(Alert.AlertType.INFORMATION, message);
        a.show();
        System.out.println(message);
    }


    public static void main(String[] args) {

        // hier die Timer setzen
        for (LocalDateTime d : delays) {
            Helper.createTimerAtTime(d, Helper.createConsoleTask());
            Helper.createTimerAtTime(d, createAlertTask());
        }

        launch();

        // wird erst nach dem Schließen der GUI erreicht
    }
}