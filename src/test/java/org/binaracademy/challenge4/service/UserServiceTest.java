package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.User;
import org.binaracademy.challenge4.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Test
    void TEST_USEREXISTANCE_SUCCESS() {
        Mockito.when(userRepository.queryFindUserByName(Mockito.anyString()))
                .thenReturn(User.builder().uname("user1").gmail("u1mail").build());

        Mockito.when(userRepository.queryFindUserByEmail(Mockito.anyString()))
                .thenReturn(User.builder().uname("user1").gmail("u1mail").build());

        boolean userExistValidation = userService.userIsExistAndCorrect("user1","u1mail");

        Mockito.verify(userRepository).queryFindUserByName(Mockito.anyString());
        Mockito.verify(userRepository).queryFindUserByEmail(Mockito.anyString());

        Assertions.assertTrue(userExistValidation);
    }

    @Test
    void TEST_USEREXISTANCE_FAILED() {
        Mockito.when(userRepository.queryFindUserByName(Mockito.anyString()))
                .thenReturn(User.builder().uname("user1").gmail("u2mail").build());

        Mockito.when(userRepository.queryFindUserByEmail(Mockito.anyString()))
                .thenReturn(User.builder().uname("user2").gmail("u1mail").build());

        boolean userExistValidation = userService.userIsExistAndCorrect("user1","u1mail");

        Mockito.verify(userRepository).queryFindUserByName(Mockito.anyString());
        Mockito.verify(userRepository).queryFindUserByEmail(Mockito.anyString());

        Assertions.assertFalse(userExistValidation);
    }

    @Test
    void TEST_UPDATEUSERNAME_SUCCESS() {
        Mockito.when(userRepository.queryFindUserByName(Mockito.anyString()))
                .thenReturn(User.builder().uname("user1").gmail("u1mail").build());
        Mockito.when(userRepository.queryFindUserByEmail(Mockito.anyString()))
                .thenReturn(User.builder().uname("user1").gmail("u1mail").build());

        boolean userUpdateValidation = userService.updateUsername(
                "user1new",
                "user1",
                "u1mail");

        Mockito.verify(userRepository).queryFindUserByName(Mockito.anyString());
        Mockito.verify(userRepository).queryFindUserByEmail(Mockito.anyString());
        Mockito.verify(userRepository).queryUpdateUsername(Mockito.anyString(),Mockito.anyString());

        Assertions.assertTrue(userUpdateValidation);
    }

    @Test
    void TEST_UPDATEUSERNAME_FAILED() {
        Mockito.when(userRepository.queryFindUserByName(Mockito.anyString()))
                .thenReturn(User.builder().uname("user1").gmail("u2mail").build());
        Mockito.when(userRepository.queryFindUserByEmail(Mockito.anyString()))
                .thenReturn(User.builder().uname("user2").gmail("u1mail").build());

        boolean userUpdateValidation = userService.updateUsername(
                "user1new",
                "user1",
                "u1mail");

        Mockito.verify(userRepository).queryFindUserByName(Mockito.anyString());
        Mockito.verify(userRepository).queryFindUserByEmail(Mockito.anyString());
        Mockito.verify(userRepository, Mockito.never()).queryUpdateUsername(Mockito.anyString(),Mockito.anyString());

        Assertions.assertFalse(userUpdateValidation);
    }

    @Test
    void TEST_UPDATEPASS_SUCCESS() {
        Mockito.when(userRepository.queryFindUserByName(Mockito.anyString()))
                .thenReturn(User.builder().uname("user1").gmail("u1mail").password("old").build());
        Mockito.when(userRepository.queryFindUserByEmail(Mockito.anyString()))
                .thenReturn(User.builder().uname("user1").gmail("u1mail").password("old").build());

        boolean userUpdateValidation = userService.updatePassword("new","user1","u1mail");

        Mockito.verify(userRepository).queryFindUserByName(Mockito.anyString());
        Mockito.verify(userRepository).queryFindUserByEmail(Mockito.anyString());
        Mockito.verify(userRepository).queryUpdatePassword(Mockito.anyString(),Mockito.anyString());
        Assertions.assertTrue(userUpdateValidation);
    }

    @Test
    void TEST_UPDATEPASS_FAILED() {
        Mockito.when(userRepository.queryFindUserByName(Mockito.anyString()))
                .thenReturn(User.builder().uname("user1").gmail("u2mail").password("old").build());
        Mockito.when(userRepository.queryFindUserByEmail(Mockito.anyString()))
                .thenReturn(User.builder().uname("user2").gmail("u1mail").password("old").build());

        boolean userUpdateValidation = userService.updatePassword(
                "new",
                "user1",
                "u1mail");

        Mockito.verify(userRepository).queryFindUserByName(Mockito.anyString());
        Mockito.verify(userRepository).queryFindUserByEmail(Mockito.anyString());
        Mockito.verify(userRepository, Mockito.never()).queryUpdatePassword(Mockito.anyString(),Mockito.anyString());

        Assertions.assertFalse(userUpdateValidation);
    }

    @Test
    void TEST_UPDATEMAIL_SUCCESS() {
        Mockito.when(userRepository.queryFindUserByName(Mockito.anyString()))
                .thenReturn(User.builder().uname("user1").gmail("u1mail").password("old").build());
        Mockito.when(userRepository.queryFindUserByEmail(Mockito.anyString()))
                .thenReturn(User.builder().uname("user1").gmail("u1mail").password("old").build());

        boolean userUpdateValidation = userService.updateEmail
                ("newmail","user1","u1mail");

        Mockito.verify(userRepository).queryFindUserByName(Mockito.anyString());
        Mockito.verify(userRepository).queryFindUserByEmail(Mockito.anyString());
        Mockito.verify(userRepository).queryUpdateEmail(Mockito.anyString(),Mockito.anyString());
        Assertions.assertTrue(userUpdateValidation);
    }

    @Test
    void TEST_UPDATEMAIL_FAILED() {
        Mockito.when(userRepository.queryFindUserByName(Mockito.anyString()))
                .thenReturn(User.builder().uname("user1").gmail("u2mail").password("old").build());
        Mockito.when(userRepository.queryFindUserByEmail(Mockito.anyString()))
                .thenReturn(User.builder().uname("user2").gmail("u1mail").password("old").build());

        boolean userUpdateValidation = userService.updateEmail(
                "u1newmail",
                "user1",
                "u1mail");

        Mockito.verify(userRepository).queryFindUserByName(Mockito.anyString());
        Mockito.verify(userRepository).queryFindUserByEmail(Mockito.anyString());
        Mockito.verify(userRepository, Mockito.never()).queryUpdateEmail(Mockito.anyString(),Mockito.anyString());

        Assertions.assertFalse(userUpdateValidation);
    }

    @Test
    void TEST_DELETEUSER_SUCCESS() {
        Mockito.when(userRepository.queryFindUserByName(Mockito.anyString()))
                .thenReturn(User.builder().uname("user1").gmail("u1mail").password("old").build());
        Mockito.when(userRepository.queryFindUserByEmail(Mockito.anyString()))
                .thenReturn(User.builder().uname("user1").gmail("u1mail").password("old").build());

        boolean userUpdateValidation = userService.deleteUser("user1","old", "u1mail");

        Mockito.verify(userRepository).queryFindUserByName(Mockito.anyString());
        Mockito.verify(userRepository).queryFindUserByEmail(Mockito.anyString());
        Mockito.verify(userRepository).queryDeleteUser(Mockito.anyString(),Mockito.anyString(),Mockito.anyString());
        Assertions.assertTrue(userUpdateValidation);
    }

    @Test
    void TEST_DELETEUSER_FAILED() {
        Mockito.when(userRepository.queryFindUserByName(Mockito.anyString()))
                .thenReturn(User.builder().uname("user1").gmail("u2mail").password("old").build());
        Mockito.when(userRepository.queryFindUserByEmail(Mockito.anyString()))
                .thenReturn(User.builder().uname("user2").gmail("u1mail").password("old").build());

        boolean userUpdateValidation = userService.deleteUser("user1","old", "u1mail");

        Mockito.verify(userRepository).queryFindUserByName(Mockito.anyString());
        Mockito.verify(userRepository).queryFindUserByEmail(Mockito.anyString());
        Mockito.verify(userRepository, Mockito.never()).queryDeleteUser(Mockito.anyString(),Mockito.anyString(),Mockito.anyString());
        Assertions.assertFalse(userUpdateValidation);
    }

    @Test
    void TEST_GETUSERBYNAME_SUCCESS() {
        Mockito.when(userRepository.queryFindUserByName("user1"))
                .thenReturn(User.builder().uname("user1").gmail("u1mail").password("old").build());

        User expected = User.builder().uname("user1").gmail("u1mail").password("old").build();
        User output = userService.getUserByName("user1");
        Mockito.verify(userRepository).queryFindUserByName(Mockito.anyString());
        Assertions.assertNotNull(output);
        Assertions.assertEquals(expected, output);
    }

    @Test
    void TEST_GETUSERBYNAME_FAILED() {
        Mockito.when(userRepository.queryFindUserByName("user1"))
                .thenReturn(User.builder().uname("user1").gmail("u1mail").password("old").build());
        User output = userService.getUserByName("use1");
        Mockito.verify(userRepository).queryFindUserByName(Mockito.anyString());
        Assertions.assertNull(output);
    }

}
