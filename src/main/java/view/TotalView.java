package view;

import serivce.LoginService;
import utils.AppUtils;

import static serivce.ClientService.createClinet;

public class TotalView {
    //static ClientView clientView = new ClientView();
    static int choice;


    public static void menu() {
        menuTotal();
        choice = AppUtils.getIntWithBound("              Enter your choice(Mời chọn chức năng):", 0, 2);
        switch (choice) {
            case 1: {
                LoginService.loginService();
                break;
            }
            case 2: {
                createClinet();
                //ClientView.showClinet();
                //menu();
            }
            case 0: {
                System.exit(1);
            }
        }
    }

    public static void menuTotal() {
        System.out.println(" ╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println(" ║                                                 Welcome to the CINEMA                                                           ║");
        System.out.println(" ╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.println(" ║                                                                                                                                 ║");
        System.out.println(" ║                                                 1. LogIn(Đăng nhập)                                                             ║");
        System.out.println(" ║                                                 2. Register(Đăng kí)                                                            ║");
        System.out.println(" ║                                                 0. Exit(Thoát chương trình)                                                     ║");
        System.out.println(" ║                                                                                                                                 ║");
        System.out.println(" ╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    public static void main(String[] args) {
        menu();

    }

}

