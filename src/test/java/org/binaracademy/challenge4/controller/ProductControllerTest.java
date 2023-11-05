package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.SecConfig.JwtUtils;
import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.User;
import org.binaracademy.challenge4.model.response.ErrorResponse;
import org.binaracademy.challenge4.model.response.MerchantResponse;
import org.binaracademy.challenge4.model.response.ProductResponse;
import org.binaracademy.challenge4.service.MerchantService;
import org.binaracademy.challenge4.service.ProductService;
import org.binaracademy.challenge4.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productService;

    @Mock
    MerchantService merchantService;

    @Mock
    UserService userService;

    @Mock
    JwtUtils jwtUtils;

    @Test
    void TEST_REQUESTADDPRODUCT() {
        Mockito.when(merchantService.merchantOfName(Mockito.anyString()))
                        .thenReturn(MerchantResponse.builder()
                                .merchantName("M1")
                                .merchantAddress("L1")
                                .build());
        Mockito.when(merchantService.merchantObjectWithName(Mockito.anyString()))
                .thenReturn(Merchant
                        .builder()
                        .name("M1")
                        .location("L1")
                        .status(true)
                        .user(User.builder()
                                .uname("U1")
                                .build())
                        .build());
        Mockito.when(jwtUtils.getUsernameFromJwtToken(Mockito.anyString()))
                .thenReturn("U1");
        Mockito.when(userService.getUserByName("U1"))
                .thenReturn(User.builder()
                        .uname("U1")
                        .build());
        Mockito.when(productService.addProduct(Mockito.any())).thenReturn(true);

        ResponseEntity<ErrorResponse<Object>> output =
                productController.requestAddProduct("M1",
                        ProductResponse.builder().productName("P1").productPrice(1).build(),
                        Mockito.anyString());
        ResponseEntity<ErrorResponse<Object>> expect = new ResponseEntity<>(ErrorResponse.builder()
                .entity(ProductResponse.builder().productName("P1").productPrice(1).build())
                .errorMessage("Successfully added product P1 to M1")
                .errorCode(HttpStatus.OK.value())
                .build(),
                HttpStatus.OK);
        Assertions.assertEquals(expect,output);
    }

    @Test
    void TEST_REQUESTUPDATENAME() {
        Mockito.when(merchantService.merchantOfName(Mockito.anyString()))
                .thenReturn(MerchantResponse.builder()
                        .merchantName("M1")
                        .merchantAddress("L1")
                        .build());
        Mockito.when(merchantService.merchantObjectWithName(Mockito.anyString()))
                .thenReturn(Merchant
                        .builder()
                        .name("M1")
                        .location("L1")
                        .status(true)
                        .user(User.builder()
                                .uname("U1")
                                .build())
                        .build());
        Mockito.when(jwtUtils.getUsernameFromJwtToken(Mockito.anyString()))
                .thenReturn("U1");
        Mockito.when(userService.getUserByName("U1"))
                .thenReturn(User.builder()
                        .uname("U1")
                        .build());
        Mockito.when(productService.updateProductName(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(true);

        ResponseEntity<ErrorResponse<Object>> output = productController.requestUpdateProductName("P1",
                ProductResponse.builder().productName("PR1").productPrice(1).build(),
                "M1",
                Mockito.anyString()
                );

        ResponseEntity<ErrorResponse<Object>> expect = new ResponseEntity<>(ErrorResponse.builder()
                .entity(ProductResponse.builder().productName("PR1").productPrice(1).build())
                .errorMessage("Successfully edited product P1 -> PR1")
                .errorCode(HttpStatus.OK.value())
                .build(),
                HttpStatus.OK);
        Assertions.assertEquals(expect,output);
    }

    @Test
    void TEST_REQUESTUPDATEPRICE() {
        Mockito.when(merchantService.merchantOfName(Mockito.anyString()))
                .thenReturn(MerchantResponse.builder()
                        .merchantName("M1")
                        .merchantAddress("L1")
                        .build());
        Mockito.when(merchantService.merchantObjectWithName(Mockito.anyString()))
                .thenReturn(Merchant
                        .builder()
                        .name("M1")
                        .location("L1")
                        .status(true)
                        .user(User.builder()
                                .uname("U1")
                                .build())
                        .build());
        Mockito.when(jwtUtils.getUsernameFromJwtToken(Mockito.anyString()))
                .thenReturn("U1");
        Mockito.when(userService.getUserByName("U1"))
                .thenReturn(User.builder()
                        .uname("U1")
                        .build());
        Mockito.when(productService.updateProductPrice(Mockito.anyString(), Mockito.anyString(), Mockito.anyDouble())).thenReturn(true);
        ResponseEntity<ErrorResponse<Object>> output = productController.requestUpdateProductPrice("P1",
                ProductResponse.builder().productName("P1").productPrice(2).build(),
                "M1",
                Mockito.anyString()
        );

        ResponseEntity<ErrorResponse<Object>> expect = new ResponseEntity<>(ErrorResponse.builder()
                .entity(ProductResponse.builder().productName("P1").productPrice(2).build())
                .errorMessage("Successfully edited product P1 price")
                .errorCode(HttpStatus.OK.value())
                .build(),
                HttpStatus.OK);
        Assertions.assertEquals(expect,output);
    }

    @Test
    void TEST_REQUESTDELETEPRODUCT() {
        Mockito.when(merchantService.merchantOfName(Mockito.anyString()))
                .thenReturn(MerchantResponse.builder()
                        .merchantName("M1")
                        .merchantAddress("L1")
                        .build());
        Mockito.when(merchantService.merchantObjectWithName(Mockito.anyString()))
                .thenReturn(Merchant
                        .builder()
                        .name("M1")
                        .location("L1")
                        .status(true)
                        .user(User.builder()
                                .uname("U1")
                                .build())
                        .build());
        Mockito.when(jwtUtils.getUsernameFromJwtToken(Mockito.anyString()))
                .thenReturn("U1");
        Mockito.when(userService.getUserByName("U1"))
                .thenReturn(User.builder()
                        .uname("U1")
                        .build());
        Mockito.when(productService.removeProductOf(Mockito.anyString(),Mockito.anyString())).thenReturn(true);
        ResponseEntity<ErrorResponse<Object>> output = productController.requestDeleteProduct("M1","P1",Mockito.anyString());
        ResponseEntity<ErrorResponse<Object>> expect = new ResponseEntity<>(ErrorResponse.builder()
                .errorMessage("Successfully deleted product P1")
                .errorCode(HttpStatus.OK.value())
                .build(), HttpStatus.OK);
        Assertions.assertEquals(expect,output);
    }

    @Test
    void TEST_REQUESTGETPRODUCT() {
        Mockito.when(productService.ListOfAvailableProduct(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(
                        Arrays.asList(
                                ProductResponse.builder()
                                        .productName("P1")
                                        .productPrice(1)
                                        .build(),
                                ProductResponse.builder()
                                        .productName("P2")
                                        .productPrice(2)
                                        .build(),
                                ProductResponse.builder()
                                        .productName("P3")
                                        .productPrice(3)
                                        .build()
                        )
                );
        List<ProductResponse> output = productController.requestGetProduct("M1", 0);
        List<ProductResponse> expect = Arrays.asList(
                ProductResponse.builder()
                        .productName("P1")
                        .productPrice(1)
                        .build(),
                ProductResponse.builder()
                        .productName("P2")
                        .productPrice(2)
                        .build(),
                ProductResponse.builder()
                        .productName("P3")
                        .productPrice(3)
                        .build()
        );
        Assertions.assertEquals(expect,output);
    }
}
