package com.example.playgroundalarm;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        //welcomeText.setText("Welcome to JavaFX Application!");
        //erweiterung eine Uhr bauen die jede Sekunde auslöst

        LocalDateTime alarmTime = LocalDateTime.now().plusSeconds(20);
        System.out.println("Alarm um " + SimpleAlarm.timeFormatHMS(alarmTime));
        //obsolete(alarmTime);
        Timer freze=new Timer();
        freze.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()-> welcomeText.setText("Alarm!!!"+SimpleAlarm.timeFormatHMS(LocalDateTime.now())));
                System.out.println("Alarm!!!" + SimpleAlarm.timeFormatHMS(LocalDateTime.now()));
            }
        },SimpleAlarm.localDateToDate(alarmTime));    }
}