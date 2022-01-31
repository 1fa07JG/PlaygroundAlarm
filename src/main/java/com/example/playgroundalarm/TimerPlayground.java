package com.example.playgroundalarm;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class TimerPlayground {
    public static void main(String[]args){
        LocalDateTime later=LocalDateTime.now().plusSeconds(20);
        //int delay =15000;
        Timer myTimer =new Timer();
        System.out.println("Hallo World");
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("See You Later World");
            }
        },localDateToDate(later));
    }

    public static Date localDateToDate(LocalDateTime local){
        Instant instant =local.atZone(ZoneId.of("Europe/Berlin")).toInstant();
        return Date.from(instant);

    }


}
