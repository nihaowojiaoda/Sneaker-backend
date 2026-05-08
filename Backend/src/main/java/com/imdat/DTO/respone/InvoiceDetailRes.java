package com.imdat.DTO.respone;

import jakarta.validation.constraints.NotNull;


public class InvoiceDetailRes {
    @NotNull
    private Integer id;

    @NotNull
    private Long amountPrice;

    @NotNull
    private Integer quantity;

    @NotNull
    private ProductVariantRes productVariantRes;

    public InvoiceDetailRes(Integer id, Long amountPrice, Integer quantity, ProductVariantRes productVariantRes) {
        this.id = id;
        this.amountPrice = amountPrice;
        this.quantity = quantity;
        this.productVariantRes = productVariantRes;
    }

    public InvoiceDetailRes() {
    }

    public Integer getId() {
        return id;
    }

    public Long getAmountPrice() {
        return amountPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ProductVariantRes getProductVariantResList() {
        return productVariantRes;
    }

    public void setAmountPrice(Long amountPrice) {
        this.amountPrice = amountPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setProductVariantRes(ProductVariantRes productVariantRes) {
        this.productVariantRes = productVariantRes;
    }
}
