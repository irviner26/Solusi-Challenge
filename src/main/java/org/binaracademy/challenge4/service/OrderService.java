package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Order;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface OrderService {

    Order orderBuilder(String username, Date time, String destination, boolean stat);
    boolean addOrderToDB(Order order);

    // TODO: TBA
    void editOrderDestination(String newDestination);
    void editOrderStatus(Boolean newStatus);
    void deleteOrder(Order order);
}
