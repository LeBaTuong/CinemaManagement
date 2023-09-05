package models;

import serivce.IParseModel;

import java.util.List;

public class Billdetail implements IParseModel<Billdetail> {
    private long billdetailid;
    private long billId;
    private long ticketID;
    List<Ticket> ticketList;


    public Billdetail() {
    }

    public Billdetail(long billdetailid, long billId, long ticketID) {
        this.billdetailid = billdetailid;
        this.billId = billId;
        this.ticketID = ticketID;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public long getBilldetailid() {
        return billdetailid;
    }

    public void setBilldetailid(long billdetailid) {
        this.billdetailid = billdetailid;
    }

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public long getTicketID() {
        return ticketID;
    }

    public void setTicketID(long ticketID) {
        this.ticketID = ticketID;
    }
    public Billdetail parse(String line) {
        Billdetail billdetail = null;
        String []items = line.split(",");
        try {
            billdetail = new Billdetail(Long.parseLong(items[0]), Long.parseLong(items[1]),
                    Long.parseLong(items[2]));
        }catch (Exception e){
            e.printStackTrace();
        }

        return billdetail;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", this.billdetailid, this.billId, this.ticketID);
    }
}
