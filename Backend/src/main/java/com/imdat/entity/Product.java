package com.imdat.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductVariant> productVariants = new ArrayList<>();

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductImage productImage;

    public Product(String productName, String description, ProductImage productImage, Category category, Brand brand) {
        this.productName = productName;
        this.description = description;
        setProductImage(productImage);
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

    public ProductImage getProductImage() {
        return productImage;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
        productImage.setProduct(this);
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


}
