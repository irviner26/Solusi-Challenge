package org.binaracademy.challenge4.controller;


import org.binaracademy.challenge4.secconfig.JwtUtils;
import org.binaracademy.challenge4.model.response.ErrorResponse;
import org.binaracademy.challenge4.model.response.UserResponse;
import org.binaracademy.challenge4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;


    /*@PostMapping(value = "/api/binarfud/user/add", consumes = "application/json")
    public ResponseEntity<String> requestAddUser(@RequestBody User user) {
        if(userService.addUser(user)) return new ResponseEntity<>("Successfully added user", HttpStatus.OK);
        else return new ResponseEntity<>("Could not add user", HttpStatus.NOT_ACCEPTABLE);
    }*/

    @PutMapping(value = "/api/binarfud/user/edit/username/{username}")
    public ResponseEntity<ErrorResponse<Object>> requestEditUsername(@PathVariable("username") String oldUsername,
                                                             @RequestBody UserResponse userResponse, @RequestHeader(name = "Authorization") String token) {
        if (userService.getUserByName(oldUsername).equals(userService.getUserByName(jwtUtils.getUsernameFromJwtToken(token)))) {
            try {
                if (userService.updateUsername(userResponse.getUsernameR(),
                        oldUsername,
                        userResponse.getEmailR()).get()) {
                    ErrorResponse<Object> ER = ErrorResponse.builder()
                            .entity(userResponse)
                            .errorMessage("Successfully edited "+oldUsername+" username")
                            .errorCode(HttpStatus.OK.value()).build();
                    return new ResponseEntity<>(ER, HttpStatus.OK);
                }
                else return new ResponseEntity<>(ErrorResponse.builder()
                        .errorMessage("Could not edit user "+oldUsername+" username")
                        .errorCode(HttpStatus.NOT_FOUND.value())
                        .build(), HttpStatus.NOT_FOUND);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        } else return new ResponseEntity<>(ErrorResponse.builder()
                .errorMessage("Could not edit user "+oldUsername+" username")
                .errorCode(HttpStatus.NOT_FOUND.value())
                .build(), HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/api/binarfud/user/edit/password/{username}")
    public ResponseEntity<ErrorResponse<Object>> requestEditPassword(@PathVariable("username") String username,
                                      @RequestBody UserResponse userResponse, @RequestHeader(name = "Authorization") String token) {
        if (userService.getUserByName(username).equals(userService.getUserByName(jwtUtils.getUsernameFromJwtToken(token)))) {
            try {
                if (userService.updatePassword(userResponse.getPasswordR(),
                        username,
                        userResponse.getEmailR()).get()) {
                    ErrorResponse<Object> ER = ErrorResponse.builder()
                            .entity(userResponse)
                            .errorMessage("Successfully edited "+username+" password")
                            .errorCode(HttpStatus.OK.value()).build();
                    return new ResponseEntity<>(ER, HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<>(ErrorResponse.builder()
                            .errorMessage("Could not edit user "+username+" password")
                            .errorCode(HttpStatus.NOT_FOUND.value())
                            .build(), HttpStatus.NOT_FOUND);
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }

        } else {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .errorMessage("Could not edit user "+username+" password")
                    .errorCode(HttpStatus.NOT_FOUND.value())
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/api/binarfud/user/edit/email/{username}")
    public ResponseEntity<String> requestEditEmail(@PathVariable("username") String username,
                                   @RequestParam("newE") String newEmail,
                                   @RequestParam("uE") String oldEmail, @RequestHeader(name = "Authorization") String token) {
        if (userService.getUserByName(username).equals(userService.getUserByName(jwtUtils.getUsernameFromJwtToken(token)))) {
            try {
                if (userService.updateEmail(newEmail
                        ,username
                        ,oldEmail).get()) {
                    return new ResponseEntity<>("Successfully edited "+username+" email", HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<>("Could not edit user "+username+" email", HttpStatus.NOT_ACCEPTABLE);
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        } else {
            return new ResponseEntity<>("Could not edit user "+username+" email", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/api/binarfud/user/delete/{username}")
    public ResponseEntity<String> requestDeleteUser(@PathVariable("username") String username,
                                    @RequestParam("uP") String password,
                                    @RequestParam("uE") String email,
                                    @RequestHeader(name = "Authorization") String token) {
        if (userService.getUserByName(username).equals(userService.getUserByName(jwtUtils.getUsernameFromJwtToken(token)))) {
            try {
                if (userService.deleteUser(username,password,email).get()) return new ResponseEntity<>("Successfully removed user "+username, HttpStatus.OK);
                else return new ResponseEntity<>("Could not delete user "+username, HttpStatus.NOT_ACCEPTABLE);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        } else return new ResponseEntity<>("Could not delete user "+username, HttpStatus.NOT_ACCEPTABLE);
    }

}
