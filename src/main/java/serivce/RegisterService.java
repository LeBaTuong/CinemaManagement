package serivce;

import utils.AppUtils;
import utils.ValidateUtils;

import static utils.AppUtils.getString;
import static view.TotalView.menu;

public class RegisterService {
    static int choice;

    public static String registerCheckUserName(String str) {
        String userName = getString(str);
        if(!ValidateUtils.isEmailValid(userName)){
            System.err.println("Input error(Nhập sai)");
            System.out.println("Please enter again(Mời nhập lại):");
            return registerCheckUserName(str);
        }
        if (isUserNameTaken(userName)) {
            System.err.println("The account already exists(Tài khoản đã tồn tại)");
            System.out.println("Please enter again(Mời nhập lại):");
            return registerCheckUserName(str);
        }

        return userName;
    }
    public static boolean isUserNameTaken(String email) {

        ClientService clientService = ClientService.getInstance();
        return clientService.getAllClient().stream().anyMatch(e -> e.getEmail().equals(email));
    }

    public static String registerCheckPassWord(String str) {
        String passWord = getString(str);
        if (!(passWord.length() >= 6)) {
            System.err.println("Input error(Nhập sai).");
            System.out.println("1. Continue(tiếp tục).");
            System.out.println("0. Go back (Quay lại).");
            choice = AppUtils.getIntWithBound("Enter your choice(Mời chọn):", 0, 1);
            switch (choice) {
                case 1: {
                    registerCheckPassWord(str);
                }
                case 0: {
                    menu();
                }
            }

        } return passWord;
    }
    public static String registerCheckName(String str) {
        String name = getString(str);
        if(!(name.length() >= 6)){
            System.err.println("Input error(Nhập sai).");
            System.out.println("1.Continue( Tiếp tục).");
            System.out.println("0.Go back (Quay lại).");
            choice = AppUtils.getIntWithBound("Enter your choice(Mời chọn):", 0, 1);
            switch (choice){
                case 1: {
                    registerCheckName(str);
                }
                case 0: {
                    menu();
                }
            }
        }
        return name;
    }
}
