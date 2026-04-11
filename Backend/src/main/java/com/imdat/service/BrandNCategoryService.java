package com.imdat.service;

import com.imdat.DTO.BrandDTO;
import com.imdat.DTO.CategoryDTO;
import com.imdat.entity.Brand;
import com.imdat.entity.Category;
import com.imdat.repository.BrandInterface;
import com.imdat.repository.CategoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandNCategoryService {
    @Autowired
    private BrandInterface brandInterface;

    @Autowired
    private CategoryInterface categoryInterface;

    public void addBrand(BrandDTO brandDTO) {
        Brand brand = new Brand(brandDTO.getBrandName(), brandDTO.getData());
        brandInterface.save(brand);
    }

    public List<Brand> getAllBrand() {
        return brandInterface.findAll();
    }

    public void deleteBrandById(Integer id) {
        brandInterface.deleteById(id);
    }

    public void addCategory(CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO.getCategoryName());
        categoryInterface.save(category);
    }

    public List<Category> getAllCategory() {
        return categoryInterface.findAll();
    }

    public void deleteCategoryById(Integer id) {
        categoryInterface.deleteById(id);
    }
}
