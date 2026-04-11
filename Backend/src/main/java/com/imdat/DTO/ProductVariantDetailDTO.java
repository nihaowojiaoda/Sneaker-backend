package com.imdat.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ProductVariantDetailDTO {
    private String color;

    @NotNull(message = "Vui lòng nhập size sản phẩm")
    private Integer size;

    @NotNull(message = "Vui lòng nhập số lượng")
    private Integer stock;

    @NotNull(message = "Vui lòng nhập giá sản phẩm")
    private Double price;

    @NotNull(message = "Vui lòng nhập giá nhập vào sản phẩm")
    private Double importPrice;

    private Integer productId;

    @NotNull(message = "Vui lòng chọn ảnh cho sản phẩm")
    private List<byte[]> datas;

    public ProductVariantDetailDTO() {}

    public String getColor() {
        return color;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getStock() {
        return stock;
    }

    public Double getPrice() {
        return price;
    }

    public Double getImportPrice() {
        return importPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public List<byte[]> getDatas() {
        return datas;
    }

    public ProductVariantDetailDTO(String color, Integer size, Integer stock, Double price, Double importPrice, Integer productId, List<byte[]> datas) {
        this.color = color;
        this.size = size;
        this.stock = stock;
        this.price = price;
        this.importPrice = importPrice;
        this.productId = productId;
        this.datas = datas;
    }
}
