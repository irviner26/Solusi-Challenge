package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.model.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface ProductService {
    // Berikut ini merupakan interface untuk logic yang berkaitan dengan product


    // Method berikut ini adalah method yang digunakan untuk menambahkan produk baru
    // sesuai dengan merchant code-nya.
    Product productBuilder(String name, double price, String merchantName);
    CompletableFuture<Boolean> addProduct(Product product);

    // Method berikut ini berfungsi untuk mengupdate nama dan harga dari produk
    CompletableFuture<Boolean> productExist(String merchantName, String productName);
    CompletableFuture<Boolean> updateProductName(String merchantName, String oldProductName, String newProductName);
    CompletableFuture<Boolean> updateProductPrice(String merchantName, String productName, double newProductPrice);

    // Method berikut ini berfungsi untuk menghapus produk yang dipilih
    CompletableFuture<Boolean> removeProductOf(String productName, String merchantName);

    // Method berikut ini berfungsi untuk menampilkan produk yang ada dari suatu merchant
    CompletableFuture<List<ProductResponse>> ListOfAvailableProduct(String merchantName, int page);
    CompletableFuture<ProductResponse> oneProduct(String merchantName, String productName);

}
