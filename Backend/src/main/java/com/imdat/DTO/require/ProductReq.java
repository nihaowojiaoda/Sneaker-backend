package com.imdat.DTO.require;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ProductReq {
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(min = 5, message = "Tên sản phẩm phải có ít nhất 5 kí tự")
    private String productName;

    @NotBlank(message = "Mô tả sản phẩm không được để trống")
    private String productDescription;

    private Integer brandId;

    private Integer categoryId;

    private List<MultipartFile> files;

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

    public List<MultipartFile> getFiles() {
        return files;
    }

    public ProductReq(String productName, String productDescription, Integer brandId, Integer categoryId, List<MultipartFile> files) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.files = files;
    }
}
