package view;

import eNum.EMovieType;
import eNum.ESeatStatus;
import models.*;
import serivce.*;
import utils.DateUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TicketView {
    Scanner scanner = new Scanner(System.in);
    TicketService ticketService = new TicketService();
    MovieService movieService = new MovieService();
    ScheduleService scheduleService = new ScheduleService();
    RoomService roomService = new RoomService();
    SeatService seatService = new SeatService();
    ClientService clientService = new ClientService();
    ScheduleView scheduleView = new ScheduleView();

    public void laucher() {
        boolean checkAction = false;
        do {
            System.out.println("Menu quản lý user: ");
            System.out.println("Nhập 1.xem danh sách chiếu  ");
            System.out.println("Nhập 2. Sửa danh sách");
            System.out.println("Nhập 3. thêm danh sách");
            System.out.println("Nhập 4. xóa danh sách theo id");
            System.out.println("Nhập 5: Xóa sản phẩm");
            System.out.println("Nhập 5: Sắp xếp theo (Vào trong chọn thêm menu: tên/tuổi/giới tính/dob + TĂNG DẦN/GIẢM DẦN) ");
            System.out.println("Nhập 6: Tìm kiếm theo ");


            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 1: {
                    showTicket();
                    break;
                }
                case 2: {
                    UpdateTicket();
                    break;
                }
                case 3: {
                    addTicket();
                    break;

                }
                case 4: {
                    deleteTicketByID();
                    break;
                }
                case 5: {
                    showTicketByIDByIDClient();
                    break;
                }


            }
        } while (checkAction);

    }

    private void deleteTicketByID() {
        System.out.println("Nhập id cần xóa");
        long id = Long.parseLong(scanner.nextLine());
        ticketService.deleteTicketByID(id);
        showTicket();

    }

//    private void showTicketByIDClient() {
//    }


    public void addTicket() {
        Ticket ticket = new Ticket();
        Seat seat = new Seat();
        ticket.setTicketID(System.currentTimeMillis() % 10000);
        scheduleView.showSchedule();
        System.out.println("Nhập id lịch phim chiếu mà bạn muốn chọn: ");
        long scheduleID = Long.parseLong(scanner.nextLine());
        ticket.setScheduleID(scheduleID);

        long seatID;
        do {
            scheduleView.showScheduleIDSeat(scheduleID);
            System.out.println("Hãy chọn ghế đang có sẵn nhé: ");
            seatID = Long.parseLong(scanner.nextLine());
        }
        while (seatService.changeSeatStatus(seatID));

        ticket.setSeatID(seatID);
        System.out.println("Nhập id người đặt");
        long clientID = Long.parseLong(scanner.nextLine());
        ticket.setClientID(clientID);
        ticket.setCeateAT(LocalDate.now());
        ticketService.createTicket(ticket);
        System.out.println("Đã thêm thành công");
        //showTicketByID();
        //showTicketByID(ticket.setTicketID(System.currentTimeMillis()%10000));
        //showTicket();

        //System.out.println("");


        //if()
//        for()
//        if(scheduleService.findScheduleById(scheduleID)){
//
//        }
    }

    private void UpdateTicket() {

    }

    private void showTicket() {
        //long ticketID, long movieID, long scheduleID, long roomID, long seatID, long clientID, LocalDate ceateAT
        List<Ticket> tickets = ticketService.getAllTicket();
        System.out.printf("%10s | %20s | %20s | %30s | %30s  \n", "TicketID", "ScheduleID", "Seat", "người đặt", "ngày đặt lịch");
        for (Ticket ticket : tickets) {
            //Movie movieID = movieService.findMoviceById(ticket.getMovieID());
            Schedule schedule = scheduleService.findScheduleById(ticket.getScheduleID());
            //Room roomID = roomService.findRoomByIdRoom(ticket.getRoomID());
            Seat seatID = seatService.findSeatById(ticket.getSeatID());
            Client clientID = clientService.findClientById(ticket.getClientID());
            System.out.printf("%10s | %20s | %20s | %30s | %30s   \n",
                    ticket.getTicketID(), schedule.getScheduleID(), seatID.getSeatNumber(), clientID.getFullName(), ticket.getCeateAT());
            System.out.println("Hiển thị lịch chiếu cụ thể:");
            scheduleView.showScheduleID(ticket.getScheduleID());
            System.out.println("--------------------------------------------------------------------------------");
        }

    }

    //    public void showTicketByID(long ticketID){
