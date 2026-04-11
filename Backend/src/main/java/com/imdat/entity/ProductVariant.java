package com.imdat.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String color;

    @NotNull
    private Integer size;

    @NotNull
    private Integer stock;

    @NotNull
    private Boolean isActive = true;

    @PrePersist
    @PreUpdate
    public void updateIsActive() {
        isActive = stock > 0;
    }

    @NotNull
    private Double price;

    @NotNull
    private Double importPrice;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonIgnore
    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    @JsonIgnore
    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL)
    private List<InvoiceDetail>  invoiceDetails;

    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Image> images;


    public ProductVariant(String color, Integer size, Integer stock, Double price, Double importPrice, Product product) {
        this.color = color;
        this.size = size;
        this.stock = stock;
        this.price = price;
        this.importPrice = importPrice;
        setProduct(product);
    }

    public ProductVariant() {
    }

    public String getColor() {
        return color;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getStock() {
        return stock;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Double getPrice() {
        return price;
    }

    public Double getImportPrice() {
        return importPrice;
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

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImportPrice(Double importPrice) {
        this.importPrice = importPrice;
    }

    public void setProduct(Product product) {
        product.setProductVariants(this);
        this.product = product;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Integer getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public List<InvoiceDetail> getInvoiceDetails() {
        return invoiceDetails;
    }

    public List<Image> getImages() {
        return images;
    }
}
