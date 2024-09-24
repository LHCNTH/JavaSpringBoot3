package com.llhtdqc.spring_boot_3.DTO.Request;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UserCreationRequest {
    private String userName;
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String passWord;
    private String firstName;
    private String lastName;
    private LocalDate dob;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
