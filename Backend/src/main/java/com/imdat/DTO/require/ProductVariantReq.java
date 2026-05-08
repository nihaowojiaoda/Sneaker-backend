package com.imdat.DTO.require;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ProductVariantReq {
    private String color;

    @NotNull(message = "Vui lòng nhập size sản phẩm")
    private Integer size;

    @NotNull(message = "Vui lòng nhập số lượng")
    private Integer stock;

    @NotNull(message = "Vui lòng nhập giá sản phẩm")
    private Long price;

    @NotNull(message = "Vui lòng nhập giá nhập vào sản phẩm")
    private Long importPrice;

    private Integer productId;

    public ProductVariantReq() {}

    public String getColor() {
        return color;
    }

    public Integer getSize() {
        return size;
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

    public Integer getProductId() {
        return productId;
    }


    public ProductVariantReq(String color, Integer size, Integer stock, Long price, Long importPrice, Integer productId) {
        this.color = color;
        this.size = size;
        this.stock = stock;
        this.price = price;
        this.importPrice = importPrice;
        this.productId = productId;
    }
}
