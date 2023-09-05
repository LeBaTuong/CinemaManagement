package view;

import java.util.Scanner;

public class RegisterView {
    private final String fileClient = "./data/clients.txt";


    private Scanner scanner = new Scanner(System.in);

    public void Register() {
//        List<Client> clients= ClientService.getInstance().getAllClient();
//        long id = System.currentTimeMillis() % 100000;
//        String fullName = registerCheckName("Enter name: The name consists of 6 characters(Nhập tên: tên gồm 6 kí tự):");
//        String email = registerCheckUserName("Enter the account name: The account consists of 6 uppercase letters and must be alphabetic(Nhập tên tài khoản: tài khoản gồm 6 kí tử lên và phải là chữ cái):");
//        String password = registerCheckPassWord("Enter the password: The password consists of 6 characters(Nhập mật khẩu: mật khẩu gồm 6 kí tự):");
//
//        LocalDate dob = DateUtils.parseDate();
//
//        String phone = getStringWithPattern("Enter phone(Nhập số điện thoại)", AppConstant.REGEX_PHONE);
//
//        Client newClient = new Client(id, fullName, email, password, dob, phone);
//        clients.add(newClient);
//        FileUtils.writerData(fileClient, clients);
//        System.out.println("User has been created successfully(Người dùng đã được tạo thành công)!");
    }
}
