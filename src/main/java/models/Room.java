package models;

import eNum.EAuth;
import serivce.IParseModel;
import utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class Room implements IParseModel {
    private long roomID;
    private String roomName;
    private long quantitySeat;
    private List<Seat> seats;
    private List<Schedule> schedules;


    public Room() {
    }

    public Room(long roomID, String roomName, long quantitySeat) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.quantitySeat = quantitySeat;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public long getRoomID() {
        return roomID;
    }

    public void setRoomID(long roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public long getQuantitySeat() {
        return quantitySeat;
    }

    public void setQuantitySeat(long quantitySeat) {
        this.quantitySeat = quantitySeat;
    }

    public List<Seat> getSeats() {
        List<Seat> seatList = new ArrayList<>();
        return seats;

    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
    //    public Room(long roomID, String roomName) {
//        this.roomID = roomID;
//        this.roomName = roomName;
//
//    }
//
//    public Room(String roomName, int seatRow, int seatperRow){
//        this.roomName=roomName;
//        int lastRow ='A' + seatRow+1;
//        for(char row='A'; row <=lastRow; row++){
//            for(int seatNumber=1; seatNumber<=seatperRow;seatNumber++){
//                Seat seat = new Seat(row + String.format("%02d", seatNumber));
//                seats.add(seat);
//            }
//        }
//    }
//    public boolean reserveSeat(String seatNumber){
//        Seat requestSeat=null;
//        for(Seat seat: seats){
//            if(seat.getSeatNumber().equals(seatNumber)){
//                break;
//            }
//        }
//        if(requestSeat==null){
//            System.out.println("ghế bạn đã được đặt, bạn hãy đặt ghế khác nhé");
//            return false;
//        }
//        return requestSeat.reserve();
//    }
//    public void printRoomSeat(){
//        for(Seat seat: seats){
//            System.out.println(seat.getSeatNumber());
//            }
//        }
//
//
//    public long getRoomID() {
//        return roomID;
//    }
//
//    public void setRoomID(long roomID) {
//        this.roomID = roomID;
//    }
//
//    public String getRoomName() {
//        return roomName;
//    }
//
//    public void setRoomName(String roomName) {
//        this.roomName = roomName;
//    }
//
//    public List<Seat> getSeats() {
//        return seats;
//    }
//
//    public void setSeats(List<Seat> seats) {
//        this.seats = seats;
//    }
    public Room parse(String line) {
        Room room = null;
        //long id,String fullName, String email, long password, String phoneNumber, LocalDate dob, EAuth eAuth
        String[] item = line.split(",");
        try {
            room = new Room(Long.parseLong(item[0]), item[1],Long.parseLong(item[2]));
        }catch (Exception e){
            e.printStackTrace();
        }

        return room;
    }
    //
    @Override
    public String toString() {
        //long id, String fullName, String email, long password, String phoneNumber, LocalDate dob, EAuth eAuth
        return String.format("%s,%s", this.roomID, this.roomName);
    }
}
