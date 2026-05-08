package com.imdat.repository;

import com.imdat.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountInterface extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {
    Optional<Account> findByUsername(String username);

    @Query("""
       SELECT COUNT(a)
       FROM Account a
       WHERE a.role = 'ROLE_USER'
       """)
    Long countCustomers();
}

