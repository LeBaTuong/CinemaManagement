package models;


import serivce.IParseModel;
import utils.DateUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ticket implements IParseModel<Ticket> {
    private long ticketID;
    //private long movieID;
    private long scheduleID;
    //private long roomID;
    private long seatID;
    private long clientID;
    private LocalDate ceateAT;
    //private long employeeID;
    List<Client> clientList;


    public Ticket() {
    }

    public Ticket(long ticketID, long scheduleID, long seatID, long clientID, LocalDate ceateAT) {
        this.ticketID = ticketID;
        // this.movieID = movieID;
        this.scheduleID = scheduleID;
        // this.roomID = roomID;
        this.seatID = seatID;
        this.clientID = clientID;
        this.ceateAT = ceateAT;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public long getTicketID() {
        return ticketID;
    }

    public void setTicketID(long ticketID) {
        this.ticketID = ticketID;
    }

//    public long getMovieID() {
//        return movieID;
//    }
//
//    public void setMovieID(long movieID) {
//        this.movieID = movieID;
//    }

    public long getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(long scheduleID) {
        this.scheduleID = scheduleID;
    }

//    public long getRoomID() {
//        return roomID;
//    }
//
//    public void setRoomID(long roomID) {
//        this.roomID = roomID;
//    }

    public long getSeatID() {
        return seatID;
    }

    public void setSeatID(long seatID) {
        this.seatID = seatID;
    }

    public long getClientID() {
        return clientID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public LocalDate getCeateAT() {
        return ceateAT;
    }

    public void setCeateAT(LocalDate ceateAT) {
        this.ceateAT = ceateAT;
    }

    @Override
    public Ticket parse(String line) {
        Ticket ticket = null;
        //long ticketID, long scheduleID, long seatID, long clientID, LocalDate ceateAT
        String[] item = line.split(",");
        try {
            ticket = new Ticket(Long.parseLong(item[0]), Long.parseLong(item[1]), Long.parseLong(item[2]),Long.parseLong(item[3]),DateUtils.parseDate(item[4]));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ticket;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", this.ticketID, this.scheduleID,this.seatID,this.clientID,DateUtils.fomatLocalDate(this.ceateAT));
    }
}