//        System.out.println("Nhập id cần show:");
//        long ticketID = Long.parseLong(scanner.nextLine());
//        Ticket ticket= ticketService.findTicketbyID(ticketID);
//
//        System.out.printf("%10s | %20s | %20s | %30s | %30s  \n", "TicketID", "ScheduleID", "Seat", "người đặt", "ngày đặt lịch");
//
//            //Movie movieID = movieService.findMoviceById(ticket.getMovieID());
//            Schedule schedule = scheduleService.findScheduleById(ticket.getScheduleID());
//            //Room roomID = roomService.findRoomByIdRoom(ticket.getRoomID());
//            Seat seatID = seatService.findSeatById(ticket.getSeatID());
//            Client clientID = clientService.findClientById(ticket.getClientID());
//            System.out.printf("%10s | %20s | %20s | %30s | %30s   \n",
//                    ticket.getTicketID(),schedule.getScheduleID(),seatID.getSeatNumber(),clientID.getFullName(),ticket.getCeateAT());
//            System.out.println("Hiển thị lịch chiếu cụ thể:");
//            scheduleView.showScheduleID(ticket.getScheduleID());
//            System.out.println("--------------------------------------------------------------------------------");
//    }
    public void showTicketByIDByIDClient() {
        System.out.println("Nhập id cần show:");
        long ClientID = Long.parseLong(scanner.nextLine());
        List<Ticket> ticket1 = ticketService.findTicketbyClientID(ClientID);

        System.out.printf("%10s | %20s | %20s | %30s | %30s  \n", "TicketID", "ScheduleID", "Seat", "người đặt", "ngày đặt lịch");
        for (Ticket ticket : ticket1) {
            Schedule schedule = scheduleService.findScheduleById(ticket.getScheduleID());
            //Room roomID = roomService.findRoomByIdRoom(ticket.getRoomID());
            Seat seatID = seatService.findSeatById(ticket.getSeatID());
            Client clientID = clientService.findClientById(ticket.getClientID());
            System.out.printf("%10s | %20s | %20s | %30s | %30s   \n",
                    ticket.getTicketID(), schedule.getScheduleID(), seatID.getSeatNumber(), clientID.getFullName(), ticket.getCeateAT());
            System.out.println("Hiển thị lịch chiếu cụ thể:");
            scheduleView.showScheduleID(ticket.getScheduleID());
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public void showTicketByIDByIDClient1(long ticketID) {
//        System.out.println("Nhập id cần show:");
//        long ticketID = Long.parseLong(scanner.nextLine());
        List<Ticket> ticket1 = ticketService.findTicketbyClientID(ticketID);

        System.out.printf("%10s | %20s | %20s | %30s | %30s  \n", "TicketID", "ScheduleID", "Seat", "người đặt", "ngày đặt lịch");
        for (Ticket ticket : ticket1) {
            Schedule schedule = scheduleService.findScheduleById(ticket.getScheduleID());
            //Room roomID = roomService.findRoomByIdRoom(ticket.getRoomID());
            Seat seatID = seatService.findSeatById(ticket.getSeatID());
            Client clientID = clientService.findClientById(ticket.getClientID());
            System.out.printf("%10s | %20s | %20s | %30s | %30s   \n",
                    ticket.getTicketID(), schedule.getScheduleID(), seatID.getSeatNumber(), clientID.getFullName(), ticket.getCeateAT());
            System.out.println("Hiển thị lịch chiếu cụ thể:");
            scheduleView.showScheduleID(ticket.getScheduleID());
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public static void main(String[] args) {
        TicketView ticketView = new TicketView();
        ticketView.laucher();
    }

}
