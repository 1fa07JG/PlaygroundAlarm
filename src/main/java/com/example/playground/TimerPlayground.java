package com.example.playground;

import com.example.app.Helper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class TimerPlayground {

    public static void main(String[] args) {
        LocalDateTime later = LocalDateTime.now().plusSeconds(20);
        //int delay =15000;
        Timer myTimer = new Timer();
        System.out.println("Hallo World");
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("See You Later World");
            }
        }, localDateToDate(later));
        doSomething();
    }

    public static void doSomething() {
        System.out.println("jetzt " + Helper.timeFormatHMS(LocalDateTime.now()));
        Helper.createTimerAfterSeconds(20, Helper.createConsoleTask());
        Helper.createTimerAfterMinutes(1,Helper.createConsoleTask());
        System.out.println("später"+Helper.timeFormatHM(LocalDateTime.now()));
    }

    public static Date localDateToDate(LocalDateTime local) {
        Instant instant = local.atZone(ZoneId.of("Europe/Berlin")).toInstant();
        return Date.from(instant);

    }


}
