package com.example.app;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

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
    private TableView<Alarm> cotainer = new TableView();


    @FXML
    protected void startButtonClick() throws IOException {

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
    public void printAlarmTimes(ArrayList<Alarm> list) {
        TableColumn<TimeLocal, Integer> hourColum = new TableColumn();
        hourColum.setCellValueFactory(new PropertyValueFactory<TimeLocal, Integer>("firstname"));
        hourColum.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        hourColum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TimeLocal, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TimeLocal, Integer> event) {
                System.out.println("----EditCommit()---- ");
                event.getRowValue().setHour(event.getNewValue());
            }
        });
        TableColumn<TimeLocal, Integer> minuteColum = new TableColumn();
        //list.sort(null);
        //Todo make Alarm comparable by startTime
        for (Alarm l : list) {
            if (l.getStartTime().isAfter(LocalDateTime.now())) {


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


    public TableView<Alarm> getCotainer() {
        return cotainer;
    }
}