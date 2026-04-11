package com.imdat.specification;

import com.imdat.entity.Product;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ProductSpecification {
    public static Specification<Product> hasName(String inputSearch) {
        return (root, query, cb) -> {
            if (inputSearch == null || inputSearch.isEmpty()) {
                return cb.conjunction();
            }
            return cb.like(root.get("productName"), "%" + inputSearch + "%");
        };
    }

    public static Specification<Product> hasCategory(String category) {
        return (root, query, cb) -> {
            if (category == null || category.isEmpty()) {
                return cb.conjunction();
            }

            return cb.like(
                    root.join("category", JoinType.LEFT).get("categoryName"),
                    "%" + category + "%"
            );
        };
    }

    public static Specification<Product> hasBrand(String brand) {
        return (root, query, cb) -> {
            if (brand == null || brand.isEmpty()) {
                return cb.conjunction();
            }

            return cb.like(
                    root.join("brand", JoinType.LEFT).get("brandName"),
                    "%" + brand + "%"
            );
        };
    }
}
