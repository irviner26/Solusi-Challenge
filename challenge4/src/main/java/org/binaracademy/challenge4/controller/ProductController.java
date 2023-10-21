package org.binaracademy.challenge4.controller;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.response.ErrorResponse;
import org.binaracademy.challenge4.model.response.ProductResponse;
import org.binaracademy.challenge4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(value = "/api/binarfud/product/add")
    public ResponseEntity<ErrorResponse<Object>> requestAddProduct(@RequestParam("merchant") String merchantName,
                                            @RequestBody ProductResponse productResponse) {
        if (productService.addProduct(productService.productBuilder(productResponse.getProductName(),
                productResponse.getProductPrice(),
                merchantName))) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .entity(productResponse)
                    .errorMessage("Successfully added product "+productResponse.getProductName()+" to "+merchantName)
                    .errorCode(HttpStatus.OK.value())
                    .build(),
                    HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .errorMessage("Failed to add product "+productResponse+" to "+merchantName)
                    .errorCode(HttpStatus.NOT_FOUND.value())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/api/binarfud/product/edit-name/{oldName}")
    public ResponseEntity<ErrorResponse<Object>> requestUpdateProductName(@PathVariable("oldName") String prodOldName,
                                           @RequestBody ProductResponse productResponse,
                                           @RequestParam("merchant") String merchantName) {
        if (productService.updateProductName(merchantName,
                prodOldName,
                productResponse.getProductName())) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .entity(productResponse)
                    .errorMessage("Successfully edited product "+prodOldName+" -> "+productResponse.getProductName())
                    .errorCode(HttpStatus.OK.value())
                    .build(),
                    HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .errorMessage("Failed to edit product "+prodOldName+" to "+productResponse+" from "+merchantName)
                    .errorCode(HttpStatus.NOT_FOUND.value())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/api/binarfud/product/edit-price/{prodName}")
    public ResponseEntity<ErrorResponse<Object>> requestUpdateProductPrice(@PathVariable("prodName") String prodName,
                                            @RequestBody ProductResponse productResponse,
                                            @RequestParam("merchant") String merchantName) {
        if (productService.updateProductPrice(merchantName
                ,prodName
                ,productResponse.getProductPrice())) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .entity(productResponse)
                    .errorMessage("Successfully edited product "+prodName+" price")
                    .errorCode(HttpStatus.OK.value())
                    .build(),
                    HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .errorMessage("Failed to edit product "+prodName+" to "+productResponse+" from "+merchantName)
                    .errorCode(HttpStatus.NOT_FOUND.value())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/api/binarfud/product/delete/{prodName}")
    public ResponseEntity<ErrorResponse<Object>> requestDeleteProduct(@RequestParam("merchant") String merchantName,
                                       @PathVariable("prodName") String prodName) {
        if (productService.removeProductOf(prodName,merchantName)) {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .errorMessage("Successfully deleted product "+prodName)
                    .errorCode(HttpStatus.OK.value())
                    .build(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .errorMessage("Failed to delete "+prodName)
                    .errorCode(HttpStatus.NOT_FOUND.value())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/api/binarfud/product/get-product/{merchant}", produces = "application/json")
    public List<ProductResponse> requestGetProduct(@PathVariable("merchant") String merchantName,
                                                   @RequestParam("page") int page) {
        return productService.ListOfAvailableProduct(merchantName,page);
    }

}
