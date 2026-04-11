package com.imdat.repository;

import com.imdat.entity.Account;
import com.imdat.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface InvoiceInterface extends JpaRepository<Invoice, Integer>, JpaSpecificationExecutor<Invoice> {
    List<Invoice> findByAccount(Account account);
}
