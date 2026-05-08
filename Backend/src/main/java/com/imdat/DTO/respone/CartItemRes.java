package com.imdat.DTO.respone;

import com.imdat.entity.Cart;

public class CartItemRes {
    private Integer id;
    private Integer quantity;
    private ProductVariantRes productVariantRes;

    public CartItemRes() {
    }

    public CartItemRes(Integer id, Integer quantity, ProductVariantRes productVariantRes) {
        this.id = id;
        this.quantity = quantity;
        this.productVariantRes = productVariantRes;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setProductVariantRes(ProductVariantRes productVariantRes) {
        this.productVariantRes = productVariantRes;
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ProductVariantRes getProductVariantRes() {
        return productVariantRes;
    }
}
