package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.repository.DetailRepository;
import org.binaracademy.challenge4.repository.OrderRepository;
import org.binaracademy.challenge4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DetailRepository detailRepository;

    @Override
    public Order orderBuilder(String username, Date time, String destination, boolean stat) {
        return Order.builder()
                .user(userRepository.queryFindUserByName(username))
                .time(time)
                .destination(destination)
                .status(stat)
                .build();
    }

    @Override
    public boolean addOrderToDB(Order order) {
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
            return true;
        } catch (Exception e) {
            log.error("Could not add order to database");
            return false;
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
