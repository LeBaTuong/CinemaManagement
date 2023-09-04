package view;

import serivce.LoginService;

import java.util.Scanner;

public class ListView {
    static boolean checkActionMenu = true;
    static boolean repeatMenu = true;
    static boolean isLogout = false;
    static Scanner scanner = new Scanner(System.in);
    static ClientView clientView = new ClientView();
    static ScheduleView scheduleView = new ScheduleView();
    static MovieView movieView = new MovieView();
    static TicketView ticketView = new TicketView();
    static LoginService loginService = new LoginService();
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
                checkActionMenu =false;
                isLogout =true;
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
            System.out.println("|| Nhấn 4: Quản lý Vé                                    ||");
            System.out.println("|| Nhấn 5: Quản lý VÉ                                    ||");
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
                ticketView.laucher();
                break;
            case 5:
//                TicketView ticketView = new TicketView();
//                ticketView.TickettView();
                break;
            case 6:
              logout();
                break;

            default:
                System.out.println("Nhập không đúng, vui lòng nhập lại !!!");
                continue;
        }
        if (!isLogout){
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
    public void showClientMenu() {
        do {
            checkActionMenu = true;
            System.out.println("===========================================================");
            System.out.println("||                 Menu Khách hàng                       ||");
            System.out.println("|| ----------------------------------------------------- ||");
            System.out.println("|| Nhấn 1: Thêm vé                                       ||");
            System.out.println("|| Nhấn 2: xóa vé                                        ||");
            System.out.println("|| Nhấn 3: tìm kiếm lịch phim                            ||");
            System.out.println("|| Nhấn 4: xem lịch sử                                   ||");
            System.out.println("|| Nhấn 5: Đăng xuất                                     ||");
            System.out.println("===========================================================");
            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
//                case 1:
//                    CustomerView customerView = new CustomerView();
//                    customerView.CustommerView(2);
//                    break;
//                case 2:
//                    GymPackagesView gymPackagesView = new GymPackagesView();
//                    gymPackagesView.GymPackagess(2);
//                    break;
//                case 3:
//                    ProductView productView = new ProductView();
//                    productView.ProducttView(2);
//                    break;
                case 4:
                    logout();
                    break;
//                case 5:
//                    TicketView ticketView = new TicketView();
//                    ticketView.TickettView();
//                    break;
                default:
                    System.out.println("Nhập không đúng, vui lòng nhập lại !!!");
                    continue;
            }
            if (!isLogout){
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
