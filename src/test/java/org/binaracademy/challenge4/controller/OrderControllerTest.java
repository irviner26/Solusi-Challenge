package org.binaracademy.challenge4.controller;


import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.User;
import org.binaracademy.challenge4.model.response.DetailResponse;
import org.binaracademy.challenge4.model.response.OrderResponse;
import org.binaracademy.challenge4.service.DetailService;
import org.binaracademy.challenge4.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;

    @Mock
    DetailService detailService;

    @Test
    void TEST_REQUESTORDER() throws ExecutionException, InterruptedException {
        Mockito.when(orderService.orderBuilder(Mockito.anyString(),Mockito.any(),Mockito.anyString(),Mockito.anyBoolean()))
                .thenReturn(CompletableFuture.supplyAsync(() -> Order.builder()
                        .destination("D1")
                        .time(new Date(0))
                        .status(true)
                        .user(User.builder().uname("U1").build())
                        .build()));
        Mockito.when(detailService.finalPrice(Mockito.anyDouble(), Mockito.anyInt())).thenReturn(10.0);
        Mockito.when(orderService.addOrderToDB(Mockito.any(Order.class))).thenReturn(CompletableFuture.supplyAsync(() -> Boolean.TRUE));
        Mockito.when(detailService.addDetailsToDB(Mockito.any())).thenReturn(CompletableFuture.supplyAsync(() -> Boolean.TRUE));

        ResponseEntity<String> output = orderController.requestMakeOrder("U1", OrderResponse.builder()
                        .orderTime(new Date(0))
                        .orderDestination("D1")
                        .detailResponses(
                                Arrays.asList(
                                        DetailResponse.builder().productName("P1").productPrice(1).productQuantity(1).build(),
                                        DetailResponse.builder().productName("P2").productPrice(2).productQuantity(2).build()
                                )
                        )
                .build());

        ResponseEntity<String> expect = new ResponseEntity<>("Successfully added order", HttpStatus.OK);

        Assertions.assertEquals(expect,output);

    }

}
