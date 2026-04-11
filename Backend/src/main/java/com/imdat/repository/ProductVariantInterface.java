package com.imdat.repository;

import com.imdat.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantInterface extends JpaRepository<ProductVariant, Integer> {
}
