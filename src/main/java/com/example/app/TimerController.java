package com.example.app;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FormatStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
    private TableView<Alarm> cotainer;


    @FXML
    protected void startButtonClick() throws IOException {

        //erweiterung eine Uhr bauen die jede Sekunde auslöst

        TimerApplication.runTimerDefault();
    }

    public void initialize() throws IOException {
        cotainer.setEditable(true);
        ObservableList<TableColumn<Alarm, ?>> columns = cotainer.getColumns();
        TableColumn<Alarm, String> startTimeColum = (TableColumn<Alarm, String>) columns.get(0);
        startTimeColum.setText("Start Time");
        startTimeColum.setCellValueFactory(new PropertyValueFactory<Alarm, String>("startTimeString"));
        startTimeColum.setCellFactory(TextFieldTableCell.forTableColumn());
        startTimeColum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Alarm, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Alarm, String> event) {
                System.out.println("----EditCommit()---- ");
                String AlarmTimeInHMFormat = event.getNewValue();
                String[] hourAndMinute = AlarmTimeInHMFormat.split(":");
                LocalDateTime startTime = event.getRowValue().getStartTime();
                startTime = startTime.withHour(Integer.parseInt(hourAndMinute[0]));
                startTime = startTime.withMinute(Integer.parseInt(hourAndMinute[1]));
                event.getRowValue().setStartTime(startTime);
            }
        });
        TableColumn<Alarm, String> endTimeColum = (TableColumn<Alarm, String>) columns.get(1);
        endTimeColum.setText("End Time");
        endTimeColum.setCellValueFactory(new PropertyValueFactory<Alarm, String>("endTimeString"));
        endTimeColum.setCellFactory(TextFieldTableCell.forTableColumn());
        endTimeColum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Alarm, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Alarm, String> event) {
                System.out.println("----EditCommit()---- ");
                String AlarmTimeInHMFormat = event.getNewValue();
                String[] hourAndMinute = AlarmTimeInHMFormat.split(":");
                LocalDateTime endTime = event.getRowValue().getStartTime();
                endTime = endTime.withHour(Integer.parseInt(hourAndMinute[0]));
                endTime = endTime.withMinute(Integer.parseInt(hourAndMinute[1]));
                event.getRowValue().setStartTime(endTime);
            }
        });

        TableColumn<Alarm, String> memoColum = (TableColumn<Alarm, String>) columns.get(2);
        memoColum.setText("Notiz");
        memoColum.setCellValueFactory(new PropertyValueFactory<Alarm, String>("memoText"));
        memoColum.setCellFactory(TextFieldTableCell.forTableColumn());
        memoColum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Alarm, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Alarm, String> editEvent) {
                System.out.println("----EditCommit()----");
                editEvent.getRowValue().setMemoText(editEvent.getNewValue());
            }
        });

        cotainer.getItems().addAll(TimerApplication.readAlarmsFromCsvFile("./alarmlistcsv.csv"));


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
        TableColumn<Alarm, String> startTimeColum = new TableColumn();
        startTimeColum.setCellValueFactory(new PropertyValueFactory<Alarm, String>("starTime"));
        startTimeColum.setCellFactory(TextFieldTableCell.forTableColumn());
        startTimeColum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Alarm, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Alarm, String> event) {
                System.out.println("----EditCommit()---- ");
                String AlarmTimeInHMFormat = event.getNewValue();
                String[] hourAndMinute = AlarmTimeInHMFormat.split(":");
                LocalDateTime startTime = event.getRowValue().getStartTime();
                startTime = startTime.withHour(Integer.parseInt(hourAndMinute[0]));
                startTime = startTime.withMinute(Integer.parseInt(hourAndMinute[1]));
                event.getRowValue().setStartTime(startTime);
            }
        });
        TableColumn<Alarm, String> endTimeColum = new TableColumn();
        endTimeColum.setCellValueFactory(new PropertyValueFactory<Alarm, String>("starTime"));
        endTimeColum.setCellFactory(TextFieldTableCell.forTableColumn());
        endTimeColum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Alarm, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Alarm, String> event) {
                System.out.println("----EditCommit()---- ");
                String AlarmTimeInHMFormat = event.getNewValue();
                String[] hourAndMinute = AlarmTimeInHMFormat.split(":");
                LocalDateTime endTime = event.getRowValue().getStartTime();
                endTime = endTime.withHour(Integer.parseInt(hourAndMinute[0]));
                endTime = endTime.withMinute(Integer.parseInt(hourAndMinute[1]));
                event.getRowValue().setStartTime(endTime);
            }
        });
        cotainer.getColumns().add(startTimeColum);
        cotainer.getColumns().add(endTimeColum);


        System.out.println("add colum");
    }

    @FXML
    private void callAddTime(ActionEvent event) {
        System.out.println("-- Reading alarms from Gui --");
        int hour = Integer.parseInt(newHour.getText());
        int minute = Integer.parseInt(newMinute.getText());
        TimerApplication.addTime(hour, minute, TimerApplication.defaultDateList);
        System.out.println("Reading alarm at " + hour + ":" + minute);

        //printAlarmTimes(TimerApplication.defaultDateList);
    }


    public TableView<Alarm> getCotainer() {
        return cotainer;
    }
}