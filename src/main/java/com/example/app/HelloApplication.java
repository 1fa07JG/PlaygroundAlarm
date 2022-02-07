package com.example.app;

import com.opencsv.CSVReader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.opencsv.CSVWriter;


import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;

public class HelloApplication extends Application implements Serializable {

    public static ArrayList<LocalDateTime> defaultDateList;
    @Serial
    public static final long serialVersionUID = 1;



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
        serializeObject(times, "./alarmlist.dat");//siehe Speiseplan: findSavePath("dat", "Week")
    }

    public static void saveCSV(ArrayList<LocalDateTime> times) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("./alarmlistcsv.csv"));
        /*ArrayList<String> dateString=new ArrayList<>();
        for (LocalDateTime l:times) {
            String s=l.toString();
            dateString.add(s);
        }*/
        String[] dateString = new String[times.size()];
        for (int i = 0; i < times.size(); i++) {
            dateString[i] = times.get(i).toString();
        }
        writer.writeNext(dateString);
        writer.flush();
    }

    public static ArrayList<LocalDateTime> readCSV(String link) throws IOException {
        ArrayList<LocalDateTime> dateTimeArrayList = new ArrayList<>();
        FileReader filereader = new FileReader(link);
        CSVReader reader = new CSVReader(filereader);
        String[] dateString;
        //String[] sL= (String[]) dateString.toArray();
        //String[] nextRecord;


        // we are going to read data line by line
        while ((dateString = reader.readNext()) != null) {
            for (String cell : dateString) {
                //System.out.println(cell);
                String[] cells = cell.split("[-:T.]");
                int[] dateData = stringArrayToIntArray(cells);
                LocalDateTime dateCache = LocalDateTime.of(dateData[0], dateData[1], dateData[2], dateData[3], dateData[4], dateData[4]);
                dateTimeArrayList.add(dateCache);
            }
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


    private static void serializeObject(ArrayList<LocalDateTime> times, String path) {

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




    public static void addTime(int hour, int minute, ArrayList<LocalDateTime> times) {
        times.add(Helper.giveTimeToday(hour, minute, 0));
    }


    public static void main(String[] args) throws IOException {


        //runTimerDefault();

        launch();

        // wird erst nach dem Schließen der GUI erreicht
    }
}