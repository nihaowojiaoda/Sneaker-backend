package com.imdat.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.imdat.DTO.require.ProductReq;
import com.imdat.DTO.respone.*;
import com.imdat.convert.Convert;
import com.imdat.entity.*;
import com.imdat.repository.BrandInterface;
import com.imdat.repository.CategoryInterface;
import com.imdat.repository.ImageInterface;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class ProductService {
    @Autowired
    private ProductInterface productInterface;

    @Autowired
    private BrandInterface brandInterface;

    @Autowired
    private CategoryInterface categoryInterface;

    @Autowired
    private Convert convert;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private ImageInterface imageInterface;

    @Transactional
    public void addProduct(ProductReq productReq) {
        Brand brand = brandInterface.findById(productReq.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        Category category = categoryInterface.findById(productReq.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));


        Product product = new Product(productReq.getProductName(),productReq.getProductDescription(), category, brand);

        List<Image> images = new ArrayList<>();

        for (MultipartFile file : productReq.getFiles()) {

            try {
                Map uploadResult = cloudinary.uploader().upload(
                        file.getBytes(),
                        ObjectUtils.emptyMap()
                );

                Image image = new Image();
                image.setUrl(uploadResult.get("secure_url").toString());
                image.setPublicId(uploadResult.get("public_id").toString());
                image.setProduct(product);
                images.add(image);

            } catch (IOException e) {
                throw new RuntimeException("Upload image failed", e);
            }
        }

        product.setImages(images);
        productInterface.save(product);
    }

    public ProductRes getProductById(Integer id) {
        Product product = productInterface.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return convert.convertProduct(product);
    }

    @Transactional
    public void modifyProductById(Integer id, ProductReq productDetailDTO) {
        Product product = productInterface.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setProductName(productDetailDTO.getProductName());
        product.setDescription(productDetailDTO.getProductDescription());
    }

    public Page<ProductRes> getProduct(String inputSearch, String direction, String brand, String category, String sortBy, Integer page, Integer size) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Product> spec = Specification.where(ProductSpecification.hasName(inputSearch))
                .and(ProductSpecification.hasBrand(brand)).and(ProductSpecification.hasCategory(category));

        Page<Product> productPage = productInterface.findAll(spec, pageable);
        return productPage.map(convert::convertProduct);
    }

    public void deleteProductById(Integer id) {
        productInterface.deleteById(id);
    }

    public List<ProductRes> getAllProduct() {
        List<Product> productList = productInterface.findAll();
        return productList.stream().map(convert::convertProduct).toList();
    }
}
