package view;

import models.Client;
import serivce.ClientService;
import utils.DateUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ClientView {
    Scanner scanner = new Scanner(System.in);
    static ClientService iClientService;

    public ClientView() {
        iClientService = new ClientService();
    }

    public void laucher() {
        boolean checkAction = false;
        do {

            System.out.println("===========================================================");
            System.out.println("||                 Menu quản lý Khách hàng               ||");
            System.out.println("|| ----------------------------------------------------- ||");
            System.out.println("|| Nhấn 1: xem danh sách tài khoản                       ||");
            System.out.println("|| Nhấn 2: Tìm kiếm tài khoản khách hàng                 ||");
            System.out.println("|| Nhấn 3: Xóa tài khoản khách hàng                      ||");
            System.out.println("|| Nhấn 4: Quay lại                                      ||");
            System.out.println("===========================================================");

            int actionMenu = Integer.parseInt(scanner.nextLine());
            switch (actionMenu) {
                case 1: {
                    showClinet();
                    break;
                }
                case 2: {
                    searchClient();
                    break;
                }
                case 3: {
                    removeClient();
                    break;
                }
                case 4: {
                    addClient();
                }
                case 5: {
                    showClientByEmail();
                }

            }
        } while (checkAction);


    }

    private void addClient() {
        System.out.println("Enter fullName: ");
        String name = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("Enter phoneNumber: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter birthday: (dd-MM-yyyy)");
        LocalDate dob = DateUtils.parseDate(scanner.nextLine());
        System.out.println("Enter Auth: ");
        String auth = scanner.nextLine();
        Client client = new Client(System.currentTimeMillis() % 100000, name, email, password, phoneNumber, dob, null);

        showClinet();

    }

    private void removeClient() {
        System.out.println("Enter the ID you want to remove: ");
        long id = Long.parseLong(scanner.nextLine());
        iClientService.deleteClient(id);
        showClinet();

    }

    private void searchClient() {
        int choose = 0;
        do {
            System.out.println("Search Client: ");
            System.out.println("1. Search FullName ");
            System.out.println("2. Search Auth");
            choose = Integer.parseInt(scanner.nextLine());
            switch (choose) {
                case 1: {
                    System.out.println("Enter the name you want to search: ");
                    String name = scanner.nextLine();
                    List<Client> clients = iClientService.searcClient(name);
                    for (Client c : clients) {
                        System.out.printf("%10s | %30s | %30s | %10s | %10s | %20s | %20s \n",
                                c.getId(), c.getFullName(), c.getEmail(), c.getPassword(), c.getPhoneNumber(), DateUtils.fomatLocalDate(c.getDob()), c.geteAuth());

                    }
                    break;
                }
                case 2: {
                    System.out.println("Enter the Auth you want to search: ");
                    String eAuth = scanner.nextLine();
                    List<Client> clients = iClientService.searcClient(eAuth);
                    for (Client c : clients) {
                        System.out.printf("%10s | %30s | %30s | %10s | %10s | %20s | %20s \n",
                                c.getId(), c.getFullName(), c.getEmail(), c.getPassword(), c.getPhoneNumber(), DateUtils.fomatLocalDate(c.getDob()), c.geteAuth());

                    }
                    break;
                }
                case 0: {
                }
                break;
            }
        } while (choose != 0);
    }

    public static void showClinet() {
        //long id,String fullName, String email, long password, String phoneNumber, LocalDate dob, EAuth eAuth
        List<Client> clients = iClientService.getAllClient();
        System.out.printf("%10s | %30s | %30s | %10s | %10s | %20s | %20s \n", "ID", "FULLName", "EMAIL", "PASSWORD", "PHONENAME", "DOB", "AUTH");
        for (Client c : clients) {
            System.out.printf("%10s | %30s | %30s | %10s | %10s | %20s | %20s \n",
                    c.getId(), c.getFullName(), c.getEmail(), c.getPassword(), c.getPhoneNumber(), DateUtils.fomatLocalDate(c.getDob()), c.geteAuth());

        }
    }

    public void showClientByEmail() {
        System.out.println("Enter the Auth you want to search: ");
        String email = scanner.nextLine();
        List<Client> clients = iClientService.searcClient(email);
        for (Client c : clients) {
            System.out.printf("%10s | %30s | %30s | %10s | %10s | %20s | %20s \n",
                    c.getId(), c.getFullName(), c.getEmail(), c.getPassword(), c.getPhoneNumber(), DateUtils.fomatLocalDate(c.getDob()), c.geteAuth());

        }
    }

    public static void main(String[] args) {
        ClientView clientView = new ClientView();
        clientView.laucher();
    }

}
