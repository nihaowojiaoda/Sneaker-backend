package com.imdat.controller;

import com.imdat.entity.Cart;
import com.imdat.service.AccountService;
import com.imdat.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class CartController {
    @Autowired
    private CartService cartService;


    //Thêm ProductVariant Vào giỏ hàng (USER)
    @PostMapping("user/cart/{id}")
    public void addCartItem(@PathVariable Integer id) {
        cartService.addCartItem(id);
    }

    //Xóa ProductVariat khỏi giỏ hàng theo Id (USER)
    @DeleteMapping("user/cart/{id}")
    public void removeCartItemById(@PathVariable Integer id) {
        cartService.removeCartItemById(id);
    }

    //Sửa số lượng sản phẩm trong giỏ hàng (USER)
    @PutMapping("user/cart/{id}")
    public void modifyQuantityCartItemById(@PathVariable Integer id, @RequestParam String state) {
        cartService.modifyQuantityCartItem(id, state);
    }

    //Lấy giỏ hàng tài khoản (USER)
    @GetMapping("user/cart")
    public Cart getCart() {
        return  cartService.getCart();
    }

}
