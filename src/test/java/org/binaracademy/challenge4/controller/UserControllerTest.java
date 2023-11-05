package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.SecConfig.JwtUtils;
import org.binaracademy.challenge4.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Mock
    JwtUtils jwtUtils;


}
