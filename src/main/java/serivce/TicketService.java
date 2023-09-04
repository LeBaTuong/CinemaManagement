package serivce;

import models.Billdetail;
import models.Client;
import models.Schedule;
import models.Ticket;
import utils.FileUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TicketService {
    Scanner scanner = new Scanner(System.in);

    private static final String fileTicket = "./data/tickets.txt";
    public List<Ticket> getAllTicket() {
        List<Ticket> tickets = FileUtils.readData(fileTicket, Ticket.class);
        return tickets;
    }
    public double total(){
        List<Ticket> tickets = getAllTicket();
        double total1=0;
        for(Ticket ticket:tickets){
            total1 += 45000;
        }return total1;
    }
    public double totalmonth(int month){
        List<Ticket> tickets = getAllTicket();
        double total1 =0;

            for(Ticket ticket:tickets) {
                if (ticket.getCeateAT().getMonthValue() == month) {
                    total1 += 45000;
                }
            }return total1;
        }
    public double totalday(LocalDate day){
        List<Ticket> tickets = getAllTicket();
        double total1 =0;

        for(Ticket ticket:tickets) {
            if (ticket.getCeateAT().isEqual(day)) {
                total1 += 45000;
            }
        }return total1;
    }
    public double totalYear(int year){
        List<Ticket> tickets = getAllTicket();
        double total1 =0;

        for(Ticket ticket:tickets) {
            if (ticket.getCeateAT().getYear()==year) {
                total1 += 45000;
            }
        }return total1;
    }


    public void createTicket(Ticket ticket){
        List<Ticket> tickets = getAllTicket();
        tickets.add(ticket);
        FileUtils.writerData(fileTicket, tickets);

    }
    public Ticket findTicketbyClientID(long id){
        List<Ticket> tickets = getAllTicket();
       return tickets.stream().filter(ticket -> ticket.getClientID()==id).findFirst().orElseThrow(null);
    }
    public Ticket findTicketbyID(long id){
        List<Ticket> tickets = getAllTicket();
        return tickets.stream().filter(ticket -> ticket.getTicketID()==id).findFirst().orElseThrow(null);
    }

}
