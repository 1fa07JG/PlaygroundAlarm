package com.example.app;

import java.time.LocalDateTime;
import java.util.Date;

public class Alarm {
    //Todo integrate the actual Timer Task in this Class

    String startTimeString;
    String endTimeString;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String memoText;

    public Alarm() {
    }

    public Alarm(LocalDateTime startTime) {
        this(startTime, startTime.plusMinutes(5), "");
    }

    public Alarm(LocalDateTime startTime, LocalDateTime endTime, String memoText) {
        this.startTime = startTime;
        this.startTimeString = Helper.timeFormatHM(startTime);
        this.endTimeString = Helper.timeFormatHM(endTime);
        this.endTime = endTime;
        this.memoText = memoText;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        this.startTimeString = Helper.timeFormatHM(startTime);
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getStartTimeString() {
        return startTimeString;
    }

    public String getEndTimeString() {
        return endTimeString;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        this.endTimeString = Helper.timeFormatHM(endTime);
    }

    public String getMemoText() {
        return memoText;
    }

    public void setMemoText(String memoText) {
        this.memoText = memoText;
    }


    public String startInHMSFormat() {
        return Helper.timeFormatHMS(startTime);
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
