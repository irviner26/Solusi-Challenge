package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.model.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    // Berikut ini merupakan interface untuk logic yang berkaitan dengan product


    // Method berikut ini adalah method yang digunakan untuk menambahkan produk baru
    // sesuai dengan merchant code-nya.
    Product productBuilder(String name, double price, String merchantName);
    boolean addProduct(Product product);

    // Method berikut ini berfungsi untuk mengupdate nama dan harga dari produk
    boolean productExist(String merchantName, String productName);
    boolean updateProductName(String merchantName, String oldProductName, String newProductName);
    boolean updateProductPrice(String merchantName, String productName, double newProductPrice);

    // Method berikut ini berfungsi untuk menghapus produk yang dipilih
    boolean removeProductOf(String productName, String merchantName);

    // Method berikut ini berfungsi untuk menampilkan produk yang ada dari suatu merchant
    List<ProductResponse> ListOfAvailableProduct(String merchantName, int page);
    ProductResponse oneProduct(String merchantName, String productName);

}
