package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Detail;
import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.repository.DetailRepository;
import org.binaracademy.challenge4.repository.OrderRepository;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BooleanSupplier;

@SpringBootTest
@AutoConfigureMockMvc
public class DetailServiceTest {
    @InjectMocks
    DetailServiceImpl detailService;

    @Mock
    DetailRepository detailRepository;

    @Mock
    OrderRepository orderRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductService productService;

    @Test
    void TEST_DETAILBUILDER() throws ExecutionException, InterruptedException {
        Mockito.when(productRepository.queryOneFromMerchant(Mockito.anyString(),Mockito.anyString()))
                .thenReturn(Product.builder().name("p1").build());
        Detail detail = detailService.buildOrderDetail(1,1, Order.builder().time(new Date(0)).build(),"p1","m1").get();
        Detail detail1 =
                Detail.builder()
                        .quantity(1)
                        .total(1)
                        .order(Order.builder().time(new Date(0)).build())
                        .product(Product.builder().name("p1").build())
                        .build();
        Assertions.assertEquals(detail1,detail);
    }

    @Test
    void TEST_ADDDETAIL() {
        Mockito.when(detailRepository.save(Mockito.any(Detail.class)))
                .thenReturn(Detail.builder()
                        .total(123)
                        .product(Product.builder().name("g").build())
                        .order(Order.builder().destination("t").build())
                        .quantity(456)
                        .build());

        CompletableFuture<Boolean> added = detailService.addDetailsToDB(
                Detail.builder()
                        .total(123)
                        .product(
                                Product.builder().name("g").build()
                        )
                        .order(
                                Order.builder().destination("t").build()
                        )
                        .quantity(456)
                        .build()
        );
        Mockito.verify(detailRepository).save(Mockito.any(Detail.class));
        Assertions.assertTrue((BooleanSupplier) added);
    }

}
