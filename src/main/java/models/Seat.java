package models;

import eNum.ESeatStatus;
import serivce.IParseModel;

public class Seat implements IParseModel {
    private long seatID;
    private long roomID;
    //private String seatRow;
    private String seatNumber;
    private ESeatStatus eSeatStatus;

    public Seat() {
    }

    public Seat(long seatID, long roomID, String seatNumber, ESeatStatus eSeatStatus) {
        this.seatID = seatID;
        this.roomID = roomID;
        this.seatNumber = seatNumber;
        this.eSeatStatus = eSeatStatus;
    }

    public long getSeatID() {
        return seatID;
    }

    public void setSeatID(long seatID) {
        this.seatID = seatID;
    }

    public long getRoomID() {
        return roomID;
    }

    public void setRoomID(long roomID) {
        this.roomID = roomID;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public ESeatStatus geteSeatStatus() {
        return eSeatStatus;
    }

    public void seteSeatStatus(ESeatStatus eSeatStatus) {
        this.eSeatStatus = eSeatStatus;
    }
    public Seat parse(String line) {
        Seat seat=null;
        String[] item = line.split(",");
        try {
            seat= new Seat(Long.parseLong(item[0]),Long.parseLong(item[1]), item[2], ESeatStatus.valueOf(item[3]));
        }catch (Exception e){
            e.printStackTrace();
        }

        return seat;
    }
}
