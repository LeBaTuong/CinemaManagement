package serivce;

import eNum.EAuth;
import models.Client;
import utils.AuthUtils;
import view.ListView;
import view.RevenueView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static eNum.EAuth.CLIENT;
import static serivce.ClientService.checkUsernamePassword;

public class LoginService {
    static RevenueView revenueView = new RevenueView();
    static Scanner scanner = new Scanner(System.in);
    static ClientService iClientServic;
    static List<Client> listGet = ClientService.getAllClient();
    static ListView listView;


    public static void loginService() {
        System.out.println("Nhập email: ");
        String email = scanner.nextLine();
        System.out.println("Nhập password:");
        String password = scanner.nextLine();
        //Client client = new Client();

//       if(iClientService.checkUsernamePassword(email, password)){
//           Client client = new Client();
//           AuthUtils.setClientAuthentication(client);
//           if(client.geteAuth().equals(CLIENT));

        if (checkUsernamePassword(email, password) == 1) {
            System.out.println("Xin chào Quý khách hàng đã đến Cinema");
            ListView.showClientMenu();
        } else if (checkUsernamePassword(email, password) == 0) {
            System.out.println("xin chào Admin");
            ListView.showAdminMenu();
        } else System.out.println("mi đã nhập sai");
    }

}
