package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.model.response.ProductResponse;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BooleanSupplier;

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
                .thenReturn(CompletableFuture.supplyAsync(() -> Boolean.TRUE));

        CompletableFuture<Boolean> added = productService.addProduct(Product.builder()
                .name("prod1")
                .merchant(Merchant.builder().name("merch1").build())
                .price(1000)
                .build());

        Mockito.verify(productRepository).save(Mockito.any(Product.class));
        Assertions.assertTrue((BooleanSupplier) added);
    }

    @Test
    void TEST_PRODUCTEXIST() {
        Mockito.when(merchantService.merchantExist(Mockito.anyString())).thenReturn(CompletableFuture.supplyAsync(() -> Boolean.TRUE));
        Mockito.when(productRepository.queryOneFromMerchant(Mockito.anyString(),Mockito.anyString())).thenReturn(Product.builder()
                        .name("prod1")
                .build());
        CompletableFuture<Boolean> existed = productService.productExist("merch","prod1");
        Assertions.assertTrue((BooleanSupplier) existed);
    }

    @Test
    void TEST_UPDATEPRODNAME() {
        Mockito.when(merchantService.merchantExist(Mockito.anyString())).thenReturn(CompletableFuture.supplyAsync(() -> Boolean.TRUE));
        Mockito.when(productRepository.queryOneFromMerchant(Mockito.anyString(),Mockito.anyString())).thenReturn(Product.builder()
                .name("prod1")
                .build());
        CompletableFuture<Boolean> updated = productService.updateProductName("merch","prod1","prod2");
        Assertions.assertTrue((BooleanSupplier) updated);
    }

    @Test
    void TEST_UPDATEPRODPRICE() {
        Mockito.when(merchantService.merchantExist(Mockito.anyString())).thenReturn(CompletableFuture.supplyAsync(() -> Boolean.TRUE));
        Mockito.when(productRepository.queryOneFromMerchant(Mockito.anyString(),Mockito.anyString())).thenReturn(Product.builder()
                .name("prod1")
                        .price(1000)
                .build());
        CompletableFuture<Boolean> updated = productService.updateProductPrice("merch","prod1",2000);
        Assertions.assertTrue((BooleanSupplier) updated);
    }

    @Test
    void TEST_DELETEPRODUCT() {
        Mockito.when(merchantService.merchantExist(Mockito.anyString())).thenReturn(CompletableFuture.supplyAsync(() -> Boolean.TRUE));
        Mockito.when(productRepository.queryOneFromMerchant(Mockito.anyString(),Mockito.anyString())).thenReturn(Product.builder()
                .name("prod1")
                .price(1000)
                .build());
        CompletableFuture<Boolean> deleted = productService.removeProductOf("prod1","merch1");
        Assertions.assertTrue((BooleanSupplier) deleted);
    }

    @Test
    void TEST_RESPONSEPROD() {
        Mockito.when(merchantService.merchantExist(Mockito.anyString())).thenReturn(CompletableFuture.supplyAsync(() -> Boolean.TRUE));
        Mockito.when(productRepository.queryOneFromMerchant(Mockito.anyString(),Mockito.anyString())).thenReturn(Product.builder()
                .name("prod1")
                .price(0)
                .build());
        Mockito.when(productRepository.queryProdFromMerch(Mockito.anyString(),Mockito.anyString())).thenReturn(Product.builder()
                .name("prod1")
                .price(0)
                .build());

        ProductResponse productResponse = null;
        try {
            productResponse = productService.oneProduct("merch", "prod1").get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        ProductResponse expected = ProductResponse.builder().productName("prod1").build();

        Assertions.assertEquals(expected, productResponse);
    }

    @Test
    void TEST_LISTPROD() {
        Page<Product> productPage = new PageImpl<>(Arrays.asList(
           Product.builder().name("prod1").build(),
           Product.builder().name("prod2").build()
        ), Mockito.mock(Pageable.class), 0);
        Mockito.when(productRepository.queryFromCertainMerchant(Mockito.anyString(), Mockito.any(PageRequest.class)))
                .thenReturn(productPage);
        List<ProductResponse> products = null;
        try {
            products = productService.ListOfAvailableProduct("merch1",1).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        List<ProductResponse> responses = Arrays.asList(
                ProductResponse.builder().productName("prod1").build(),
                ProductResponse.builder().productName("prod2").build()
        );
        Assertions.assertEquals(responses, products);
    }
}
