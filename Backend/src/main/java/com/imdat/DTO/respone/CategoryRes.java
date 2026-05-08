package com.imdat.DTO.respone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CategoryRes {
    @NotNull
    Integer id;

    @NotBlank
    private String categoryName;

    public CategoryRes(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public CategoryRes() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
