package com.imdat.repository;

import com.imdat.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductInterface extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
}
