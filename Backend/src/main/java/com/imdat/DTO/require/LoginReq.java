package com.imdat.DTO.require;

import jakarta.validation.constraints.NotBlank;

public class LoginReq {
    @NotBlank(message = "Không được để trống tên đăng nhập")
    private String username;

    @NotBlank(message = "Không được để trống mật khẩu")
    private String password;

    public LoginReq(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginReq() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
