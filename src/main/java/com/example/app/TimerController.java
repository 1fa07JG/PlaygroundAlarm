package com.example.app;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TimerTask;

public class TimerController {

    @FXML
    private Label welcomeText;
    @FXML
    private TextField newHour;

    @FXML
    private TextField newMinute;

    @FXML
    private Label timerList;

    @FXML
    private TableColumn container = new TableColumn("Active Alarms");

    @FXML
    protected void onHelloButtonClick() throws IOException {

        //erweiterung eine Uhr bauen die jede Sekunde auslöst

        TimerApplication.runTimerDefault();
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
    public void printAlarmTimes(ArrayList<LocalDateTime> list) {

        list.sort(null);
        StringBuilder printFormat = new StringBuilder();
        for (LocalDateTime l : list) {
            if (l.isAfter(LocalDateTime.now())) {
                container.getCellFactory();
                Cell c = new Cell();
                TableCell t = new TableCell();
                t.setText((("Alarm at: ") + (Helper.timeFormatHMS(l))));
                container.setCellFactory();
            }
        }

    }

    @FXML
    private void callAddTime(ActionEvent event) {
        System.out.println("-- Reading alarms from Gui --");
        int hour = Integer.parseInt(newHour.getText());
        int minute = Integer.parseInt(newMinute.getText());
        TimerApplication.addTime(hour, minute, TimerApplication.defaultDateList);
        System.out.println("Reading alarm at " + hour + ":" + minute);

        printAlarmTimes(TimerApplication.defaultDateList);
    }


}