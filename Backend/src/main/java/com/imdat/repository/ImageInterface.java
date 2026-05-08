package com.imdat.repository;

import com.imdat.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageInterface extends JpaRepository<Image, Integer> {
}
