package com.imdat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private byte[] data;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductImage(byte[] data) {
        this.data = data;
    }

    public ProductImage() {}

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }
}
