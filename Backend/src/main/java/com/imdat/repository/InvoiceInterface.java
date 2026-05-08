package com.imdat.repository;

import com.imdat.entity.Account;
import com.imdat.entity.Invoice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InvoiceInterface extends JpaRepository<Invoice, Integer>, JpaSpecificationExecutor<Invoice> {
    List<Invoice> findByAccount(Account account);

    @Query("""
           SELECT COALESCE(SUM(i.totalPrice), 0)
           FROM Invoice i
           WHERE i.status = 'COMPLETED'
           """)
    Double getTotalRevenue();

    @Query("""
       SELECT COUNT(i)
       FROM Invoice i
       WHERE i.status = 'NEW'
       """)
    Long countNewOrders();

    @Query("""
       SELECT i
       FROM Invoice i
       ORDER BY i.createdAt DESC
       """)
    List<Invoice> getRecentOrders(Pageable pageable);
}
