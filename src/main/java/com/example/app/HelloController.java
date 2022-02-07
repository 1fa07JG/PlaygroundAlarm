package com.example.app;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField newHour;

    @FXML
    private TextField newMinute;
    @FXML
    private Label clock;

    @FXML
    protected void onHelloButtonClick() throws IOException {

        //erweiterung eine Uhr bauen die jede Sekunde auslöst

        HelloApplication.runTimerDefault();
    }

    public TimerTask createGuiTask() {
        return new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> welcomeText.setText("Alarm!!!" + Helper.timeFormatHMS(LocalDateTime.now())));
                System.out.println("Alarm!!!" + Helper.timeFormatHMS(LocalDateTime.now()));
            }
        };
    }

    @FXML
    private void callAddTime(ActionEvent event) {
        int hour = Integer.parseInt(newHour.getText());
        int minute = Integer.parseInt(newMinute.getText());
        HelloApplication.addTime(hour, minute, HelloApplication.defaultDateList);
    }

    @FXML
    private void runClock() throws InterruptedException {
        while (true) {
            writeClockText();
            //Thread.sleep(500);
            Timer check = new Timer();
            check.schedule(createClockTask(), Helper.localDateToDate(LocalDateTime.now().plusNanos(1000)));
        }
    }

    public Runnable writeClockText() {
        clock.setText(Helper.timeFormatHMS(LocalDateTime.now()));
        return null;
    }

    public TimerTask createClockTask() {
        return new TimerTask() {
            @Override

            public void run() {
                Platform.runLater(writeClockText());
            }
        };
    }


}