package models;


import serivce.IParseModel;
import serivce.TicketService;
import utils.DateUtils;

import java.util.List;

public class Bill implements IParseModel<Bill> {
    private long billID;
    private double total;
    private long clientID;
    List<Billdetail> billdetailList;
    TicketService ticketService;

    public Bill() {
    }

    public Bill(long billID, double total, long clientID) {
        this.billID = billID;
        this.total = total;
        this.clientID=clientID;

    }

    public long getClientID() {
        return clientID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public long getBillID() {
        return billID;
    }

    public void setBillID(long billID) {
        this.billID = billID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }



    public List<Billdetail> getBilldetailList() {
        return billdetailList;
    }

    public void setBilldetailList(List<Billdetail> billdetailList) {
        this.billdetailList = billdetailList;
    }
    public Bill parse(String line) {
        Bill bill = null;
        String[] items = line.split(",");
        try {
            bill  = new Bill(Long.parseLong(items[0]), Double.parseDouble(items[1]), Long.parseLong(items[2]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bill;

    }
    public  void setTotaPrice(){
        double total = 0;

        //int count=0;
        if(this.getBilldetailList()!=null){
            for(Billdetail ot: this.getBilldetailList()){
                //count++;
                total +=45000;
            }
        }this.total= total;

    }
    public double total(int month){
        //double total = 0;
        double total1 =0;

        //int count=0;
        if(this.getBilldetailList()!=null){
            for(Billdetail ot: this.getBilldetailList()) {
                Ticket ticket = ticketService.findTicketbyID(ot.getTicketID());
                if (ticket.getCeateAT().getMonthValue() == month) {

                    //count++;
                    total1 += getTotal();
                }
            }//total1 +=total;
        }return total1;

    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", this.billID, this.total, this.clientID

        );
    }

}
