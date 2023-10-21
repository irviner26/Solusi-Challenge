package org.binaracademy.challenge4.controller;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.response.ErrorResponse;
import org.binaracademy.challenge4.model.response.MerchantResponse;
import org.binaracademy.challenge4.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @PostMapping(value = "/api/binarfud/merchant/add", consumes = "application/json")
    public ResponseEntity<String> requestAddMerchant(@RequestBody Merchant merchant) {
        if (merchantService.addMerchant(merchant)) return new ResponseEntity<>("Successfully added merchant", HttpStatus.OK);
        else return new ResponseEntity<>("Failed to add "+merchant+" to database", HttpStatus.OK);
    }

    @PutMapping(value = "/api/binarfud/merchant/edit/status/{merchant}")
    public ResponseEntity<ErrorResponse<Objects>> requestEditMerchantStat(@PathVariable("merchant") String merchantName,
                                          @RequestParam("nStat") boolean newStat) {
        if (merchantService.changeMerchantStat(newStat,merchantName)) {
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
    }

    @GetMapping(value = "/api/binarfud/merchant/get-active/{pageNumber}", produces = "application/json")
    public ResponseEntity requestActiveMerchantList(@PathVariable("pageNumber") int pageNumber) {
        List<MerchantResponse> activeMerchantOfPage = merchantService.pageOfMerchants(pageNumber);
        if (Objects.nonNull(activeMerchantOfPage)) {
            return new ResponseEntity<>(activeMerchantOfPage, HttpStatus.OK);
        }
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorCode(HttpStatus.NOT_FOUND.value())
                .errorMessage("No Active Merchant")
                .build(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/api/binarfud/merchant/delete/{merchantName}")
    public ResponseEntity requestDeleteMerchant(@PathVariable("merchantName") String mercName) {
        if (merchantService.deleteMerchant(mercName)) {
            return new ResponseEntity<>("Successfully deleted "+mercName, HttpStatus.OK);
        }
        else return new ResponseEntity<>(ErrorResponse.builder()
                .errorCode(HttpStatus.NOT_FOUND.value())
                .errorMessage("Could not delete "+mercName)
                .build(), HttpStatus.NOT_FOUND);
    }

}
