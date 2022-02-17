package com.example.app;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Helper {

    public static void createTimerAfterMinutes(int minutes, TimerTask task) {
        createTimerAfterSeconds(minutes * 60, task);
    }

    public static void createTimerAfterSeconds(int seconds, TimerTask task) {
        Alarm alarmTime = new Alarm(LocalDateTime.now().plusSeconds(seconds));
        createTimerAtTime(alarmTime, task);
    }


    public static void createTimerAtTime(Alarm alarmTime, TimerTask task) {
        if (alarmTime.startTime.isAfter(LocalDateTime.now())) {
            System.out.println("createTimerAtTime() Alarm um " + alarmTime.startInHMSFormat());
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

    public static Date localDateToDate(Alarm local) {
        Instant instant = local.getStartTime().atZone(ZoneId.of("Europe/Berlin")).toInstant();
        return Date.from(instant);

    }

    public static ArrayList<Alarm> parseAlarmTriples(String[] alarmTripleArray) {
        ArrayList<Alarm> alarmList = new ArrayList<Alarm>();
        for (String alarmTriple : alarmTripleArray) {
            String[] alarmParts = alarmTriple.split("#");
            LocalDateTime startTime = stringToLocalDateTime(alarmParts[0]);
            LocalDateTime endTime = stringToLocalDateTime(alarmParts[1]);
            String memo = alarmParts[2];
            alarmList.add(new Alarm(startTime, endTime, memo));
        }
        return alarmList;
    }

    private static LocalDateTime stringToLocalDateTime(String cell) {
        LocalDateTime dateCache;
        String[] cells = cell.split("[-:T.]");
        int[] dateData = TimerApplication.stringArrayToIntArray(cells);

        if (dateData.length <= 5) {
            dateCache = LocalDateTime.of(dateData[0], dateData[1], dateData[2], dateData[3], dateData[4], 0);
        } else {
            dateCache = LocalDateTime.of(dateData[0], dateData[1], dateData[2], dateData[3], dateData[4], dateData[5]);
        }
        return dateCache;
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
