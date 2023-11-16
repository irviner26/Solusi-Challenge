package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.repository.DetailRepository;
import org.binaracademy.challenge4.repository.OrderRepository;
import org.binaracademy.challenge4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DetailRepository detailRepository;

    @Async
    @Override
    public CompletableFuture<Order> orderBuilder(String username, Date time, String destination, boolean stat) {
        return CompletableFuture.supplyAsync(() -> Order.builder()
                .user(userRepository.queryFindUserByName(username))
                .time(time)
                .destination(destination)
                .status(stat)
                .build());
    }

    @Async
    @Transactional
    @Override
    public CompletableFuture<Boolean> addOrderToDB(Order order) {
        Order confirmedOrders = Optional.of(order)
                .filter(order1 -> Objects.nonNull(order1.getUser()) &&
                                  Objects.nonNull(order1.getTime()) &&
                                  order1.getDestination() != null)
                .orElse(null);
        try {
            log.info("Adding order to database...");
            assert confirmedOrders != null;
            orderRepository.save(confirmedOrders);
            log.info("Order added to database.");
            return CompletableFuture.supplyAsync(() -> Boolean.TRUE);
        } catch (Exception e) {
            log.error("Could not add order to database");
            return CompletableFuture.supplyAsync(() -> Boolean.FALSE);
        }
    }

    // TODO: TBA
    @Override
    public void editOrderDestination(String newDestination) {}
    @Override
    public void editOrderStatus(Boolean newStatus) {}
    @Override
    public void deleteOrder(Order order) {}
}
