package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.model.User;
import org.binaracademy.challenge4.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AllServiceTests {

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;
    @Autowired
    MerchantService merchantService;
    @Autowired
    DetailService detailService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MerchantRepository merchantRepository;
    @Autowired
    DetailRepository detailRepository;

    @Test
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void testAddProduct() {
        productService.addProduct(productService.productBuilder("pppp",20000,"merch1"));
    }

    @Test
    void testDeleteMerchant() {
        System.out.println(merchantService.deleteMerchant("spiderman"));
    }

    @Test
    void testMerchantExist() {
        System.out.println(merchantService.merchantExist("spiderman"));
    }


}
