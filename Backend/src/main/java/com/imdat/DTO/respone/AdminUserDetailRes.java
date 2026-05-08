package com.imdat.DTO.respone;

import java.time.LocalDateTime;

public class AdminUserDetailRes {
    private Integer id;
    private String username;
    private String phoneNumber;
    private String address;
    private String email;
    private String role;
    private Boolean isActive;
    private LocalDateTime createAt;
    private LocalDateTime modifyAt;

    public AdminUserDetailRes(Integer id,String username, String phoneNumber, String address, String email, String role,
                              LocalDateTime createAt, LocalDateTime modifyAt, Boolean isActive) {
        this.id = id;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.role = role;
        this.createAt = createAt;
        this.modifyAt = modifyAt;
        this.isActive = isActive;
    }

    public AdminUserDetailRes() {}

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

    public Boolean getIsActive() {return isActive;}

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

    public void setIsActive(Boolean isActive) {this.isActive = isActive;}

    public void setId(Integer id) {
        this.id = id;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public void setModifyAt(LocalDateTime modifyAt) {
        this.modifyAt = modifyAt;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getActive() {
        return isActive;
    }
}
