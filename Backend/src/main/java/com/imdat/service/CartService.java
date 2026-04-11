package com.imdat.service;

import com.imdat.entity.Account;
import com.imdat.entity.Cart;
import com.imdat.entity.CartItem;
import com.imdat.entity.ProductVariant;
import com.imdat.repository.AccountInterface;
import com.imdat.repository.CartInterface;
import com.imdat.repository.CartItemInterface;
import com.imdat.repository.ProductVariantInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartItemInterface cartItemInterface;

    @Autowired
    private AccountInterface accountInterface;

    @Autowired
    private CartInterface cartInterface;

    @Autowired
    private ProductVariantInterface productVariantInterface;

    @Transactional
    public void addCartItem(Integer productVariantId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Account account = accountInterface.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = account.getCart();

        ProductVariant productVariant = productVariantInterface.findById(productVariantId).orElseThrow(() -> new RuntimeException("Product Variant not found"));

        Optional<CartItem> existing = cartItemInterface.findByCartAndProductVariant(cart, productVariant);

        if(existing.isPresent()) {
            CartItem cartItem = existing.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            return;
        }

        CartItem cartItem = new CartItem(1, cart, productVariant);

        cartInterface.save(cart);
    }

    public void removeCartItemById(Integer id) {
        cartItemInterface.deleteById(id);
    }

    @Transactional
    public void modifyQuantityCartItem(Integer id, String state) {
        CartItem cartItem = cartItemInterface.findById(id).orElseThrow(() -> new RuntimeException("Cart Item not found"));

        if (state.equals("plus")) {
            if(cartItem.getProductVariant().getStock() == 0) {
                return;
            }
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            int newQuan = cartItem.getQuantity() - 1;
            if (newQuan <= 0) {
                removeCartItemById(id);
                return;
            }
            cartItem.setQuantity(cartItem.getQuantity() - 1);
        }
    }

    @Transactional
    public Cart getCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Account account = accountInterface.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = account.getCart();
        return cart;
    }
}
