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
        if (alarmTime.isAfter(LocalDateTime.now())) {
            System.out.println("createTimerAtTime() Alarm um " + timeFormatHMS(alarmTime));
            Timer freeze = new Timer();
            freeze.schedule(task, localDateToDate(alarmTime));
        }
    }

    public static TimerTask createConsoleTask() {
        return new TimerTask() {
            @Override
            public void run() {
                System.out.println("createConsoleTask() Alarm!!!" + timeFormatHMS(LocalDateTime.now()));
            }
        };
    }

    public static Date localDateToDate(LocalDateTime local) {
        Instant instant = local.atZone(ZoneId.of("Europe/Berlin")).toInstant();
        return Date.from(instant);

    }

    public static LocalDateTime giveTimeToday(int hour, int minute, int second) {
        LocalDateTime today = LocalDateTime.now();
        return LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), toDayFormat(hour), toTimeFormat(minute), toTimeFormat(second));
    }

    public static int toTimeFormat(int timeAmount) {
        if (timeAmount < 0) {
            timeAmount = 0;
        } else if (timeAmount > 59) {
            timeAmount = 59;
        }
        return timeAmount;
    }

    public static int toDayFormat(int timeAmount) {
        if (timeAmount < 0) {
            timeAmount = 0;
        } else if (timeAmount > 23) {
            timeAmount = 23;
        }
        return timeAmount;
    }

    public static String timeFormatHM(LocalDateTime hora) {
        return hora.getHour() + ":" + formatWithTwoDigits(hora.getMinute());
    }

    public static String timeFormatHMS(LocalDateTime hora) {
        return hora.getHour() + ":" + formatWithTwoDigits(hora.getMinute()) + ":" + formatWithTwoDigits(hora.getSecond());
    }

    public static String formatWithTwoDigits(int timeNumber) {
        String timeText = "" + timeNumber;
        if (timeText.toCharArray().length == 1) {
            timeText = "0" + timeNumber;
        }
        return timeText;
    }

    public static void printArray(int[] y) {
        for (int i = 0; i < y.length; i++) {
            System.out.println("an der Stelle " + i + " steht:" + y[i]);
        }
    }
}
