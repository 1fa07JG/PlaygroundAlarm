package com.example.app;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TimerTask;

public class TimerApplication extends Application implements Serializable {
    public static ArrayList<Alarm> defaultDateList = new ArrayList<>();
    public static final long serialVersionUID = 1;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TimerApplication.class.getResource("timer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        TimerController lord = fxmlLoader.getController();
        stage.setTitle("Timer");
        stage.setScene(scene);
        stage.show();
        lord.printAlarmTimes(defaultDateList);
        lord.getCotainer();
    }


    public static TimerTask createAlertTask() {
        return new TimerTask() {
            @Override

            public void run() {
                Platform.runLater(TimerApplication::showAlert);
            }
        };
    }

    public static void saveCSV(ArrayList<Alarm> times) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("./alarmlistcsv.csv"));
        String[] dateString = new String[times.size()];
        //times.sort(null);
        //Todo make Alarm sortable
        for (int i = 0; i < times.size(); i++) {
            dateString[i] = times.get(i).toCsvFormat();
        }
        writer.writeNext(dateString);
        writer.flush();
    }

    public static void addToAlarmList(ArrayList<Alarm> list, LocalDateTime alarmTime) {
        if (!isInArrayList(list, alarmTime)) {
            list.add(new Alarm(alarmTime));
        }
    }

    public static boolean isInArrayList(ArrayList<Alarm> list, LocalDateTime alarmTime) {
        boolean exists = false;
        for (Alarm l : list) {
            if (l.getStartTime().equals(alarmTime)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public static ArrayList<Alarm> readCSV(String link) throws IOException {
        ArrayList<Alarm> dateTimeArrayList = new ArrayList<>();
        FileReader filereader = new FileReader(link);
        CSVReader reader = new CSVReader(filereader);
        String[] dateString;
        //String[] sL= (String[]) dateString.toArray();
        //String[] nextRecord;


        // we are going to read data line by line
        while ((dateString = reader.readNext()) != null) {
            dateTimeArrayList.add(Helper.readCsvAlarm(dateString));
        }
        return dateTimeArrayList;
    }

    public static int[] stringArrayToIntArray(String[] zahlText) {
        int[] zahl = new int[zahlText.length];
        for (int i = 0; i < zahlText.length; i++) {
            zahl[i] = Integer.parseInt(zahlText[i]);
        }
        return zahl;
    }


    private static void showAlert() {
        String message = "Alarm!!!" + Helper.timeFormatHMS(LocalDateTime.now());
        Alert a = new Alert(Alert.AlertType.INFORMATION, message);
        a.initModality(Modality.NONE);
        a.show();
        System.out.println(message);
    }


    public static void addTime(int hour, int minute, ArrayList<Alarm> times) {

        addToAlarmList(times, Helper.giveTimeToday(hour, minute, 0));
    }


    public static void main(String[] args) {

        addAlarmFromTerminal(args);


        launch();

        // wird erst nach dem Schließen der GUI erreicht
    }

    public static void addAlarmFromTerminal(String[] input) {
        System.out.println("-- Reading alarms from Command Line --");
        for (String s : input) {
            String[] timeData = s.split(":");
            int hour = Integer.parseInt(timeData[0]);
            int minute = Integer.parseInt(timeData[1]);
            addTime(hour, minute, defaultDateList);
            System.out.println("Reading alarm at " + hour + ":" + minute);
        }
    }

    public static void runTimerDefault() throws IOException {

        //addTestContent();




        /*openWindow = new ArrayList<>(Arrays.asList(

                Helper.giveTimeToday(8, 45, 00),
                Helper.giveTimeToday(9, 35, 00),
                Helper.giveTimeToday(15, 40, 70)));*/


        saveCSV(defaultDateList);

        for (Alarm d : readCSV("./alarmlistcsv.csv")) {
            Helper.createTimerAtTime(d, createAlertTask());
            // alternativ wären die Timer mit minuten oder Sekundenangabe zu nutzen
        }
    }

    private static void addTestContent() {
        addToAlarmList(defaultDateList, LocalDateTime.now().plusSeconds(10));
        addToAlarmList(defaultDateList, LocalDateTime.now().plusSeconds(15));
        addToAlarmList(defaultDateList, LocalDateTime.now().plusSeconds(30));
        addToAlarmList(defaultDateList, Helper.giveTimeToday(16, 20, 0));
        addToAlarmList(defaultDateList, LocalDateTime.of(1983, 1, 19, 23, 59, 50));
    }
}