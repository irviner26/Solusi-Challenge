package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.SecConfig.JwtUtils;
import org.binaracademy.challenge4.model.User;
import org.binaracademy.challenge4.model.response.ErrorResponse;
import org.binaracademy.challenge4.model.response.UserResponse;
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


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Mock
    JwtUtils jwtUtils;

    @Test
    void TEST_REQUESTEDITUSERNAME() {
        Mockito.when(userService.getUserByName("u"))
                .thenReturn(User.builder().uname("u").gmail("e").build());
        Mockito.when(jwtUtils.getUsernameFromJwtToken(Mockito.anyString()))
                .thenReturn("u");
        Mockito.when(userService.updateUsername(Mockito.anyString(),Mockito.anyString(),Mockito.anyString()))
                .thenReturn(true);

        ResponseEntity<ErrorResponse<Object>> entity = userController.requestEditUsername("u"
                ,UserResponse.builder().usernameR("u1").emailR("e").build()
                ,Mockito.anyString());

        ResponseEntity<ErrorResponse<Object>> expect = new ResponseEntity<>(
                ErrorResponse.builder()
                        .entity(UserResponse.builder().usernameR("u1").emailR("e").build())
                        .errorMessage("Successfully edited u username")
                        .errorCode(HttpStatus.OK.value())
                        .build()
                , HttpStatus.OK);

        Assertions.assertEquals(expect,entity);
    }

    @Test
    void TEST_REQUESTEDITPASS() {
        Mockito.when(userService.getUserByName("u"))
                .thenReturn(User.builder().uname("u").password("p1").gmail("e").build());
        Mockito.when(jwtUtils.getUsernameFromJwtToken(Mockito.anyString()))
                .thenReturn("u");
        Mockito.when(userService.updatePassword(Mockito.anyString(),Mockito.anyString(),Mockito.anyString()))
                .thenReturn(true);

        ResponseEntity<ErrorResponse<Object>> entity = userController.requestEditPassword("u"
                ,UserResponse.builder().usernameR("u").passwordR("p2").emailR("e").build()
                ,Mockito.anyString());

        ResponseEntity<ErrorResponse<Object>> expect = new ResponseEntity<>(
                ErrorResponse.builder()
                        .entity(UserResponse.builder().usernameR("u").passwordR("p2").emailR("e").build())
                        .errorMessage("Successfully edited u password")
                        .errorCode(HttpStatus.OK.value())
                        .build()
                , HttpStatus.OK);

        Assertions.assertEquals(expect,entity);

    }

    @Test
    void TEST_REQUESTEDITEMAIL() {
        Mockito.when(userService.getUserByName("u"))
                .thenReturn(User.builder().uname("u").password("p1").gmail("e").build());
        Mockito.when(jwtUtils.getUsernameFromJwtToken(Mockito.anyString()))
                .thenReturn("u");
        Mockito.when(userService.updateEmail(Mockito.anyString(),Mockito.anyString(),Mockito.anyString()))
                .thenReturn(true);

        ResponseEntity<String> entity = userController.requestEditEmail("u"
                ,"e1","e"
                ,Mockito.anyString());

        ResponseEntity<String> expect = new ResponseEntity<>("Successfully edited u email", HttpStatus.OK);

        Assertions.assertEquals(expect,entity);
    }

    @Test
    void TEST_REQUESTDELETE() {
        Mockito.when(userService.getUserByName("u"))
                .thenReturn(User.builder().uname("u").password("p1").gmail("e").build());
        Mockito.when(jwtUtils.getUsernameFromJwtToken(Mockito.anyString()))
                .thenReturn("u");
        Mockito.when(userService.deleteUser(Mockito.anyString(),Mockito.anyString(),Mockito.anyString()))
                .thenReturn(true);
        ResponseEntity<String> entity = userController.requestDeleteUser("u","p1","e",Mockito.anyString());
        ResponseEntity<String> expect = new ResponseEntity<>("Successfully removed user u", HttpStatus.OK);
    }
}
