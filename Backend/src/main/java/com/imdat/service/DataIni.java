package com.imdat.service;

import com.imdat.entity.*;
import com.imdat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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

    @Autowired private ImageInterface imageInterface;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private CartInterface cartInterface;

    @Autowired private CartItemInterface cartItemInterface;

    public void init() {
        Account admin = new Account();
        admin.setUsername("imadmin");
        admin.setPassword(passwordEncoder.encode("123654798Dts!"));
        admin.setEmail("imadmin@example.com");
        admin.setRole("ROLE_ADMIN");
        admin.setPhoneNumber("0353250191");
        admin.setActive(true);

        accountInterface.save(admin);

        Account user1 = new Account();
        user1.setUsername("imuser1");
        user1.setPassword(passwordEncoder.encode("123654798Dts!"));
        user1.setEmail("imuser1@example.com");
        user1.setRole("ROLE_USER");
        user1.setPhoneNumber("0353250192");
        user1.setActive(true);

        accountInterface.save(user1);

        Account user2 = new Account();
        user2.setUsername("imuser2");
        user2.setPassword(passwordEncoder.encode("123654798Dts!"));
        user2.setEmail("imuser2@example.com");
        user2.setRole("ROLE_USER");
        user2.setPhoneNumber("0353250193");
        user2.setActive(true);

        accountInterface.save(user2);

        Account user3 = new Account();
        user3.setUsername("imuser3");
        user3.setPassword(passwordEncoder.encode("123654798Dts!"));
        user3.setEmail("imuser3@example.com");
        user3.setRole("ROLE_USER");
        user3.setPhoneNumber("0353250194");
        user3.setActive(true);

        accountInterface.save(user3);

        Cart cartUser1 = new Cart(user1);
        Cart cartUser2 = new Cart(user2);
        Cart cartUser3 = new Cart(user3);

        cartInterface.save(cartUser1);
        cartInterface.save(cartUser2);
        cartInterface.save(cartUser3);

        Brand nike = new Brand("Nike");
        Brand adidas = new Brand("Adidas");
        Brand newBalance = new Brand("New Balance");
        Brand puma = new Brand("Puma");

        brandInterface.save(nike);
        brandInterface.save(adidas);
        brandInterface.save(puma);
        brandInterface.save(newBalance);

        Category basketBall = new Category("BasketBall");
        Category running = new Category("Running");
        Category lifeStyle = new Category("Life Style");
        categoryInterface.save(basketBall);
        categoryInterface.save(running);
        categoryInterface.save(lifeStyle);

        Product airmax97 = new Product("Air Max 97", "Nike Air Max 97", lifeStyle, nike);
        Product jordan = new Product("Jordan", "Nike Jordan", basketBall, nike);
        Product ultraboost = new Product("UltraBoost", "UltraBoost Adidas", running, adidas);
        Product newBlanace550 = new Product("New Balance 550", "New Balance 550", lifeStyle, newBalance);
        Product suedeClassic = new Product("suedeClassic", "suedeClassic Puma", lifeStyle, puma);

        Image airmax97Img = new Image(
                "https://res.cloudinary.com/dyksxn2fo/image/upload/v1778145806/sneaker-1_meesgz.jpg",
                "sneaker-1_meesgz",
                airmax97
        );

        Image jordanImg = new Image(
                "https://res.cloudinary.com/dyksxn2fo/image/upload/v1778145919/sneaker-3_cmju01.jpg",
                "sneaker-3_cmju01",
                jordan
        );

        Image ultraBoostImg = new Image(
                "https://res.cloudinary.com/dyksxn2fo/image/upload/v1778145918/sneaker-2_wzagnq.jpg",
                "sneaker-2_wzagnq",
                ultraboost
        );

        Image newBlanceImg = new Image(
                "https://res.cloudinary.com/dyksxn2fo/image/upload/v1778145920/sneaker-4_y2utoj.jpg",
                "sneaker-4_y2utoj",
                newBlanace550
        );

        Image suedeImg = new Image(
                "https://res.cloudinary.com/dyksxn2fo/image/upload/v1778145921/sneaker-6_ogtvy0.jpg",
                "sneaker-6_ogtvy0",
                suedeClassic
        );

        airmax97.setImages(List.of(airmax97Img));
        jordan.setImages(List.of(jordanImg));
        ultraboost.setImages(List.of(ultraBoostImg));
        newBlanace550.setImages(List.of(newBlanceImg));
        suedeClassic.setImages(List.of(suedeImg));


        ProductVariant airmax97sb40 = new ProductVariant("Silver Bullet", 40, 5, 4500000L, 4000000L, airmax97);
        ProductVariant airmax97sb39 = new ProductVariant("Silver Bullet", 39, 4, 4500000L, 4000000L, airmax97);
        ProductVariant airmax97sb38 = new ProductVariant("Silver Bullet", 38, 3, 4500000L, 4000000L, airmax97);
        ProductVariant airmax97sb41 = new ProductVariant("Silver Bullet", 41, 3, 4500000L, 4000000L, airmax97);

        airmax97.setProductVariants(List.of(airmax97sb41, airmax97sb38, airmax97sb38, airmax97sb40));

        ProductVariant jordan40 = new ProductVariant("Red", 40, 3, 4500000L, 4000000L, jordan);
        ProductVariant jordan39 = new ProductVariant("Red", 39, 3, 4500000L, 4000000L, jordan);
        ProductVariant jordan38 = new ProductVariant("Red", 38, 3, 4500000L, 4000000L, jordan);
        ProductVariant jordan41 = new ProductVariant("Red", 41, 3, 4500000L, 4000000L, jordan);

        jordan.setProductVariants(List.of(jordan41, jordan38, jordan39, jordan40));

        ProductVariant ultraboost40 = new ProductVariant("Black", 40, 3, 4500000L, 4000000L, ultraboost);
        ProductVariant ultraboost39 = new ProductVariant("Black", 39, 3, 4500000L, 4000000L, ultraboost);
        ProductVariant ultraboost38 = new ProductVariant("Black", 38, 3, 4500000L, 4000000L, ultraboost);
        ProductVariant ultraboost41 = new ProductVariant("Black", 41, 3, 4500000L, 4000000L, ultraboost);

        ultraboost.setProductVariants(List.of(ultraboost41, ultraboost38, ultraboost39, ultraboost40));

        ProductVariant newBalance40 = new ProductVariant("Green White", 40, 3, 4500000L, 4000000L, newBlanace550);
        ProductVariant newBalance39 = new ProductVariant("Green White", 39, 3, 4500000L, 4000000L, newBlanace550);
        ProductVariant newBalance38 = new ProductVariant("Green White", 38, 3, 4500000L, 4000000L, newBlanace550);
        ProductVariant newBalance41 = new ProductVariant("Green White", 41, 3, 4500000L, 4000000L, newBlanace550);

        newBlanace550.setProductVariants(List.of(newBalance41, newBalance38, newBalance39, newBalance40));

        ProductVariant suedeClassic40 = new ProductVariant("Blue Red", 41, 3, 4500000L, 4000000L, suedeClassic);
        ProductVariant suedeClassic39 = new ProductVariant("Blue Red", 39, 3, 4500000L, 4000000L, suedeClassic);
        ProductVariant suedeClassic38 = new ProductVariant("Blue Red", 38, 3, 4500000L, 4000000L, suedeClassic);
        ProductVariant suedeClassic41 = new ProductVariant("Blue Red", 40, 3, 4500000L, 4000000L, suedeClassic);

        suedeClassic.setProductVariants(List.of(suedeClassic41, suedeClassic38, suedeClassic39, suedeClassic40));

        productInterface.save(airmax97);
        productInterface.save(jordan);
        productInterface.save(ultraboost);
        productInterface.save(newBlanace550);
        productInterface.save(suedeClassic);

        CartItem cartItem1 = new CartItem(3, cartUser1, suedeClassic38);
        CartItem cartItem2 = new CartItem(2, cartUser1, airmax97sb38);
        CartItem cartItem3 = new CartItem(1, cartUser1, jordan38);

        cartUser1.setCartItems(cartItem1);
        cartUser1.setCartItems(cartItem2);
        cartUser1.setCartItems(cartItem3);

        cartInterface.save(cartUser1);
    }


    @Override
    public void run(String... args) throws Exception {
        init();
    }
}
