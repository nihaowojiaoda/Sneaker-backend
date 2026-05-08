package com.imdat.controller;

import com.imdat.DTO.require.ProductReq;
import com.imdat.DTO.respone.ProductRes;
import com.imdat.entity.Product;
import com.imdat.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ProductController {
    @Autowired
    private ProductService productService;

    //Thêm sản phẩm(ADMIN)
    @PostMapping("admin/product")
    public void addProduct(@Valid @RequestBody ProductReq productReq) {
        productService.addProduct(productReq);
    }

    //Lấy sản phẩm theo id(Auth)
    @GetMapping("auth/product/{id}")
    public ProductRes getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    //Sửa sản phẩm theo Id(ADMIN)
    @PutMapping("admin/product/{id}")
    public void modifyProductById(@PathVariable Integer id, @Valid @RequestBody ProductReq productReq) {
        productService.modifyProductById(id, productReq);
    }

    //Lọc, tìm kiếm sản phẩm(ADMIN)
    @GetMapping("admin/product_page")
    public Page<ProductRes> getProduct(
            @RequestParam(required = false) String inputSearch,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return productService.getProduct(inputSearch, direction,brand, category, sortBy, page, size);
    }

    //Xóa sản phẩm(Admin)
    @DeleteMapping("admin/product/{id}")
    public void removeProductById(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }

    @GetMapping("auth/product")
    public List<ProductRes> getAllProduct() {
        return productService.getAllProduct();
    }
}
