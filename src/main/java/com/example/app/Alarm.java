package com.example.app;

import java.time.LocalDateTime;
import java.util.Date;

public class Alarm {
    LocalDateTime startTime;
    LocalDateTime endTime;
    String memoText;

    public Alarm() {
    }

    public Alarm(LocalDateTime startTime) {
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(5);
        this.memoText = "";
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getMemoText() {
        return memoText;
    }

    public void setMemoText(String memoText) {
        this.memoText = memoText;
    }

    public Alarm(LocalDateTime startTime, LocalDateTime endTime, String memoText) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.memoText = memoText;
    }

    public String startInHMSFormat() {
        return Helper.timeFormatHMS(startTime);
    }

    public String startInHMFormat() {
        return Helper.timeFormatHM(startTime);
    }


    @Override
    public String toString() {
        return "Alarm{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", memoText='" + memoText + '\'' +
                '}';
    }

    public String toCsvFormat() {
        return startTime + "#" + endTime + "# " + memoText;
    }
}
