package com.example.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;

public class HelloApplication extends Application {

    public static ArrayList<Integer> delays = new ArrayList(Arrays.asList(5, 10, 15));

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
                Platform.runLater(() -> showAlert());
            }
        };
    }

    private static void showAlert() {
        String message = "Alarm!!!" + Helper.timeFormatHMS(LocalDateTime.now());
        ;
        //AlertApplication alarmAlert=new AlertApplication(message);
        //alarmAlert.show();
        Alert a = new Alert(Alert.AlertType.INFORMATION, message);
        Stage dialogsStage = new Stage();
        Scene scene = new Scene(a.getDialogPane(), 320, 240);
        dialogsStage.setTitle("Alert");
        dialogsStage.setScene(scene);
        dialogsStage.show();

        //a.show();

        System.out.println(message);
    }


    public static void main(String[] args) {

        // hier die Timer setzen
        for (Integer i : delays) {
            Helper.createTimerAfterSeconds(i, Helper.createConsoleTask());
            Helper.createTimerAfterSeconds(i, createAlertTask());
        }

        launch();

        // wird erst nach dem Schliessen der GUI erreicht
    }
}