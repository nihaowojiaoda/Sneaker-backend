package com.imdat.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class ChangePasswordRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String otp;

    @NotNull
    private LocalDateTime expiryDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public ChangePasswordRequest(String otp, LocalDateTime expiryDate) {
        this.otp = otp;
        this.expiryDate = expiryDate;
    }

    public ChangePasswordRequest() {}

    public String getOtp() {
        return otp;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
