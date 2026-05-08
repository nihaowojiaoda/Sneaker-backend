package com.imdat.DTO.respone;

import com.imdat.entity.Brand;
import com.imdat.entity.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ProductRes {
    @NotNull
    private Integer id;

    @NotBlank
    private String productName;

    @NotBlank
    private String description;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandRes brandRes;

    private CategoryRes categoryRes;

    @NotNull
    private List<ProductVariantRes> productVariantRes;

    @NotNull
    private List<ImageRes> imageRes;

    public ProductRes(Integer id ,String productName, String description, BrandRes brandRes, CategoryRes categoryRes, List<ProductVariantRes> productVariantRes, List<ImageRes> imageRes) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.brandRes = brandRes;
        this.categoryRes = categoryRes;
        this.productVariantRes = productVariantRes;
        this.imageRes = imageRes;
    }

    public ProductRes() {
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public BrandRes getBrand() {
        return this.brandRes;
    }

    public CategoryRes getCategory() {
        return categoryRes;
    }

    public List<ProductVariantRes> getProductVariantRes() {
        return productVariantRes;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBrand(BrandRes brandRes) {
        this.brandRes = brandRes;
    }

    public void setCategory(CategoryRes categoryRes) {
        this.categoryRes = categoryRes;
    }

    public void setProductVariantRes(List<ProductVariantRes> productVariantRes) {
        this.productVariantRes = productVariantRes;
    }

    public Integer getId() {
        return id;
    }

    public BrandRes getBrandRes() {
        return brandRes;
    }

    public CategoryRes getCategoryRes() {
        return categoryRes;
    }

    public List<ImageRes> getImageRes() {
        return imageRes;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBrandRes(BrandRes brandRes) {
        this.brandRes = brandRes;
    }

    public void setCategoryRes(CategoryRes categoryRes) {
        this.categoryRes = categoryRes;
    }

    public void setImageRes(List<ImageRes> imageRes) {
        this.imageRes = imageRes;
    }
}
