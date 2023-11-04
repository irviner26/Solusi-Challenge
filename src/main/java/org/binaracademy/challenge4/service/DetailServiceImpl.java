package org.binaracademy.challenge4.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Detail;
import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.response.DetailResponse;
import org.binaracademy.challenge4.repository.DetailRepository;
import org.binaracademy.challenge4.repository.OrderRepository;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
@Slf4j
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
    public double finalPrice(double price, int quant) {
        return price * quant;
    }

    @Override
    public double totalPriceInCart(List<DetailResponse> userCart) {
        return userCart.stream().reduce(0.0, (aDouble, detailResponse) -> aDouble + detailResponse.getProductFinalPrice(), Double::sum);
    }

    @Override
    public Detail buildOrderDetail(int quant,
                                   double total,
                                   Order order,
                                   String productName,
                                   String merchantName) {
        return Detail.builder()
                .quantity(quant)
                .total(total)
                .order(order)
                .product(productRepository.queryOneFromMerchant(productName,merchantName))
                .build();
    }

    @Override
    public boolean addDetailsToDB(Detail detail) {
        Detail details = Optional.ofNullable(detail)
                .filter(detail1 -> detail1.getQuantity() != 0 &&
                        detail1.getTotal() != 0 &&
                        Objects.nonNull(detail1.getOrder()) &&
                        Objects.nonNull(detail1.getProduct()))
                .orElse(null);
        try {
            log.info("Adding detail to database...");
            assert details != null;
            detailRepository.save(details);
            log.info("Successfully added detail");
            return true;
        } catch (Exception e) {
            log.info("Could not add detail to database");
            return false;
        }
    }
}
