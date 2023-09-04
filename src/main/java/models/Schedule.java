package models;

import serivce.IParseModel;
import utils.DateUtils;

import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule implements IParseModel {
    private long scheduleID;
    private long roomID;
    private long MovieID;
    private LocalDate scheduleDate;
    private LocalTime scheduleStart;
    private LocalTime scheduleEnd;
    //private LocalTime movieDuration;

    public Schedule() {
    }

    public Schedule(long scheduleID, long roomID, long movieID, LocalDate scheduleDate, LocalTime scheduleStart, LocalTime scheduleEnd) {
        this.scheduleID = scheduleID;
        this.roomID = roomID;
        MovieID = movieID;
        this.scheduleDate = scheduleDate;
        this.scheduleStart = scheduleStart;
        this.scheduleEnd = scheduleEnd;
        //this.movieDuration=movieDuration;
    }

//    public LocalTime getMovieDuration() {
//        return movieDuration;
//    }
//
//    public void setMovieDuration(LocalTime movieDuration) {
//        this.movieDuration = movieDuration;
//    }

    public long getMovieID() {
        return MovieID;
    }

    public void setMovieID(long movieID) {
        MovieID = movieID;
    }

    public long getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(long scheduleID) {
        this.scheduleID = scheduleID;
    }

    public long getRoomID() {
        return roomID;
    }

    public void setRoomID(long roomID) {
        this.roomID = roomID;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public LocalTime getScheduleStart() {
        return scheduleStart;
    }

    public void setScheduleStart(LocalTime scheduleStart) {
        this.scheduleStart = scheduleStart;
    }

    public LocalTime getScheduleEnd() {
        return scheduleEnd;
    }

    public void setScheduleEnd(LocalTime scheduleEnd) {
        this.scheduleEnd = scheduleEnd;
    }

    @Override
    public Schedule parse(String line) {
        Schedule schedule = null;
        //long scheduleID, long roomID, long movieID, LocalDate scheduleDate, LocalTime scheduleStart, LocalTime scheduleEnd
        String[] item = line.split(",");
        try {
            schedule = new Schedule(Long.parseLong(item[0]),Long.parseLong(item[1]),Long.parseLong(item[2]), DateUtils.parseDate(item[3]), LocalTime.parse(item[4]),LocalTime.parse(item[5]));
        }catch (Exception e){
            e.printStackTrace();
        }

        return schedule;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s", this.scheduleID, this.roomID, this.MovieID, this.scheduleDate, this.scheduleStart, this.scheduleEnd);
    }
}
