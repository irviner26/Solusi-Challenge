package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.model.response.ProductResponse;
import org.binaracademy.challenge4.repository.MerchantRepository;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    @Override
    public boolean addProduct(Product product) {
        if (merchantService.merchantExist(product.getMerchant().getName())) {
            try {
                log.info("Adding product to database...");
                productRepository.save(Objects.requireNonNull(Optional.of(product)
                        .filter(val -> val.getName() != null && val.getPrice() != 0)
                        .orElse(null)));
                log.info("Successfully added product {} of merchant {}", product.getName(), product.getMerchant().getName());
                return true;
            } catch (Exception e) {
                log.error("Could not add product");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean productExist(String merchantName, String productName) {
        if (merchantService.merchantExist(merchantName)) {
            try {
                return Objects.nonNull(productRepository.queryOneFromMerchant(productName,merchantName));
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean updateProductName(String merchantName, String oldProductName, String newProductName) {
        if (productExist(merchantName,oldProductName)) {
            log.info("Submitting edit to database...");
            productRepository.queryEditProductName(merchantName, oldProductName, newProductName);
            log.info("Successfully edited product name ({} -> {})",oldProductName,newProductName);
            return true;
        } else {
            log.error("Could not edit product {} name", oldProductName);
            return false;
        }
    }

    @Override
    public boolean updateProductPrice(String merchantName, String productName, double newProductPrice) {
        if (productExist(merchantName,productName)) {
            log.info("Submitting edit to database...");
            productRepository.queryEditProductPrice(merchantName,productName,newProductPrice);
            log.info("Successfully edited product price");
            return true;
        } else {
            log.error("Could not edit product {} price", productName);
            return false;
        }
    }

    @Override
    public boolean removeProductOf(String productName, String merchantName) {
        if (productExist(merchantName,productName)) {
            log.info("Deleting product from database...");
            productRepository.queryDeleteProduct(productName,merchantName);
            log.info("Successfully deleted product {}", productName);
            return true;
        } else {
            log.error("Could not delete product");
            return false;
        }
    }

    @Override
    public List<ProductResponse> ListOfAvailableProduct(String merchantName, int page) {
        return productRepository.queryFromCertainMerchant(merchantName, PageRequest.of(page,5))
                .stream()
                .map(product -> ProductResponse.builder()
                        .productName(product.getName())
                        .productPrice(product.getPrice())
                        .build())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse oneProduct(String merchantName, String productName) {
        if (productExist(merchantName,productName)) {
            Product quedProd = productRepository.queryProdFromMerch(merchantName,productName);
            return ProductResponse.builder()
                    .productName(quedProd.getName())
                    .productPrice(quedProd.getPrice())
                    .build();
        }
        else return new ProductResponse();
    }

}
