package com.imdat.DTO;

public class UserDetailDTO {
    private String username;
    private String phoneNumber;
    private String address;
    private String email;

    public UserDetailDTO(String username, String phoneNumber, String address, String email) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }

    public UserDetailDTO() {}

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
