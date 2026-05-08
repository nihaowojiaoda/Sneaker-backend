package com.imdat.DTO.respone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ProductVariantRes {
    @NotNull
    private Integer id;

    @NotBlank
    private String color;

    @NotNull
    private Integer size;

    @NotNull
    private Integer stock;

    @NotNull
    private Boolean isActive = true;

    @NotNull
    private Long price;

    @NotNull
    private Long importPrice;

    public ProductVariantRes(Integer id ,String color, Integer size, Boolean isActive, Integer stock, Long importPrice, Long price) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.isActive = isActive;
        this.stock = stock;
        this.importPrice = importPrice;
        this.price = price;
    }

    public ProductVariantRes() {
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setImportPrice(Long importPrice) {
        this.importPrice = importPrice;
    }

    public String getColor() {
        return color;
    }

    public Integer getSize() {
        return size;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Integer getStock() {
        return stock;
    }

    public Long getPrice() {
        return price;
    }

    public Long getImportPrice() {
        return importPrice;
    }
}
