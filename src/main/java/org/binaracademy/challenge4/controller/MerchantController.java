package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.secconfig.JwtUtils;
import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.response.ErrorResponse;
import org.binaracademy.challenge4.model.response.MerchantResponse;
import org.binaracademy.challenge4.service.MerchantService;
import org.binaracademy.challenge4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@RestController
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping(value = "/api/binarfud/merchant/add", consumes = "application/json")
    public ResponseEntity<String> requestAddMerchant(@RequestBody MerchantResponse merchant,
                                                     @RequestHeader(name = "Authorization") String token) throws ExecutionException, InterruptedException {
        if (merchantService.merchantObjectWithName(merchant.getMerchantName()).get().getUser().equals(userService.getUserByName(jwtUtils.getUsernameFromJwtToken(token)))) {
            try {
                if (merchantService.addMerchant(Merchant.builder()
                        .name(merchant.getMerchantName())
                        .location(merchant.getMerchantAddress())
                        .status(true)
                        .user(userService.getUserByName(jwtUtils.getUsernameFromJwtToken(token)))
                        .build()).get()) {
                    return new ResponseEntity<>("Successfully added merchant", HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<>("Failed to add "+merchant+" to database", HttpStatus.NOT_ACCEPTABLE);
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return new ResponseEntity<>("Failed to add "+merchant+" to database", HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping(value = "/api/binarfud/merchant/edit/status/{merchant}")
    public ResponseEntity<ErrorResponse<Objects>> requestEditMerchantStat(@PathVariable("merchant") String merchantName,
                                          @RequestParam("nStat") boolean newStat, @RequestHeader(name = "Authorization") String token) throws ExecutionException, InterruptedException {
        if (merchantService.merchantObjectWithName(merchantName).get().getUser().equals(userService.getUserByName(jwtUtils.getUsernameFromJwtToken(token)))) {
            if (merchantService.changeMerchantStat(newStat,merchantName).get()) {
                return new ResponseEntity<>( ErrorResponse.<Objects>builder()
                        .errorMessage("Successfully edited "+merchantName+" status")
                        .errorCode(HttpStatus.OK.value())
                        .build(),HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>( ErrorResponse.<Objects>builder()
                        .errorMessage("Failed to edit merchant "+merchantName+" status")
                        .errorCode(HttpStatus.NOT_FOUND.value())
                        .build(),HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>( ErrorResponse.<Objects>builder()
                    .errorMessage("Failed to edit merchant "+merchantName+" status")
                    .errorCode(HttpStatus.NOT_FOUND.value())
                    .build(),HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/api/binarfud/merchant/get-active/{pageNumber}", produces = "application/json")
    public ResponseEntity requestActiveMerchantList(@PathVariable("pageNumber") int pageNumber) throws ExecutionException, InterruptedException {
        List<MerchantResponse> activeMerchantOfPage = merchantService.pageOfMerchants(pageNumber).get();
        if (Objects.nonNull(activeMerchantOfPage)) {
            return new ResponseEntity<>(activeMerchantOfPage, HttpStatus.OK);
        }
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorCode(HttpStatus.NOT_FOUND.value())
                .errorMessage("No Active Merchant")
                .build(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/api/binarfud/merchant/delete/{merchantName}")
    public ResponseEntity requestDeleteMerchant(@PathVariable("merchantName") String mercName, @RequestHeader(name = "Authorization") String token) throws ExecutionException, InterruptedException {
        if (merchantService.merchantObjectWithName(mercName).get().getUser().equals(userService.getUserByName(jwtUtils.getUsernameFromJwtToken(token)))) {
            if (merchantService.deleteMerchant(mercName).get()) {
                return new ResponseEntity<>("Successfully deleted "+mercName, HttpStatus.OK);
            }
            else return new ResponseEntity<>(ErrorResponse.builder()
                    .errorCode(HttpStatus.NOT_FOUND.value())
                    .errorMessage("Could not delete "+mercName)
                    .build(), HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>(ErrorResponse.builder()
                .errorCode(HttpStatus.NOT_FOUND.value())
                .errorMessage("Could not delete "+mercName)
                .build(), HttpStatus.NOT_FOUND);
    }

}
