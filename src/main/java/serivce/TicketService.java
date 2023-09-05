package serivce;

import models.Billdetail;
import models.Client;
import models.Schedule;
import models.Ticket;
import utils.FileUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TicketService {
    Scanner scanner = new Scanner(System.in);
    SeatService seatService = new SeatService();

    private static final String fileTicket = "./data/tickets.txt";

    public List<Ticket> getAllTicket() {
        List<Ticket> tickets = FileUtils.readData(fileTicket, Ticket.class);
        return tickets;
    }

    public void deleteTicketByID(long id) {
        List<Ticket> tickets = getAllTicket();
        Ticket ticket= tickets.stream().filter(ticket1 -> ticket1.getTicketID() == id).findFirst().orElseThrow(null);
        if(ticket!=null){
            tickets.remove(ticket);
        }
        FileUtils.writerData(fileTicket, tickets);
        seatService.changeSeatStatus1(ticket.getSeatID());
        //System.out.println("xoa ticket thanh cong");
    }

    public double total() {
        List<Ticket> tickets = getAllTicket();
        double total1 = 0;
        for (Ticket ticket : tickets) {
            total1 += 45000;
        }
        return total1;
    }

    public double totalmonth(int month) {
        List<Ticket> tickets = getAllTicket();
        double total1 = 0;

        for (Ticket ticket : tickets) {
            if (ticket.getCeateAT().getMonthValue() == month) {
                total1 += 45000;
            }
        }
        return total1;
    }

    public double totalday(LocalDate day) {
        List<Ticket> tickets = getAllTicket();
        double total1 = 0;

        for (Ticket ticket : tickets) {
            if (ticket.getCeateAT().isEqual(day)) {
                total1 += 45000;
            }
        }
        return total1;
    }

    public double totalYear(int year) {
        List<Ticket> tickets = getAllTicket();
        double total1 = 0;

        for (Ticket ticket : tickets) {
            if (ticket.getCeateAT().getYear() == year) {
                total1 += 45000;
            }
        }
        return total1;
    }


    public void createTicket(Ticket ticket) {
        List<Ticket> tickets = getAllTicket();
        tickets.add(ticket);
        FileUtils.writerData(fileTicket, tickets);

    }

    public List<Ticket> findTicketbyClientID(long id) {
        List<Ticket> tickets = getAllTicket();
        List<Ticket> result = tickets.stream().filter(ticket -> ticket.getClientID() == id).collect(Collectors.toList());
        return result;
    }

    public Ticket findTicketbyID(long id) {
        List<Ticket> tickets = getAllTicket();
        return tickets.stream().filter(ticket -> ticket.getTicketID() == id).findFirst().orElseThrow(null);
    }


}
