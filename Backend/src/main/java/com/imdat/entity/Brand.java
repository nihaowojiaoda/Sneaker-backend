package com.imdat.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String brandName;

    @NotBlank
    private byte[] img;

    @OneToMany(mappedBy = "brand")
    private List<Product> products = new ArrayList<>();

    public Brand(Integer id, String brandName, @NotBlank byte[] img) {
        this.id = id;
        this.brandName = brandName;
        this.img = img;
    }

    public Brand() {}

    public Integer getId() {
        return id;
    }

    public String getBrandName() {
        return brandName;
    }

    public byte[] getImg() {
        return img;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
