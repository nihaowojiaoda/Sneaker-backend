package com.imdat.service;

import com.imdat.DTO.respone.BrandRes;
import com.imdat.DTO.respone.CategoryRes;
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

    public void addBrand(BrandRes brandRes) {
        Brand brand = new Brand(brandRes.getBrandName());
        brandInterface.save(brand);
    }

    public List<BrandRes> getAllBrand() {
        List<Brand> brandList = brandInterface.findAll();
        return brandList.stream().map(this::convertBrand).toList();
    }

    public void deleteBrandById(Integer id) {
        brandInterface.deleteById(id);
    }

    public void addCategory(CategoryRes categoryRes) {
        Category category = new Category(categoryRes.getCategoryName());
        categoryInterface.save(category);
    }

    public List<CategoryRes> getAllCategory() {
        List<Category> categoryList = categoryInterface.findAll();

        return categoryList.stream().map(this::convertCategory).toList();
    }

    public void deleteCategoryById(Integer id) {
        categoryInterface.deleteById(id);
    }

    public CategoryRes convertCategory(Category category) {
        return new CategoryRes(
                category.getId(),
                category.getCategoryName()
        );
    }

    public BrandRes convertBrand(Brand brand) {
        return new BrandRes(
                brand.getId(),
                brand.getBrandName()
        );
    }
}
