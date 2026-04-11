package com.imdat.repository;

import com.imdat.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryInterface extends JpaRepository<Category, Integer> {
}
