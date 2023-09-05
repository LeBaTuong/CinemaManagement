package view;

import models.*;
import serivce.BillService;
import serivce.ClientService;
import serivce.TicketService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BillView {
    Scanner scanner = new Scanner(System.in);
    TicketService ticketService = new TicketService();
    BillService billService = new BillService();
    ClientService clientService = new ClientService();
    ScheduleView scheduleView = new ScheduleView();
    TicketView ticketView = new TicketView();

    Bill bill = new Bill();

    public void laucher() {
        boolean checkAction = false;
        do {

            System.out.println("===========================================================");
            System.out.println("||                 Menu quản lý Bill                     ||");
            System.out.println("|| ----------------------------------------------------- ||");
            System.out.println("|| Nhấn 1: Xem tất cả Bill                               ||");
            System.out.println("|| Nhấn 2: Xem chi tiết Bill với ID Khách hàng           ||");
            System.out.println("|| Nhấn 3: Quay lại                                      ||");
            System.out.println("===========================================================");

            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 1: {
                    showBill();
                    break;
                }
                case 2: {
                    showBillClient1();
                    //showBillClient();
                    break;
                }
                case 3: {
                    //showBillClient1();
                    break;

                }
                case 4: {
                    CreateBill();
                    break;
                }
                case 5: {
                    deleteBill();
                    break;
                }


            }
        } while (checkAction);

    }

    public void deleteBill() {
        BillView billView = new BillView();
        System.out.println("Nhập id khách hàng");
        long idClient = Long.parseLong(scanner.nextLine());
        billView.showBillClient(idClient);
        System.out.println("Nhập id Bill cần xóa");
        long idBill = Long.parseLong(scanner.nextLine());
        billService.deleteBillByID(idBill);
        System.out.println("đã được xóa thành công");
        //billService.deleteBillDetailByID(id);

    }


    public void CreateBill() {
        Bill bill = new Bill();
        bill.setBillID(System.currentTimeMillis() % 10000);
        boolean checkContinueBilldetail = false;
        do {
            Ticket ticket = new Ticket();
            long ticketID = (System.currentTimeMillis() % 10000);

            ticketView.addTicket();
            ticket.setTicketID(ticketID);
            if (bill.getBilldetailList() == null) {
                Billdetail billdetail = new Billdetail(System.currentTimeMillis() % 10000, bill.getBillID(), ticketID);
                List<Billdetail> billdetails = new ArrayList<>();
                billdetails.add(billdetail);
                bill.setBilldetailList(billdetails);
            } else {
                Billdetail billdetail = new Billdetail(System.currentTimeMillis() % 10000, bill.getBillID(), ticketID);
                bill.getBilldetailList().add(billdetail);
            }
            System.out.println("Bạn có muốn thêm tiếp sản phẩm không:Y/N ");
            String actioncotinue = scanner.nextLine();
            switch (actioncotinue) {
                case "Y": {
                    checkContinueBilldetail = true;
                    break;
                }
                case "N": {
                    checkContinueBilldetail = false;
                    break;
                }
            }

        } while (checkContinueBilldetail);
        System.out.println("Nhập id của bạn: ");
        long clientID = Long.parseLong(scanner.nextLine());
        bill.setClientID(clientID);
        bill.setTotaPrice();
        billService.createBill(bill);

    }
    //public boolean

//    private void showByBillID() {
//        System.out.println("Nhập billID cần show");
//        long id = Long.parseLong(scanner.nextLine());
//        Bill bill = billService.findBill(id);
//        System.out.println("mã hóa đơn: " + bill.getBillID());
//        Client client = clientService.findClientById(bill.getClientID());
//        System.out.println("FullName: " + client.getFullName());
//        double total = 0;
//        int count=0;
//        for(Billdetail b: bill.getBilldetailList()){
//             Ticket t= ticketService.findTicketbyID(b.getTicketID());
//             ticketView.showTicketByID(t.getTicketID());
//             count++;
//              ticketView.showTicketByID(t.getTicketID());
//            System.out.printf("%-20s | %10s | %10s\n", t.getScheduleID(), ot.getQuantity(), ot.getQuantity()*ot.getPrice());
//           //count++;
//
//            scheduleView.showScheduleID(t.getScheduleID());
//
//        }
//        total=count*45000;
//        System.out.println("tổng tiền: " + total);
//    }

    private void showBill() {
        List<Bill> billList = billService.getAllBill();
        System.out.printf("%10s | %20s | %20s  \n", "ID", "Total", "FULLNAME");
        for (Bill bill : billList) {
            Client client = clientService.findClientById(bill.getClientID());
            System.out.printf("%10s | %20s | %20s \n",
                    bill.getBillID(), bill.getTotal(), client.getFullName());
        }
    }

    private void showBillClient(long clientID) {
//        System.out.println("Nhập id Client cần show:");
//        long clientID = Long.parseLong(scanner.nextLine());
        List<Bill> bills = billService.findBillbyClientID(clientID);

        System.out.printf("%10s | %20s | %20s  \n", "ID", "Total", "FULLNAME");
        for (Bill bill : bills) {
            Client client = clientService.findClientById(bill.getClientID());
            System.out.printf("%10s | %20s | %20s \n",
                    bill.getBillID(), bill.getTotal(), client.getFullName());
            System.out.println("Hiển thị ticket cụ thể:");
            ticketView.showTicketByIDByIDClient1(clientID);
        }
    }
    private void showBillClient1() {
        System.out.println("Nhập id Client cần show:");
        long clientID = Long.parseLong(scanner.nextLine());
        List<Bill> bills = billService.findBillbyClientID(clientID);

        System.out.printf("%10s | %20s | %20s  \n", "ID", "Total", "FULLNAME");
        for (Bill bill : bills) {
            Client client = clientService.findClientById(bill.getClientID());
            System.out.printf("%10s | %20s | %20s \n",
                    bill.getBillID(), bill.getTotal(), client.getFullName());
            System.out.println("Hiển thị ticket cụ thể:");
            ticketView.showTicketByIDByIDClient1(clientID);
        }
    }


    public static void main(String[] args) {
        BillView billView = new BillView();
        billView.laucher();
    }
}