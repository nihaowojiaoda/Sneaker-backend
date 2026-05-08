package com.imdat.DTO.respone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class InvoiceRes {
    @NotNull
    private Integer id;

    @NotNull
    private Long totalPrice;

    @NotBlank
    private String status;

    @NotBlank
    private String paymentMethod;

    @NotBlank
    private String shippingAddress;

    @NotBlank
    private String receiverPhone;

    @NotBlank
    private String receiverName;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private List<InvoiceDetailRes> invoiceDetailResList;

    public InvoiceRes(Integer id ,Long totalPrice, String status, String paymentMethod, String shippingAddress, String receiverPhone, String receiverName, LocalDateTime createdAt, List<InvoiceDetailRes> invoiceDetailResList) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.shippingAddress = shippingAddress;
        this.receiverPhone = receiverPhone;
        this.receiverName = receiverName;
        this.createdAt = createdAt;
        this.invoiceDetailResList = invoiceDetailResList;
    }

    public InvoiceRes() {
    }

    public Integer getId() {
        return id;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<InvoiceDetailRes> getInvoiceDetailResList() {
        return invoiceDetailResList;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setInvoiceDetailResList(List<InvoiceDetailRes> invoiceDetailResList) {
        this.invoiceDetailResList = invoiceDetailResList;
    }
}


