package com.imdat.repository;

import com.imdat.entity.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDetailInterface extends JpaRepository<InvoiceDetail, Integer> {
}
