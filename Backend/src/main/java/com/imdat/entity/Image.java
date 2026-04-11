package com.imdat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Lob
    private byte[] data;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_variant_id")
    private ProductVariant productVariant;

    public Image(byte[] data, ProductVariant productVariant) {
        this.data = data;
        this.productVariant = productVariant;
    }

    public Image() {}

    public byte[] getData() {
        return data;
    }

    public Integer getId() {
        return id;
    }

    public ProductVariant getProductVariant() {
        return productVariant;
    }
}
