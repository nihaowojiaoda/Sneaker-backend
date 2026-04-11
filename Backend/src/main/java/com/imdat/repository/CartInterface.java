package com.imdat.repository;

import com.imdat.entity.Account;
import com.imdat.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartInterface extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByAccount(Account account);
}
