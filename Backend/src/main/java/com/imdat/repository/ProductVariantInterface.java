package com.imdat.repository;

import com.imdat.entity.ProductVariant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface ProductVariantInterface extends JpaRepository<ProductVariant, Integer> {
    @Query("""
       SELECT pv
       FROM ProductVariant pv
       ORDER BY pv.stock ASC
       """)
    List<ProductVariant> getLowStockProducts(
            Pageable pageable
    );
}
