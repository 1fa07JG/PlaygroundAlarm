package com.example.app;

import java.time.LocalDateTime;

public class TimeLocal {
    LocalDateTime time;
    int hour;
    int minute;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
        hour = time.getHour();
        minute = time.getMinute();
    }

    public TimeLocal(LocalDateTime time) {
        this.time = time;
        hour = time.getHour();
        minute = time.getMinute();
    }

    public void setHour(int hour) {
        this.hour = hour;
        this.time = this.time.withHour(hour);
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        return "TimeLocal{" +
                "time=" + time +
                ", hour=" + hour +
                ", minute=" + minute +
                '}';
    }

    public TimeLocal() {
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
