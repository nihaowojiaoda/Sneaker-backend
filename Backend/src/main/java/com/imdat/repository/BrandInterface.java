package com.imdat.repository;

import com.imdat.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandInterface extends JpaRepository<Brand, Integer> {
}
