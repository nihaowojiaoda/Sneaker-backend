package com.imdat.DTO;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductDetailDTO {
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 5, message = "Tên sản phẩm phải có ít nhất 5 kí tự")
    private String productName;

    @NotBlank(message = "Mô tả sản phẩm không được để trống")
    private String productDescription;

    private Integer brandId;

    private Integer categoryId;

    @Lob
    @NotNull(message = "Vui lòng chọn ảnh sản phẩm")
    private byte[] data;

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public byte[] getData() {
        return data;
    }

    public ProductDetailDTO(String productName, String productDescription, Integer brandId, Integer categoryId, @NotNull(message = "Vui lòng chọn ảnh sản phẩm") byte[] data) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.data = data;
    }
}
