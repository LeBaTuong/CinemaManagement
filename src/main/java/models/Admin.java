package models;

import eNum.EAuth;

import java.time.LocalDate;

public class Admin {
    private long id;
    private String fullName;
    private String email;
    private long password;
    private String phoneNumber;
    private LocalDate dob;
    private EAuth eAuth;

    public Admin() {
    }

    public Admin(String email, long password) {
        this.email = email;
        this.password = password;
    }

    public Admin(long id, String fullName, String email, long password, String phoneNumber, LocalDate dob, EAuth eAuth) {
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

    public long getPassword() {
        return password;
    }

    public void setPassword(long password) {
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
}
