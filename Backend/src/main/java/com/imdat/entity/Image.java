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
    private String url;

    @NotNull
    private String publicId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Image(String url, String publicId, Product product) {
        this.url = url;
        this.publicId = publicId;
        this.product = product;
    }

    public Image() {}

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public void setProductVariant(ProductVariant productVariant) {
        this.product = product;
    }

    public Product getProductVariant() {
        return product;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
