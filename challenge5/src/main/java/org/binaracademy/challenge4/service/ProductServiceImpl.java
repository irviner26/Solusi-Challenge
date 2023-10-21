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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MerchantRepository merchantRepository;


    @Override
    public Product productBuilder(String name, double price, String merchantName) {
        return Product.builder()
                .name(name)
                .price(price)
                .merchant(merchantRepository.queryFindMerchantByName(merchantName))
                .build();
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(Optional.ofNullable(product)
                .filter(val -> val.getName() != null && val.getPrice() != 0)
                .orElse(Product.builder()
                        .code("DELETETHIS3")
                        .build()));
        try {
            productRepository.deleteById("DELETETHIS3");
        } catch (Exception e) {
            log.info("Succesfully added to database");
        }
    }

    @Override
    public void updateProductName(String merchantName, String oldProductName, String newProductName) {
        productRepository.queryEditProductName(merchantName, oldProductName, newProductName);
    }

    @Override
    public void updateProductPrice(String merchantName, String productName, double newProductPrice) {
        productRepository.queryEditProductPrice(merchantName,productName,newProductPrice);
    }

    @Override
    public void removeProductOf(String productName, String merchantName) {
        productRepository.queryDeleteProduct(productName,merchantName);
    }

    @Override
    public List<ProductResponse> ListOfAvailableProduct(String merchantName, int page) {
        return productRepository.queryFromCertainMerchant(merchantName, PageRequest.of(page,5))
                .stream()
                .map(product -> ProductResponse.builder()
                        .ProductName(product.getName())
                        .ProductPrice(product.getPrice())
                        .build())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public void viewListOfProduct(List<ProductResponse> products) {
        AtomicInteger index = new AtomicInteger(-1);
        products.forEach(prod ->{
                    index.addAndGet(1);
                    System.out.println(index+". "+prod.getProductName()+"\t|\t"+prod.getProductPrice());
                });
    }
}
