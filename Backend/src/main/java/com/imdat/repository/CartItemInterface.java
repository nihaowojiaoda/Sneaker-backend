package com.imdat.repository;

import com.imdat.entity.Cart;
import com.imdat.entity.CartItem;
import com.imdat.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemInterface extends JpaRepository<CartItem, Integer> {
    Optional<CartItem> findByCartAndProductVariant(Cart cart, ProductVariant productVariant);
}
