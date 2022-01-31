package com.example.playgroundalarm;

import javafx.application.Platform;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SimpleAlarm {

    public static void main(String[] args) {
        System.out.println("jetzt " + timeFormatHMS(LocalDateTime.now()));
        timer20Sec();
        //System.out.println("Alarm!!!" + timeFormatHM(LocalDateTime.now()));

    }

    private static void timer20Sec() {
        //LocalDateTime alarmTime=LocalDateTime.now().plusMinutes(1);
        LocalDateTime alarmTime = LocalDateTime.now().plusSeconds(20);
        System.out.println("Alarm um " + timeFormatHMS(alarmTime));
        //obsolete(alarmTime);
        Timer freze=new Timer();
        freze.schedule(new TimerTask() {
            @Override
            public void run() {
                //Platform.runLater(()->System.out.println("Alarm!!!" + timeFormatHMS(LocalDateTime.now())));
                System.out.println("Alarm!!!" + timeFormatHMS(LocalDateTime.now()));
            }
        },localDateToDate(alarmTime));
    }

    public static Date localDateToDate(LocalDateTime local){
        Instant instant =local.atZone(ZoneId.of("Europe/Berlin")).toInstant();
        return Date.from(instant);

    }

    private static void obsolete(LocalDateTime alarmTime) throws InterruptedException {
        do {
            System.out.println(timeFormatHMS(LocalDateTime.now()));
            Thread.sleep(500);


        } while (LocalDateTime.now().isBefore(alarmTime));
    }

    public static String timeFormatHM(LocalDateTime hora) {
        return hora.getHour() + ":" + hora.getMinute();
    }

    public static String timeFormatHMS(LocalDateTime hora) {
        String print=hora.getHour()+ ":" + hora.getMinute() + ":" + hora.getSecond();
        return print;
    }
}
