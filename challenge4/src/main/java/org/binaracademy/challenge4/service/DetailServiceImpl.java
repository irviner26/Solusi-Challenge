package org.binaracademy.challenge4.service;

import lombok.Getter;
import org.binaracademy.challenge4.model.Detail;
import org.binaracademy.challenge4.model.response.DetailResponse;
import org.binaracademy.challenge4.model.response.ProductResponse;
import org.binaracademy.challenge4.repository.DetailRepository;
import org.binaracademy.challenge4.repository.OrderRepository;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class DetailServiceImpl implements DetailService{

    @Autowired
    private ProductService productService;

    @Autowired
    private DetailRepository detailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public double finalPrice(ProductResponse productResponse, int quant) {
        return productResponse.getProductPrice() * quant;
    }

    @Override
    public double totalPriceInCart(List<DetailResponse> userCart) {
        return userCart.stream().reduce(0.0, (aDouble, detailResponse) -> aDouble + detailResponse.getProductFinalPrice(), Double::sum);
    }

    @Override
    public Detail buildOrderDetail(int quant, double total, String username, String productName, String merchantName) {
        return Detail.builder()
                .quantity(quant)
                .total(total)
                .order(orderRepository.getOrdersOfUser(username).get(orderRepository.getOrdersOfUser(username).size()-1))
                .product(productRepository.queryOneFromMerchant(productName,merchantName))
                .build();
    }

    @Override
    public void addDetailsToDB(Detail details) {
        detailRepository.save(details);
    }
}
