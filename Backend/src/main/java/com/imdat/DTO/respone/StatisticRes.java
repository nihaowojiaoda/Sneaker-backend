package com.imdat.DTO.respone;

import com.imdat.entity.ProductVariant;

import java.util.List;

public class StatisticRes {
    private Double totalRevenue;

    private Long totalOrders;

    private Long totalUsers;

    private List<InvoiceRes> invoiceResList;

    private List<ProductVariantRes> productVariantsList;

    public StatisticRes(Double totalRevenue, Long totalOrders, Long totalUsers, List<InvoiceRes> invoiceResList, List<ProductVariantRes> productVariantResList) {
        this.totalRevenue = totalRevenue;
        this.totalOrders = totalOrders;
        this.totalUsers = totalUsers;
        this.invoiceResList = invoiceResList;
        this.productVariantsList = productVariantResList;
    }

    public StatisticRes() {
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public Long getTotalOrders() {
        return totalOrders;
    }

    public Long getTotalUsers() {
        return totalUsers;
    }

    public List<InvoiceRes> getInvoiceResList() {
        return invoiceResList;
    }

    public List<ProductVariantRes> getProductVariantsList() {
        return productVariantsList;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public void setTotalOrders(Long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public void setTotalUsers(Long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public void setInvoiceResList(List<InvoiceRes> invoiceResList) {
        this.invoiceResList = invoiceResList;
    }

    public void setProductVariantsList(List<ProductVariantRes> productVariantsList) {
        this.productVariantsList = productVariantsList;
    }
}
