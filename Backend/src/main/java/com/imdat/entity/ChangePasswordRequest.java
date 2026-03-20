package com.imdat.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ChangePasswordRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private String otp;

    private LocalDateTime expiryDate;

    public ChangePasswordRequest() {}
}
