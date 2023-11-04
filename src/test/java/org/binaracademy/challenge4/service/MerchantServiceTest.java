package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.Product;
import org.binaracademy.challenge4.model.response.MerchantResponse;
import org.binaracademy.challenge4.repository.MerchantRepository;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class MerchantServiceTest {
    @InjectMocks
    MerchantServiceImpl merchantService;

    @Mock
    MerchantRepository merchantRepository;

    @Mock
    ProductRepository productRepository;

    @Test
    void TEST_MERCHANTEXISTANCE_SUCCESS() {
        Mockito.when(merchantRepository.queryFindMerchantByName("merch1"))
                .thenReturn(Merchant.builder()
                        .name("merch1")
                        .build());

        boolean existance = merchantService.merchantExist("merch1");
        Mockito.verify(merchantRepository).queryFindMerchantByName(Mockito.anyString());
        Assertions.assertTrue(existance);
    }

    @Test
    void TEST_MERCHANTEXISTANCE_FAILED() {
        Mockito.when(merchantRepository.queryFindMerchantByName("merch1"))
                .thenReturn(Merchant.builder()
                        .name("merch1")
                        .build());

        boolean existance = merchantService.merchantExist("merch2");
        Mockito.verify(merchantRepository).queryFindMerchantByName(Mockito.anyString());
        Assertions.assertFalse(existance);
    }

    @Test
    void TEST_ADDMERCHANT_SUCCESS() {
        Mockito.when(merchantRepository.save(Mockito.any(Merchant.class)))
                .thenReturn(Merchant.builder()
                        .name("merch1")
                        .location("loc1")
                        .build());

        boolean success = merchantService.addMerchant(Merchant.builder().name("merch1").location("loc1").build());
        Mockito.verify(merchantRepository).save(Mockito.any());
        Assertions.assertTrue(success);
    }

    @Test
    void TEST_ADDMERCHANT_FAILED() {
        Mockito.when(merchantRepository.save(Mockito.any(Merchant.class)))
                .thenReturn(Merchant.builder()
                        .name("merch1")
                        .location("")
                        .build());

        boolean success = merchantService.addMerchant(Merchant.builder().name("merch1").location("").build());
        Mockito.verify(merchantRepository, Mockito.never()).save(Mockito.any());
        Assertions.assertFalse(success);
    }

    @Test
    void TEST_EDITMERCHANTSTAT_SUCCESS() {
        Mockito.when(merchantRepository.queryFindMerchantByName("merch1"))
                .thenReturn(Merchant.builder()
                        .name("merch1")
                        .status(true)
                        .build());

        boolean edited = merchantService.changeMerchantStat(false, "merch1");
        Mockito.verify(merchantRepository).queryFindMerchantByName(Mockito.anyString());
        Mockito.verify(merchantRepository).queryUpdateMerchantStat(Mockito.anyBoolean(), Mockito.anyString());
        Assertions.assertTrue(edited);
    }

    @Test
    void TEST_EDITMERCHANTSTAT_FAILED() {
        Mockito.when(merchantRepository.queryFindMerchantByName(Mockito.anyString()))
                .thenReturn(Merchant.builder()
                        .name("merch1")
                        .status(true)
                        .build());

        boolean edited = merchantService.changeMerchantStat(false, "merch2");
        Mockito.verify(merchantRepository).queryFindMerchantByName(Mockito.anyString());
        Mockito.verify(merchantRepository, Mockito.never()).queryUpdateMerchantStat(Mockito.anyBoolean(), Mockito.anyString());
        Assertions.assertFalse(edited);
    }

    @Test
    void TEST_DELETEMERCHANT_SUCCESS() {
        Mockito.when(merchantRepository.queryFindMerchantByName(Mockito.anyString()))
                .thenReturn(Merchant.builder()
                        .name("merch1")
                        .status(true)
                        .build());

        Mockito.when(productRepository.queryListOfProdFromMerch(Mockito.anyString()))
                .thenReturn(Arrays.asList(
                        Product.builder()
                                .name("prod1")
                                .merchant(Merchant.builder()
                                        .name("merch1")
                                        .status(true)
                                        .build())
                                .build(),
                        Product.builder()
                                .name("prod2")
                                .merchant(Merchant.builder()
                                        .name("merch1")
                                        .status(true)
                                        .build())
                                .build()
                ));

        boolean deleted = merchantService.deleteMerchant("merch1");
        Mockito.verify(merchantRepository).queryDeleteByName("merch1");
        Mockito.verify(productRepository).queryListOfProdFromMerch("merch1");
        Mockito.verify(merchantRepository).queryDeleteByName("merch1");
        Assertions.assertTrue(deleted);
    }

    @Test
    void TEST_DELETEMERCHANT_FAILED() {
        Mockito.when(merchantRepository.queryFindMerchantByName(Mockito.anyString()))
                .thenReturn(Merchant.builder()
                        .name("merch2")
                        .status(true)
                        .build());

        Mockito.when(productRepository.queryListOfProdFromMerch(Mockito.anyString()))
                .thenReturn(Arrays.asList(
                        Product.builder()
                                .name("prod1")
                                .merchant(Merchant.builder()
                                        .name("merch2")
                                        .status(true)
                                        .build())
                                .build(),
                        Product.builder()
                                .name("prod2")
                                .merchant(Merchant.builder()
                                        .name("merch2")
                                        .status(true)
                                        .build())
                                .build()
                ));

        boolean deleted = merchantService.deleteMerchant("merch1");
        Mockito.verify(merchantRepository).queryFindMerchantByName("merch1");
        Mockito.verify(productRepository, Mockito.never()).queryListOfProdFromMerch("merch1");
        Mockito.verify(merchantRepository, Mockito.never()).queryDeleteByName("merch1");
        Assertions.assertFalse(deleted);
    }

    @Test
    void TEST_RETURNRESPONSE() {
        Mockito.when(merchantRepository.queryFindMerchantByName(Mockito.anyString()))
                .thenReturn(Merchant.builder()
                        .name("merch1")
                        .location("loc1")
                        .build());

        MerchantResponse returnedResponse = merchantService.merchantOfName("merch1");
        MerchantResponse expectedResponse = MerchantResponse.builder()
                .merchantName("merch1").merchantAddress("loc1").build();
        Assertions.assertEquals(expectedResponse, returnedResponse);
    }

    @Test
    void TEST_LISTRETURNRESPONSE() {
        Mockito.when(merchantRepository.queryActiveMerchant())
                .thenReturn(Arrays.asList(
                        Merchant.builder()
                                .name("merch1")
                                .location("loc1")
                                .build(),
                        Merchant.builder()
                                .name("merch2")
                                .location("loc2")
                                .build()
                ));

        List<MerchantResponse> returnedResponse = merchantService.listOfMerchants();
        List<MerchantResponse> expectedResponse = Arrays.asList(MerchantResponse.builder()
                .merchantName("merch1").merchantAddress("loc1").build(),
                MerchantResponse.builder()
                        .merchantName("merch2").merchantAddress("loc2").build());
        Assertions.assertEquals(expectedResponse, returnedResponse);
    }

}
