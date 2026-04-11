package com.imdat.service;

import com.imdat.DTO.ProductVariantDetailDTO;
import com.imdat.entity.Image;
import com.imdat.entity.Product;
import com.imdat.entity.ProductVariant;
import com.imdat.repository.ProductInterface;
import com.imdat.repository.ProductVariantInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductVariantService {
    @Autowired
    private ProductVariantInterface productVariantInterface;

    @Autowired
    private ProductInterface productInterface;

    @Transactional
    public void addProductVariant(ProductVariantDetailDTO productVariantDetailDTO) {
        Product product = productInterface.findById(productVariantDetailDTO.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
        ProductVariant productVariant = new ProductVariant(
                productVariantDetailDTO.getColor(),
                productVariantDetailDTO.getSize(),
                productVariantDetailDTO.getStock(),
                productVariantDetailDTO.getPrice(),
                productVariantDetailDTO.getImportPrice(), product
        );

        product.setProductVariants(productVariant);

        List<Image> images = new ArrayList<>();

        for (int i = 0; i < productVariantDetailDTO.getDatas().size(); i++) {
            Image newImage = new Image(productVariantDetailDTO.getDatas().get(i), productVariant);
            images.add(newImage);
        }

        productVariant.setImages(images);

        productVariantInterface.save(productVariant);
    }

    public void deleteProductVariantById(Integer id) {
        productVariantInterface.deleteById(id);
    }

    public ProductVariant getProductVariantById(Integer id) {
        return productVariantInterface.findById(id).orElseThrow(() -> new RuntimeException("Product Variant not found"));
    }

    @Transactional
    public void modifyProductVariantById(Integer productVariantId, ProductVariantDetailDTO productVariantDetailDTO) {
        ProductVariant productVariant = productVariantInterface.findById(productVariantId).orElseThrow(() -> new RuntimeException(("Product Variant not found")));
        if (productVariantDetailDTO.getColor() != null) productVariant.setColor(productVariantDetailDTO.getColor());
        if (productVariantDetailDTO.getSize() != null) productVariant.setSize(productVariantDetailDTO.getSize());
        if (productVariantDetailDTO.getStock() != null) productVariant.setStock(productVariantDetailDTO.getStock());
        if (productVariantDetailDTO.getPrice() != null) productVariant.setPrice(productVariantDetailDTO.getPrice());
        if (productVariantDetailDTO.getImportPrice() != null) productVariant.setImportPrice(productVariantDetailDTO.getImportPrice());
    }
}
