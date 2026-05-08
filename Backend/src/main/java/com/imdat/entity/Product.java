package com.imdat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String productName;

    @NotBlank
    private String description;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductVariant> productVariants = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    public Product(String productName, String description, Category category, Brand brand) {
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.brand = brand;
    }

    public Product() {
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public Brand getBrand() {
        return brand;
    }

    public Category getCategory() {
        return category;
    }

    public List<ProductVariant> getProductVariants() {
        return productVariants;
    }

    public void setProductVariants(ProductVariant productVariants) {
        this.productVariants.add(productVariants);
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void setProductVariants(List<ProductVariant> productVariants) {
        this.productVariants = productVariants;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Image> getImages() {
        return images;
    }
}
