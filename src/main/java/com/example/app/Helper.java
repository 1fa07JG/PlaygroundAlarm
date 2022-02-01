package com.example.app;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Helper {

    public static void createTimerAfterMinutes(int minutes, TimerTask task) {
        createTimerAfterSeconds(minutes * 60, task);
    }

    public static void createTimerAfterSeconds(int seconds, TimerTask task) {
        LocalDateTime alarmTime = LocalDateTime.now().plusSeconds(seconds);
        createTimerAtTime(alarmTime, task);
    }




    public static void createTimerAtTime(LocalDateTime alarmTime, TimerTask task) {
        System.out.println("Alarm um " + timeFormatHMS(alarmTime));
        Timer freeze = new Timer();
        freeze.schedule(task, localDateToDate(alarmTime));
    }

    public static TimerTask createConsoleTask() {
        return new TimerTask() {
            @Override
            public void run() {
                System.out.println("Alarm!!!" + timeFormatHMS(LocalDateTime.now()));
            }
        };
    }

    public static Date localDateToDate(LocalDateTime local) {
        Instant instant = local.atZone(ZoneId.of("Europe/Berlin")).toInstant();
        return Date.from(instant);

    }

    public static String timeFormatHM(LocalDateTime hora) {
        return hora.getHour() + ":" + hora.getMinute();
    }

    public static String timeFormatHMS(LocalDateTime hora) {
        return hora.getHour() + ":" + hora.getMinute() + ":" + hora.getSecond();
    }
}
