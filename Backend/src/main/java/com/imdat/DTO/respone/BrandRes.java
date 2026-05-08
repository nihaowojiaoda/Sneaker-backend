package com.imdat.DTO.respone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BrandRes {
    @NotNull
    private Integer id;

    @NotBlank
    private String brandName;

    public BrandRes(Integer id, String brandName) {
        this.id = id;
        this.brandName = brandName;
    }

    public BrandRes() {
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
