package com.imdat.DTO;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class AdminUserDetailDTO {
    private String username;
    private String phoneNumber;
    private String address;
    private String email;
    private String role;
    private LocalDateTime createAt;
    private LocalDateTime modifyAt;

    public AdminUserDetailDTO(String username, String phoneNumber, String address, String email, String role,
        LocalDateTime createAt, LocalDateTime modifyAt) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.role = role;
        this.createAt = createAt;
        this.modifyAt = modifyAt;
    }

    public AdminUserDetailDTO() {}

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

    public String getRole() {
        return role;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getModifyAt() {
        return modifyAt;
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

    public void setRole(String role) {
        this.role = role;
    }
}
