package view;

import serivce.*;
import utils.DateUtils;

import java.time.LocalDate;
import java.util.Scanner;

public class RevenueView {
    Scanner scanner = new Scanner(System.in);
    TicketService ticketService = new TicketService();
    MovieService movieService = new MovieService();
    ScheduleService scheduleService = new ScheduleService();
    RoomService roomService = new RoomService();
    SeatService seatService = new SeatService();
    ClientService clientService = new ClientService();
    ScheduleView scheduleView= new ScheduleView();
    public void laucher() {
        boolean checkAction = false;
        do {
            System.out.println("Menu doanh thu: ");
            System.out.println("Nhập 1.tổng doanh thu ");
            System.out.println("Nhập 2. doanh thu ngày");
            System.out.println("Nhập 3. doanh thu tháng");
            System.out.println("Nhập 4. doanh thu năm");
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
                    RevenueTotal();
                    break;
                }
                case 2: {
                    RevenuTotalDay();
                    break;
                }
                case 3: {
                    RevenueTotalMonth();
                    break;

                }
                case 4: {
                    RevenueTotalYear();
                    break;
                }
                case 5: {
                    RevenueTotalYear();
                    break;
                }


            }
        } while (checkAction);

    }
    private void RevenueTotal() {
        System.out.println(ticketService.total());
    }
    private void RevenueTotalMonth() {
        System.out.println("Nhập tháng mà bạn muốn show doanh thu");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.println(ticketService.totalmonth(month));
    }
    private void RevenuTotalDay(){
        System.out.println("Nhập ngày chiếu: (đ-MM-YYYY)");
        LocalDate dob = DateUtils.parseDate(scanner.nextLine());
        System.out.println(ticketService.totalday(dob));
    }
    private void RevenueTotalYear() {
        System.out.println("Nhập tháng mà bạn muốn show doanh thu");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println(ticketService.totalYear(year));
    }

    public static void main(String[] args) {
        RevenueView revenueView =new RevenueView();
        revenueView.laucher();
    }
}
