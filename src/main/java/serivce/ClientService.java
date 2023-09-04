package serivce;

import eNum.EAuth;
import models.Client;
import models.Schedule;
import utils.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static serivce.RegisterService.*;
import static utils.AppUtils.getStringWithPattern;
import static utils.PasswordUtils.generatePassword;

public class ClientService  {
    static Scanner scanner = new Scanner(System.in);
    private static final String fileClient = "./data/clients.txt";
    private static ClientService instance;

    public static ClientService getInstance() {
        if (instance == null) {
            instance = new ClientService();
        }
        return instance;
    }


    public static List<Client> getAllClient() {
        return FileUtils.readData(fileClient, Client.class);
    }


    public List<Client> searcClient(String kw) {
        List<Client> clients = getAllClient();
        List<Client> result = new ArrayList<>();
        result = clients.stream().filter(client -> searchClientByKW(client, kw)).collect(Collectors.toList());
        return result;
    }

    public boolean searchClientByKW(Client client, String kw) {
        return client.getFullName().toLowerCase().contains(kw.toLowerCase()) ||
                client.geteAuth().toString().toLowerCase().contains(kw.toLowerCase());
    }


    public void updateClient() {

    }
    public Client findClientById(long id) {
        List<Client> clients =getAllClient();
//        for(Order o: orders){
//            if(o.getId()==id){
//                return o;
//            }
//        }
        Client result= clients.stream().filter(client -> client.getId()==id).findFirst().orElseThrow(null);
        return result;
    }


    public void deleteClient(long id) {
        List<Client> clients = getAllClient();
        clients.remove(clients.stream().filter(client -> client.getId() == id).findFirst().orElseThrow(null));
        FileUtils.writerData(fileClient, clients);
        System.out.println("client with id: " + id + " has been removed successfully");

    }


    public static void createClinet() {
        //long id, String fullName, String email, long password, String phoneNumber, LocalDate dob
        List<Client> clients = getAllClient();

        String fullName = registerCheckName("Enter name: The name consists of 6 characters(Nhập tên: tên gồm 6 kí tự):");

           String email = registerCheckUserName("Enter the account email: The account consists of 6 uppercase letters and must be alphabetic(Nhập tên tài khoản: tài khoản gồm 6 kí tử lên và phải là chữ cái):");

        String password = registerCheckPassWord("Enter the password: The password consists of 6 characters(Nhập mật khẩu: mật khẩu gồm 6 kí tự):");

        LocalDate dob = AppUtils.getUserDateOfBirth();

        String phone = getStringWithPattern("Enter phone(Nhập số điện thoại)", AppConstant.REGEX_PHONE);
        System.out.println("Enter Auth: ");
        for (EAuth eAuth : EAuth.values()) {
            System.out.println(eAuth.getName() + " : " + eAuth.getId());
        }
        int idAuth  = Integer.parseInt(scanner.nextLine());
        EAuth eAuth = EAuth.findById(idAuth);

        Client newClient = new Client(System.currentTimeMillis() % 100000, fullName, email, generatePassword(password), phone, dob, eAuth);

        clients.add(newClient);
        FileUtils.writerData(fileClient, clients);
    }
    public static int checkUsernamePassword(String email, String password) {
        List<Client> clientList = ClientService.getAllClient();
//
//        return clientList
//                .stream()
//                .anyMatch(client -> client.getEmail().equals(email) && PasswordUtils.isValid(password, client.getPassword()));
            Client client = clientList.stream().filter(s->s.getEmail().equals(email)).findFirst().orElse(null);
            if(client!=null&& PasswordUtils.isValid(password, client.getPassword())){
                if(client.geteAuth().equals(EAuth.CLIENT)){
                    return 1;
                }else {
                    return 0;
                }
            }else return 2;
    }
}
