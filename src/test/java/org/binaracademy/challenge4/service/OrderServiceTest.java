package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.User;
import org.binaracademy.challenge4.repository.DetailRepository;
import org.binaracademy.challenge4.repository.OrderRepository;
import org.binaracademy.challenge4.repository.UserRepository;
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
public class OrderServiceTest {
    @InjectMocks
    OrderServiceImpl orderService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    UserRepository userRepository;

    @Test
    void TEST_ORDERBUILDER() throws ExecutionException, InterruptedException {
        Mockito.when(userRepository.queryFindUserByName(Mockito.anyString()))
                .thenReturn(User.builder().uname("u1").build());

        Order created = orderService.orderBuilder("u1", new Date(), "d", false).get();
        Order expected = Order.builder()
                .user(User.builder().uname("u1").build())
                .time(created.getTime())
                .destination("d")
                .status(false)
                .build();
        Mockito.verify(userRepository).queryFindUserByName(Mockito.anyString());
        Assertions.assertEquals(expected,created);
    }

    @Test
    void TEST_ADDORDER() {
        Order expected = Order.builder()
                .user(User.builder().uname("u1").build())
                .time(new Date())
                .destination("d")
                .status(false)
                .build();
        Mockito.when(orderRepository.save(Mockito.any(Order.class)))
                .thenReturn(expected);

        CompletableFuture<Boolean> added = orderService.addOrderToDB(
                Order.builder()
                        .user(User.builder().uname("u1").build())
                        .time(expected.getTime())
                        .destination("d")
                        .status(false)
                        .build()
        );

        Mockito.verify(orderRepository).save(Mockito.any(Order.class));
        Assertions.assertTrue((BooleanSupplier) added);
    }
}
