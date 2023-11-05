package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.SecConfig.JwtUtils;
import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.User;
import org.binaracademy.challenge4.model.response.ErrorResponse;
import org.binaracademy.challenge4.model.response.MerchantResponse;
import org.binaracademy.challenge4.service.MerchantService;
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
import java.util.Objects;

@SpringBootTest
@AutoConfigureMockMvc
public class MerchantControllerTest {

    @InjectMocks
    MerchantController merchantController;

    @Mock
    MerchantService merchantService;

    @Mock
    UserService userService;

    @Mock
    JwtUtils jwtUtils;

    @Test
    void TEST_REQUESTADDMERCH() {
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
        Mockito.when(merchantService.addMerchant(Mockito.any())).thenReturn(true);

        ResponseEntity<String> entity = merchantController.requestAddMerchant(
                MerchantResponse.builder()
                        .merchantName("M1")
                        .merchantAddress("L1")
                        .build(),
                Mockito.anyString()
        );

        ResponseEntity<String> expected = new ResponseEntity<>("Successfully added merchant", HttpStatus.OK);

        Assertions.assertEquals(expected,entity);
    }

    @Test
    void TEST_REQUESTEDITMERCHSTAT() {
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
        Mockito.when(merchantService.changeMerchantStat(Mockito.anyBoolean(),Mockito.anyString())).thenReturn(true);

        ResponseEntity<ErrorResponse<Objects>> entity = merchantController.requestEditMerchantStat(
                "M1",
                false,
                Mockito.anyString());

        ResponseEntity<ErrorResponse<Objects>> expected = new ResponseEntity<>( ErrorResponse.<Objects>builder()
                .errorMessage("Successfully edited M1 status")
                .errorCode(HttpStatus.OK.value())
                .build(),HttpStatus.OK);

        Assertions.assertEquals(expected,entity);
    }

    @Test
    void TEST_REQUESTMERCHANTLIST() {
        Mockito.when(merchantService.pageOfMerchants(Mockito.anyInt())).thenReturn(
                Arrays.asList(
                        MerchantResponse.builder().merchantName("M1").merchantAddress("L1").build(),
                        MerchantResponse.builder().merchantName("M2").merchantAddress("L2").build(),
                        MerchantResponse.builder().merchantName("M3").merchantAddress("L3").build()
                )
        );

        ResponseEntity entity = merchantController.requestActiveMerchantList(0);
        ResponseEntity expected = new ResponseEntity<>(Arrays.asList(
                MerchantResponse.builder().merchantName("M1").merchantAddress("L1").build(),
                MerchantResponse.builder().merchantName("M2").merchantAddress("L2").build(),
                MerchantResponse.builder().merchantName("M3").merchantAddress("L3").build()
        ), HttpStatus.OK);
        Assertions.assertEquals(expected,entity);
    }

    @Test
    void TEST_DELETEMERCH() {
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
        Mockito.when(merchantService.deleteMerchant(Mockito.any())).thenReturn(true);

        ResponseEntity entity = merchantController.requestDeleteMerchant("M1", Mockito.anyString());
        ResponseEntity expected = new ResponseEntity<>("Successfully deleted M1", HttpStatus.OK);
        Assertions.assertEquals(expected,entity);
    }
}
