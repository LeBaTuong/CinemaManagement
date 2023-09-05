package serivce;

import models.Bill;
import models.Billdetail;
import models.Schedule;
import models.Ticket;
import utils.FileUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BillDetailService {
    private final String fileBilldetail = "./data/billdetails.txt";
    private static final String fileTicket = "./data/tickets.txt";
    TicketService ticketService = new TicketService();

    public List<Billdetail> getAllBillDetail() {
        List<Billdetail> billdetails = FileUtils.readData(fileBilldetail, Billdetail.class);
        for (Billdetail b : billdetails) {
            List<Ticket> tickets = ticketService.findTicketbyClientID(b.getTicketID());
            b.setTicketList(tickets);
        }
        return billdetails;
    }

    public List<Billdetail> getAllBillDetailByIDBill(long id) {
        List<Billdetail> billdetails = getAllBillDetail();
        List<Billdetail> result = billdetails.stream().filter(billdetail -> billdetail.getBillId() == id).collect(Collectors.toList());
        return result;
    }

    public void createBillDetail(Billdetail billdetail) {
        List<Billdetail> billdetails = getAllBillDetail();
        billdetails.add(billdetail);
        FileUtils.writerData(fileBilldetail, billdetails);

    }

    public void deleteBillDetailByID(long id) {
        List<Billdetail> billdetails = getAllBillDetail();
        Billdetail billdetail = billdetails.stream().filter(billdetail1 -> billdetail1.getBilldetailid() == id).findFirst().orElseThrow(null);
        ticketService.deleteTicketByID(billdetail.getTicketID());
        if (billdetail != null) {
            billdetails.remove(billdetail);
        }
        FileUtils.writerData(fileBilldetail, billdetails);
//        for(Ticket t: billdetail.getTicketList()){
//            ticketService.deleteTicketByID(t.getTicketID());
//        }
//        Billdetail billdetail = billdetails.stream().filter(billdetail1 -> billdetail1.getBilldetailid()==id).findFirst().orElseThrow(null);
//        if(billdetail!=null){
//            billdetails.remove(billdetail);
//        }
//        TicketService ticketService1 = new TicketService();
//        List<Ticket> tickets = ticketService1.getAllTicket();
//        List<Ticket> listToRemove = tickets.stream().filter(s->s.getTicketID()==id).toList();
//        for (Ticket t:listToRemove) {
//            for (Ticket t2:tickets) {
//                if(t.getTicketID()==t2.getTicketID()){
//                    tickets.remove(t2);
//                }
//            }
//        }
//        FileUtils.writerData(fileTicket, tickets);
//        FileUtils.writerData(fileBilldetail, billdetails);

    }
}
