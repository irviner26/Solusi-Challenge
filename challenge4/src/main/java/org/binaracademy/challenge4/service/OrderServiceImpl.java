package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Detail;
import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.repository.DetailRepository;
import org.binaracademy.challenge4.repository.OrderRepository;
import org.binaracademy.challenge4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public void addOrderToDB(Order confirmedOrders) {
        orderRepository.save(confirmedOrders);
    }

    @Override
    public void editOrderDestination(String newDestination) {

    }

    @Override
    public void editOrderStatus(Boolean newStatus) {

    }

    @Override
    public void deleteOrder(Order order) {

    }
}
