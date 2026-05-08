package com.imdat.controller;

import com.imdat.DTO.respone.BrandRes;
import com.imdat.DTO.respone.CategoryRes;
import com.imdat.entity.Brand;
import com.imdat.entity.Category;
import com.imdat.service.BrandNCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class BrandNCategoryController {

    @Autowired
    private BrandNCategoryService brandNCategoryService;

    @GetMapping("auth/category")
    public List<CategoryRes> getAllCategory() {
        return brandNCategoryService.getAllCategory();
    }

    @GetMapping("auth/brand")
    public List<BrandRes> getAllBrand() {
        return brandNCategoryService.getAllBrand();
    }

    @PostMapping("admin/category")
    public void addCategory(
            @RequestBody CategoryRes categoryRes
            ) {
        brandNCategoryService.addCategory(categoryRes);
    }

    @PostMapping("admin/brand")
    public void addBrand(@RequestBody BrandRes brandRes) {
        brandNCategoryService.addBrand(brandRes);
    }

    @DeleteMapping("admin/category/{id}")
    public void deleteCategoryById(@PathVariable Integer categoryId) {
        brandNCategoryService.deleteCategoryById(categoryId);
    }

    @DeleteMapping("admin/brand/{id}")
    public void deleteBrandById(@PathVariable Integer brandId) {
        brandNCategoryService.deleteBrandById(brandId);
    }
}
