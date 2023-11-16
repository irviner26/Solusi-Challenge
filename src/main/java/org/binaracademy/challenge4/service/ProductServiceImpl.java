package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.model.response.ProductResponse;
import org.binaracademy.challenge4.repository.MerchantRepository;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private MerchantService merchantService;


    @Override
    public Product productBuilder(String name, double price, String merchantName) {
        return Product.builder()
                .name(name)
                .price(price)
                .merchant(merchantRepository.queryFindMerchantByName(merchantName))
                .build();
    }

    @Async
    @Transactional
    @Override
    public CompletableFuture<Boolean> addProduct(Product product) {
        try {
            if (merchantService.merchantExist(product.getMerchant().getName()).get()) {
                try {
                    log.info("Adding product to database...");
                    productRepository.save(Objects.requireNonNull(Optional.of(product)
                            .filter(val -> val.getName() != null && val.getPrice() != 0)
                            .orElse(null)));
                    log.info("Successfully added product {} of merchant {}", product.getName(), product.getMerchant().getName());
                    return CompletableFuture.supplyAsync(() -> Boolean.TRUE);
                } catch (Exception e) {
                    log.error("Could not add product");
                    return CompletableFuture.supplyAsync(() -> Boolean.FALSE);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return CompletableFuture.supplyAsync(() -> Boolean.FALSE);
    }

    @Async
    @Transactional(readOnly = true)
    @Override
    public CompletableFuture<Boolean> productExist(String merchantName, String productName) {
        try {
            if (merchantService.merchantExist(merchantName).get()) {
                try {
                    return CompletableFuture.supplyAsync(() ->
                            Objects.nonNull(productRepository.queryOneFromMerchant(productName,merchantName)));
                } catch (Exception e) {
                    return CompletableFuture.supplyAsync(() -> Boolean.FALSE);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return CompletableFuture.supplyAsync(() -> Boolean.FALSE);
    }

    @Async
    @Transactional
    @Override
    public CompletableFuture<Boolean> updateProductName(String merchantName, String oldProductName, String newProductName) {
        try {
            if (productExist(merchantName,oldProductName).get()) {
                log.info("Submitting edit to database...");
                productRepository.queryEditProductName(merchantName, oldProductName, newProductName);
                log.info("Successfully edited product name ({} -> {})",oldProductName,newProductName);
                return CompletableFuture.supplyAsync(() -> Boolean.TRUE);
            } else {
                log.error("Could not edit product {} name", oldProductName);
                return CompletableFuture.supplyAsync(() -> Boolean.FALSE);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    @Transactional
    @Override
    public CompletableFuture<Boolean> updateProductPrice(String merchantName, String productName, double newProductPrice) {
        try {
            if (productExist(merchantName,productName).get()) {
                log.info("Submitting edit to database...");
                productRepository.queryEditProductPrice(merchantName,productName,newProductPrice);
                log.info("Successfully edited product price");
                return CompletableFuture.supplyAsync(() -> Boolean.TRUE);
            } else {
                log.error("Could not edit product {} price", productName);
                return CompletableFuture.supplyAsync(() -> Boolean.FALSE);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    @Transactional
    @Override
    public CompletableFuture<Boolean> removeProductOf(String productName, String merchantName) {
        try {
            if (productExist(merchantName,productName).get()) {
                log.info("Deleting product from database...");
                productRepository.queryDeleteProduct(productName,merchantName);
                log.info("Successfully deleted product {}", productName);
                return CompletableFuture.supplyAsync(() -> Boolean.TRUE);
            } else {
                log.error("Could not delete product");
                return CompletableFuture.supplyAsync(() -> Boolean.FALSE);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    @Transactional(readOnly = true)
    @Override
    public CompletableFuture<List<ProductResponse>> ListOfAvailableProduct(String merchantName, int page) {
        return CompletableFuture.supplyAsync(() -> productRepository.queryFromCertainMerchant(merchantName, PageRequest.of(page,5))
                .stream()
                .map(product -> ProductResponse.builder()
                        .productName(product.getName())
                        .productPrice(product.getPrice())
                        .build())
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }

    @Async
    @Transactional(readOnly = true)
    @Override
    public CompletableFuture<ProductResponse> oneProduct(String merchantName, String productName) {
        try {
            if (productExist(merchantName,productName).get()) {
                Product quedProd = productRepository.queryProdFromMerch(merchantName,productName);
                return CompletableFuture.supplyAsync(() -> ProductResponse.builder()
                        .productName(quedProd.getName())
                        .productPrice(quedProd.getPrice())
                        .build());
            }
            else return CompletableFuture.supplyAsync(ProductResponse::new);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
