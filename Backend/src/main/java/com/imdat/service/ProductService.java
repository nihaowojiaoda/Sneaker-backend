package com.imdat.service;

import com.imdat.controller.ProductController;
import com.imdat.entity.Product;
import com.imdat.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void insertProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + "not found"));
    }

    public void removeProductById(Integer id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void updateProductById(Integer id, Product product) {
        Product modifyProduct = productRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + "not found"));

        modifyProduct.setName(product.getName());
        modifyProduct.setDescription(product.getDescription());
        modifyProduct.setPrice(product.getPrice());
    }
}
