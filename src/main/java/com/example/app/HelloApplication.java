package com.example.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;

public class HelloApplication extends Application {

    public static ArrayList<LocalDateTime> delays;


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

    public static void save(ArrayList<LocalDateTime> times) {
        serializeObject(times, findSavePath("dat", "Week"));
    }

    private static void serializeObject(LocalDateTime times, String path) {

        FileOutputStream fos;
        ObjectOutputStream out;

        try {
            fos = new FileOutputStream(path);
            out = new ObjectOutputStream(fos);
            out.writeObject(times);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void showAlert() {
        String message = "Alarm!!!" + Helper.timeFormatHMS(LocalDateTime.now());
        Alert a = new Alert(Alert.AlertType.INFORMATION, message);
        a.show();
        System.out.println(message);
    }


    public static void main(String[] args) {


        delays = new ArrayList<>(Arrays.asList(

                LocalDateTime.now().plusSeconds(10),
                LocalDateTime.now().plusSeconds(15),
                LocalDateTime.now().plusSeconds(30),
                Helper.giveTimeToday(16, 20, 0)));

        /*delays = new ArrayList<>(Arrays.asList(

                Helper.giveTimeToday(15, 40, 50),
                Helper.giveTimeToday(15, 40, -1),
                Helper.giveTimeToday(15, 40, 70)));*/

        // hier die Timer setzen
        for (LocalDateTime d : delays) {
            Helper.createTimerAtTime(d, Helper.createConsoleTask());
            Helper.createTimerAtTime(d, createAlertTask());
            // alternativ wären die Timer mit minuten oder Sekundenangabe zu nutzen
        }

        launch();

        // wird erst nach dem Schließen der GUI erreicht
    }
}