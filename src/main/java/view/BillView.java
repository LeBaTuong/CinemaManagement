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
            System.out.println("Menu quản lý user: ");
            System.out.println("Nhập 1.xem danh sách order ");
            System.out.println("Nhập 2. show order");
            System.out.println("Nhập 3. thêm order");
            System.out.println("Nhập 4. sửa sản phẩm theo ID");
            System.out.println("Nhập 5: Xóa sản phẩm");
            System.out.println("Nhập 5: Sắp xếp theo (Vào trong chọn thêm menu: tên/tuổi/giới tính/dob + TĂNG DẦN/GIẢM DẦN) ");
            System.out.println("Nhập 6: Tìm kiếm theo ");

            /**
             System.out.println("Menu quản lý Sản phẩm: ");
             System.out.println("Nhập 1. Xem danh sách ");
             System.out.println("Nhập 2. Thêm user");
             System.out.println("Nhập 3. Sửa user");
             System.out.println("Nhập 4. Xóa user theo ID");
             System.out.println("Nhập 5: Sắp xếp theo (Vào trong chọn thêm menu: tên/tuổi/giới tính/dob + TĂNG DẦN/GIẢM DẦN) ");
             System.out.println("Nhập 6: Tìm kiếm theo ");
             */

            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 1: {
                    showBill();
                    break;
                }
                case 2: {
                    showByBillID();
                    break;
                }
                case 3: {
                    CreateBill();
                    break;

                }
                case 4: {
                    //RevenueBill();
                    break;
                }
                case 5: {
                    //deleteProduct();
                    break;
                }


            }
        } while (checkAction);

    }



    private void CreateBill() {
        Bill bill = new Bill();
        bill.setBillID(System.currentTimeMillis()%10000);
        boolean checkContinueBilldetail= false;
        do{
            Ticket ticket = new Ticket();
            long ticketID = (System.currentTimeMillis()%10000);

            ticketView.addTicket();
            ticket.setTicketID(ticketID);
            if(bill.getBilldetailList()==null){
                Billdetail billdetail = new Billdetail(System.currentTimeMillis()%10000,bill.getBillID(),ticketID);
                List<Billdetail> billdetails = new ArrayList<>();
                billdetails.add(billdetail);
                bill.setBilldetailList(billdetails);
            }else {
                Billdetail billdetail = new Billdetail(System.currentTimeMillis()%10000,bill.getBillID(),ticketID);
                bill.getBilldetailList().add(billdetail);
            }
            System.out.println("Bạn có muốn thêm tiếp sản phẩm không:Y/N ");
            String actioncotinue= scanner.nextLine();
            switch (actioncotinue){
                case "Y": {
                    checkContinueBilldetail= true;
                    break;
                }
                case "N": {
                    checkContinueBilldetail= false;
                    break;
                }
            }

        }while (checkContinueBilldetail);
        System.out.println("Nhập id của bạn: ");
        long clientID = Long.parseLong(scanner.nextLine());
        bill.setClientID(clientID);
        bill.setTotaPrice();
        billService.createBill(bill);

    }
    //public boolean

    private void showByBillID() {
        System.out.println("Nhập billID cần show");
        long id = Long.parseLong(scanner.nextLine());
        Bill bill = billService.findBill(id);
        System.out.println("mã hóa đơn: " + bill.getBillID());
        Client client = clientService.findClientById(bill.getClientID());
        System.out.println("FullName: " + client.getFullName());
        double total = 0;
        int count=0;
        for(Billdetail b: bill.getBilldetailList()){
             Ticket t= ticketService.findTicketbyID(b.getTicketID());
             ticketView.showTicketByID(t.getTicketID());
             count++;
              //ticketView.showTicketByID(t.getTicketID());
//            System.out.printf("%-20s | %10s | %10s\n", t.getScheduleID(), ot.getQuantity(), ot.getQuantity()*ot.getPrice());
//           //count++;

           // scheduleView.showScheduleID(t.getScheduleID());

        }
        total=count*45000;
        System.out.println("tổng tiền: " + total);
    }

    private void showBill() {
        List<Bill> billList = billService.getAllBill();
        System.out.printf("%10s | %20s | %20s  \n", "ID", "Total", "FULLNAME");
        for (Bill bill : billList) {
            Client client = clientService.findClientById(bill.getClientID());
            System.out.printf("%10s | %20s | %20s \n",
                    bill.getBillID(), bill.getTotal(), client.getFullName());
        }
    }


    public static void main(String[] args) {
        BillView billView = new BillView();
        billView.laucher();
    }
}