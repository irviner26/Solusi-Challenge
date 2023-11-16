package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Detail;
import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.response.DetailResponse;
import org.binaracademy.challenge4.model.response.OrderResponse;
import org.binaracademy.challenge4.model.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface DetailService {
    double finalPrice(double price, int quant);
    double totalPriceInCart(List<DetailResponse> userCart);
    CompletableFuture<Detail> buildOrderDetail(int quant, double total, Order order, String productName, String merchantName);
    CompletableFuture<Boolean> addDetailsToDB(Detail orderDetail);
}
