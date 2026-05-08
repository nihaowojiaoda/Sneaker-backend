package com.imdat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart(Account account) {
        this.account = account;
        account.setCart(this);
    }

    public Cart() {}

    public void setCartItems(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }

    public List<CartItem> getCartItems() {
        return this.cartItems;
    }

    public Integer getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }
}
