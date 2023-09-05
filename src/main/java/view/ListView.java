package view;

import serivce.ClientService;
import serivce.LoginService;

import java.util.Scanner;

public class ListView {
    static boolean checkActionMenu = true;
    static boolean repeatMenu = true;
    static boolean isLogout = false;
    static ClientService clientService = new ClientService();
    static Scanner scanner = new Scanner(System.in);
    static ClientView clientView = new ClientView();
    static ScheduleView scheduleView = new ScheduleView();
    static MovieView movieView = new MovieView();
    static TicketView ticketView = new TicketView();
    static BillView billView = new BillView();
    static LoginService loginService = new LoginService();
    static RevenueView revenueView = new RevenueView();

    private static void logout() {
        System.out.println("Logout thành công");
        System.out.println("Ban có muốn tiếp tục hay không: ");
        System.out.println("Nhập 1. Tiếp tục");
        System.out.println("Nhập 2. Kết thúc");
        int actionMenuContinue = Integer.parseInt(scanner.nextLine());
        switch (actionMenuContinue) {
            case 1:
                LoginService.loginService();
                break;
            case 2:
                System.out.println("Bạn đã thoát khỏi hệ thống !!!");
                checkActionMenu = false;
                isLogout = true;
                break;
            default:
                System.out.println("Không đúng lệnh, vui lòng nhập lại:");
        }
    }

    public static void showAdminMenu() {
        do {
            System.out.println("===========================================================");
            System.out.println("||                 Menu Quản lý (ADMIN)                  ||");
            System.out.println("|| ----------------------------------------------------- ||");
            System.out.println("|| Nhấn 1: Quản lý Thông tin khách hàng                  ||");
            System.out.println("|| Nhấn 2: Quản lý Lịch chiếu                            ||");
            System.out.println("|| Nhấn 3: Quản lý Movie                                 ||");
            System.out.println("|| Nhấn 4: Quản lý Bill                                   ||");
            System.out.println("|| Nhấn 5: Quản lý doanh thu                             ||");
            System.out.println("|| Nhấn 6: Đăng xuất                                     ||");
            System.out.println("===========================================================");
            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 1:
                    clientView.laucher();
                    break;
                case 2:
                    scheduleView.laucher();
                    break;
                case 3:
                    movieView.laucher();
                    break;
                case 4:
                    billView.laucher();
                    break;
                case 5:
                    revenueView.laucher();
                    break;
                case 6:
                    logout();
                    break;

                default:
                    System.out.println("Nhập không đúng, vui lòng nhập lại !!!");
                    continue;
            }
            if (!isLogout) {
                do {
                    System.out.println("Ban có muốn tiếp tục hay không: ");
                    System.out.println("Nhập 1. Tiếp tục");
                    System.out.println("Nhập 2. Kết thúc");
                    int actionMenuContinue = Integer.parseInt(scanner.nextLine());
                    switch (actionMenuContinue) {
                        case 1:
                            checkActionMenu = true;
                            repeatMenu = false;
                            break;
                        case 2:
                            checkActionMenu = false;
                            repeatMenu = false;
                            break;
                        default:
                            System.out.println("Không đúng lệnh, vui lòng nhập lại:");
                    }
                } while (repeatMenu);

            }
        } while (checkActionMenu);
    }

    public static void showClientMenu() {
        do {
            checkActionMenu = true;
            System.out.println("===========================================================");
            System.out.println("||                 Menu Khách hàng                       ||");
            System.out.println("|| ----------------------------------------------------- ||");
            System.out.println("|| Nhấn 1: Thêm vé                                       ||");
            System.out.println("|| Nhấn 2: Hủy vé                                        ||");
            System.out.println("|| Nhấn 3: xem lịch chiếu phim                           ||");
            System.out.println("|| Nhấn 4: xem lịch sử đã đặt trước đây                  ||");
            System.out.println("|| Nhấn 5: Sửa profile                                   ||");
            System.out.println("|| Nhấn 6: xem profile của bạn                           ||");
            System.out.println("|| Nhấn 7: Đăng xuất                                     ||");
            System.out.println("===========================================================");
            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 1:
                    billView.CreateBill();
                    break;
                case 2:
                    billView.deleteBill();
                    break;
                case 3:
                    scheduleView.showSchedule();
                    break;
                case 4:
                    ticketView.showTicketByIDByIDClient();
                    break;
                case 5:
                    clientService.updateClient();
                    break;
                case 6:
                    clientView.showClientByEmail();
                    break;
                case 7:
                    logout();
                    break;
                default:
                    System.out.println("Nhập không đúng, vui lòng nhập lại !!!");
                    continue;
            }
            if (!isLogout) {
                do {
                    System.out.println("Ban có muốn tiếp tục hay không: ");
                    System.out.println("Nhập 1. Tiếp tục");
                    System.out.println("Nhập 2. Kết thúc");
                    int actionMenuContinue = Integer.parseInt(scanner.nextLine());
                    switch (actionMenuContinue) {
                        case 1:
                            checkActionMenu = true;
                            repeatMenu = false;
                            break;
                        case 2:
                            checkActionMenu = false;
                            repeatMenu = false;
                            break;
                        default:
                            System.out.println("Không đúng lệnh, vui lòng nhập lại:");
                    }
                } while (repeatMenu);
            }
        } while (checkActionMenu);
    }
}
