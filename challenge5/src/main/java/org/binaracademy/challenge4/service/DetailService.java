package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Detail;
import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.response.DetailResponse;
import org.binaracademy.challenge4.model.response.OrderResponse;
import org.binaracademy.challenge4.model.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetailService {
    double finalPrice(ProductResponse productResponse, int quant);
    double totalPriceInCart(List<DetailResponse> userCart);
    Detail buildOrderDetail(int quant, double total, String username, String productName, String merchantName);
    void addDetailsToDB(Detail orderDetail);
}
