package com.imdat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String brandName;

    @NotNull
    @Lob
    private Byte[] data;

    @JsonIgnore
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public Brand(String brandName, Byte[] data) {
        this.brandName = brandName;
        this.data = data;
    }

    public Brand() {}

    public String getBrandName() {
        return brandName;
    }

    public Byte[] getData() {
        return data;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setData(Byte[] data) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }
}
