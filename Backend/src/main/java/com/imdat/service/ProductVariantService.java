package com.imdat.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.imdat.DTO.require.ProductVariantReq;
import com.imdat.DTO.respone.ImageRes;
import com.imdat.DTO.respone.ProductVariantRes;
import com.imdat.convert.Convert;
import com.imdat.entity.Image;
import com.imdat.entity.Product;
import com.imdat.entity.ProductVariant;
import com.imdat.repository.ProductInterface;
import com.imdat.repository.ProductVariantInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductVariantService {
    @Autowired
    private ProductVariantInterface productVariantInterface;

    @Autowired
    private ProductInterface productInterface;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private Convert convert;

    @Transactional
    public void addProductVariant(ProductVariantReq productVariantReq) {

        Product product = productInterface.findById(productVariantReq.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductVariant productVariant = new ProductVariant(
                productVariantReq.getColor(),
                productVariantReq.getSize(),
                productVariantReq.getStock(),
                productVariantReq.getPrice(),
                productVariantReq.getImportPrice(),
                product
        );

        product.setProductVariants(productVariant);
        productVariantInterface.save(productVariant);
    }

    @Transactional
    public void modifyProductVariantById(Integer id, ProductVariantReq productVariantReq) {

        ProductVariant productVariant = productVariantInterface.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Variant not found"));

        if (productVariantReq.getColor() != null) productVariant.setColor(productVariantReq.getColor());
        if (productVariantReq.getSize() != null) productVariant.setSize(productVariantReq.getSize());
        if (productVariantReq.getStock() != null) productVariant.setStock(productVariantReq.getStock());
        if (productVariantReq.getPrice() != null) productVariant.setPrice(productVariantReq.getPrice());
        if (productVariantReq.getImportPrice() != null) productVariant.setImportPrice(productVariantReq.getImportPrice());

        productVariantInterface.save(productVariant);
    }

    @Transactional public ProductVariantRes getProductVariantById(Integer id) {
        ProductVariant productVariant = productVariantInterface.findById(id).orElseThrow(() -> new RuntimeException("Product variant not found"));

        return convert.convertProductVariant(productVariant);
    }

    public void deleteProductVariantById(Integer productVariantId) {
        productVariantInterface.deleteById(productVariantId);
    }
}
