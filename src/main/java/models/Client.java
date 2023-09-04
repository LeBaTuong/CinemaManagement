package models;

import eNum.EAuth;
import serivce.IParseModel;
import utils.DateUtils;

import java.time.LocalDate;

public class Client implements IParseModel<Client> {

    private long id;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate dob;
    private EAuth eAuth;

    public Client() {
    }

    public Client(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Client(long id, String fullName, String email, String password, String phoneNumber, LocalDate dob) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
    }

    public Client(long id, String fullName, String email, String password, String phoneNumber, LocalDate dob, EAuth eAuth) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.eAuth = eAuth;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public EAuth geteAuth() {
        return eAuth;
    }

    public void seteAuth(EAuth eAuth) {
        this.eAuth = eAuth;
    }


    @Override
    public Client parse(String line) {
        //long id,String fullName, String email, long password, String phoneNumber, LocalDate dob, EAuth eAuth
        String[] item = line.split(",");
        Client client = new Client(Long.parseLong(item[0]), item[1], item[2], item[3], item[4], DateUtils.parseDate(item[5]), EAuth.valueOf(item[6]));
        return client;
    }

    @Override
    public String toString() {
       //long id, String fullName, String email, long password, String phoneNumber, LocalDate dob, EAuth eAuth
           return String.format("%s,%s,%s,%s,%s,%s,%s", this.id, this.fullName, this.email,this.password,this.phoneNumber, DateUtils.fomatLocalDate(dob), this.eAuth);
        }
    }



