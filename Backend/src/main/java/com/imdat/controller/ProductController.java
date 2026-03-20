package com.imdat.controller;

import com.imdat.entity.Product;
import com.imdat.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public void addNewProduct(@RequestBody Product product) {
        productService.insertProduct(product);
    }

    @DeleteMapping("{id}")
    public void removeProductById(@PathVariable Integer id) {
        productService.removeProductById(id);
    }

    @PutMapping("{id}")
    public void updateProductById(@PathVariable Integer id, @RequestBody Product product) { productService.updateProductById(id, product);}


}
