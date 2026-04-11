package com.imdat.service;

import com.imdat.DTO.ProductDetailDTO;
import com.imdat.entity.*;
import com.imdat.repository.BrandInterface;
import com.imdat.repository.CategoryInterface;
import com.imdat.repository.ProductInterface;
import com.imdat.specification.ProductSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductInterface productInterface;

    @Autowired
    private BrandInterface brandInterface;

    @Autowired
    private CategoryInterface categoryInterface;

    @Transactional
    public void addProduct(ProductDetailDTO productDetailDTO) {
        Brand brand = brandInterface.findById(productDetailDTO.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        Category category = categoryInterface.findById(productDetailDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        ProductImage productImage = new ProductImage();
        productImage.setData(productDetailDTO.getData());

        Product newProduct = new Product(productDetailDTO.getProductName(),productDetailDTO.getProductDescription(), productImage, category, brand);
        productInterface.save(newProduct);
    }

    public Product getProductById(Integer id) {
        return productInterface.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Transactional
    public void modifyProductById(Integer id, ProductDetailDTO productDetailDTO) {
        Product product = productInterface.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setProductName(productDetailDTO.getProductName());
        product.setDescription(productDetailDTO.getProductDescription());
        if (product.getProductImage() != null) {
            product.getProductImage().setData(productDetailDTO.getData());
        } else {
            ProductImage productImage = new ProductImage(productDetailDTO.getData());
            productImage.setProduct(product);
            product.setProductImage(productImage);
        }
    }

    public Page<Product> getProduct(String inputSearch, String direction, String brand, String category, String sortBy, Integer page, Integer size) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Product> spec = Specification.where(ProductSpecification.hasName(inputSearch))
                .and(ProductSpecification.hasBrand(brand)).and(ProductSpecification.hasCategory(category));

        return productInterface.findAll(spec, pageable);
    }

    public void deleteProductById(Integer id) {
        productInterface.deleteById(id);
    }

}
