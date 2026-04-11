package com.imdat.controller;


import com.imdat.DTO.ProductVariantDetailDTO;
import com.imdat.entity.ProductVariant;
import com.imdat.service.ProductVariantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ProductVariantController {
    @Autowired
    ProductVariantService productVariantService;

    //Thêm biến thể sản phẩm(ADMIN)
    @PostMapping("admin/product_variant")
    public void addProductVariant(@Valid @RequestBody ProductVariantDetailDTO productVariantDetailDTO) {
        productVariantService.addProductVariant(productVariantDetailDTO);
    }

    @GetMapping("auth/product_variant/{id}")
    public ProductVariant getProdcutVariantById(@PathVariable Integer id) {
        return productVariantService.getProductVariantById(id);
    }

    //Xóa biến thể sản phẩm(ADMIN)
    @DeleteMapping("admin/product_variant/{id}")
    public void deleteProductVariantById(@PathVariable Integer productVariantId) {
        productVariantService.deleteProductVariantById(productVariantId);
    }

    //Sửa biến thể sản phẩm(ADMIN)
    @PutMapping("admin/product_variant/{id}")
    public void modifyProductVariantById(@PathVariable Integer productVariantId, @Valid @RequestBody ProductVariantDetailDTO productVariantDetailDTO) {
        productVariantService.modifyProductVariantById(productVariantId, productVariantDetailDTO);
    }
}
