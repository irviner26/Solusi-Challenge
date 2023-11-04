package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.repository.MerchantRepository;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceTest {
    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    @Mock
    MerchantService merchantService;

    @Test
    void TEST_ADDPROD() {

        Mockito.when(productRepository.save(Mockito.any(Product.class)))
                .thenReturn(Product.builder()
                        .name("prod1")
                        .merchant(Merchant.builder().name("merch1").build())
                        .price(1)
                        .build());

        Mockito.when(merchantService.merchantExist(Mockito.anyString()))
                .thenReturn(true);

        boolean added = productService.addProduct(Product.builder()
                .name("prod1")
                .merchant(Merchant.builder().name("merch1").build())
                .price(1000)
                .build());

        Mockito.verify(productRepository).save(Mockito.any(Product.class));
        Assertions.assertTrue(added);
    }

    @Test
    void TEST_PRODUCTEXIST() {
        Mockito.when(merchantService.merchantExist(Mockito.anyString())).thenReturn(true);
        Mockito.when(productRepository.queryOneFromMerchant(Mockito.anyString(),Mockito.anyString())).thenReturn(Product.builder()
                        .name("prod1")
                .build());
        boolean existed = productService.productExist("merch","prod1");
        Assertions.assertTrue(existed);
    }

    @Test
    void TEST_UPDATEPRODNAME() {
        Mockito.when(merchantService.merchantExist(Mockito.anyString())).thenReturn(true);
        Mockito.when(productRepository.queryOneFromMerchant(Mockito.anyString(),Mockito.anyString())).thenReturn(Product.builder()
                .name("prod1")
                .build());
        boolean updated = productService.updateProductName("merch","prod1","prod2");
        Assertions.assertTrue(updated);
    }

    @Test
    void TEST_UPDATEPRODPRICE() {
        Mockito.when(merchantService.merchantExist(Mockito.anyString())).thenReturn(true);
        Mockito.when(productRepository.queryOneFromMerchant(Mockito.anyString(),Mockito.anyString())).thenReturn(Product.builder()
                .name("prod1")
                        .price(1000)
                .build());
        boolean updated = productService.updateProductPrice("merch","prod1",2000);
        Assertions.assertTrue(updated);
    }
}
