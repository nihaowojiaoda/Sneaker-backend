package com.imdat.service;

import com.imdat.DTO.ProductDetailDTO;
import com.imdat.DTO.ProductVariantDetailDTO;
import com.imdat.entity.*;
import com.imdat.repository.*;
import jakarta.validation.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataIni implements CommandLineRunner {
    @Autowired
    private AccountInterface accountInterface;

    @Autowired
    private BrandInterface brandInterface;

    @Autowired
    private CategoryInterface categoryInterface;

    @Autowired
    private ProductInterface productInterface;

    @Autowired
    private ProductVariantInterface productVariantInterface;

    @Autowired private ProductService productService;

    @Autowired private ProductVariantService productVariantService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private CartInterface cartInterface;

    @Autowired private CartItemInterface cartItemInterface;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("USER_DIR = " + System.getProperty("user.dir"));

        Account acc = new Account();
        acc.setUsername("taolaadmin");
        acc.setPassword(passwordEncoder.encode("123123123Dts!")); 
        acc.setEmail("taolaadmin@example.com");
        acc.setRole("ROLE_ADMIN");
        acc.setPhoneNumber("0353250191");
        acc.setActive(true);

        accountInterface.save(acc);

        Account acc2 = new Account();
        acc2.setUsername("taolauser");
        acc2.setPassword(passwordEncoder.encode("123123123Dts!")); 
        acc2.setEmail("taolauser@example.com");
        acc2.setRole("ROLE_USER");
        acc2.setPhoneNumber("0353250192");
        acc2.setActive(true);

        accountInterface.save(acc2);

        Brand brand = new Brand("Nike", new Byte[0]);
        brandInterface.save(brand);

        Category category = new Category();
        category.setCategoryName("Sport");
        categoryInterface.save(category);


        byte[] data = new byte[0]; // fileUtil.readImage("uploads/product/img.png");

        ProductDetailDTO productDetailDTO = new ProductDetailDTO("Giày Nike", "Giày thể thao Nike",
                1, 1, data);
        productService.addProduct(productDetailDTO);

        List<byte[]> imgsData = new ArrayList<byte[]>();

        // imgsData.add(fileUtil.readImage("uploads/product/img_1.png"));
        // imgsData.add(fileUtil.readImage("uploads/product/img_2.png"));

        ProductVariantDetailDTO productVariantDetailDTO = new ProductVariantDetailDTO("Xanh", 43, 4, 123.0, 123.0, 1, imgsData);
        productVariantService.addProductVariant(productVariantDetailDTO);

        Cart cart = new Cart(acc2);
        cartInterface.save(cart);

        CartItem cartItem1 = new CartItem(3, cart, productVariantService.getProductVariantById(1));
        cartItemInterface.save(cartItem1);
    }
}
