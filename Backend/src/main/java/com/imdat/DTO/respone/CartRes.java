package com.imdat.DTO.respone;

import java.util.List;

public class CartRes {
    private Integer id;
    private List<CartItemRes> cartItemRes;

    public CartRes(Integer id, List<CartItemRes> cartItemRes) {
        this.id = id;
        this.cartItemRes = cartItemRes;
    }

    public CartRes() {
    }

    public Integer getId() {
        return id;
    }

    public List<CartItemRes> getCartItemRes() {
        return cartItemRes;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCartItemRes(List<CartItemRes> cartItemRes) {
        this.cartItemRes = cartItemRes;
    }
}
