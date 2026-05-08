package com.imdat.controller;


import com.imdat.DTO.require.ProductVariantReq;
import com.imdat.DTO.respone.ProductVariantRes;
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
    public void addProductVariant(@Valid @RequestBody ProductVariantReq productVariantDetailReq) {
        productVariantService.addProductVariant(productVariantDetailReq);
    }

    @GetMapping("auth/product_variant/{id}")
    public ProductVariantRes getProductVariantById(@PathVariable Integer id) {
        return productVariantService.getProductVariantById(id);
    }

    //Xóa biến thể sản phẩm(ADMIN)
    @DeleteMapping("admin/product_variant/{id}")
    public void deleteProductVariantById(@PathVariable Integer productVariantId) {
        productVariantService.deleteProductVariantById(productVariantId);
    }

    //Sửa biến thể sản phẩm(ADMIN)
    @PutMapping("admin/product_variant/{id}")
    public void modifyProductVariantById(@PathVariable Integer productVariantId, @Valid @RequestBody ProductVariantReq productVariantReq) {
        productVariantService.modifyProductVariantById(productVariantId, productVariantReq);
    }
}
