package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Order;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface OrderService {

    Order orderBuilder(String username, Date time, String destination, boolean stat);
    void addOrderToDB(Order confirmedOrders);
    void editOrderDestination(String newDestination);
    void editOrderStatus(Boolean newStatus);
    void deleteOrder(Order order);
}
