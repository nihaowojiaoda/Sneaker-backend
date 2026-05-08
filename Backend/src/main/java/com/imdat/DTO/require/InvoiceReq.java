package com.imdat.DTO.require;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class InvoiceReq {
    @NotNull(message = "Trạng thái đơn hàng không được để trống")
    private String status;

    @NotNull(message = "Vui lòng chọn phương thức thanh toán")
    private String paymentMethod;

    @NotNull(message = "Vui lòng nhập địa chỉ giao hàng")
    private String shippingAddress;

    @NotNull(message = "Vui lòng nhập số điện thoại người nhận")
    @Pattern(regexp = "^(0|\\+84)(\\d{9})$", message = "SDT không đúng định dạng")
    private String receivePhone;

    @NotNull(message = "Vui lòng nhập tên người nhận")
    private String receiveName;

    public InvoiceReq() {}

    public InvoiceReq(String status, String paymentMethod, String shippingAddress, String receivePhone, String receiveName) {
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.shippingAddress = shippingAddress;
        this.receivePhone = receivePhone;
        this.receiveName = receiveName;
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

    public String getReceivePhone() {
        return receivePhone;
    }

    public String getReceiveName() {
        return receiveName;
    }
}
