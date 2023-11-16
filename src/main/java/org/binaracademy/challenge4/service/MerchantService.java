package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.response.MerchantResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface MerchantService {

    CompletableFuture<Boolean> merchantExist(String merchantName);

    CompletableFuture<Boolean> addMerchant(Merchant merchant);
    CompletableFuture<Boolean> changeMerchantStat(boolean tof, String mname);
    CompletableFuture<Boolean> deleteMerchant(String mname);
    CompletableFuture<List<MerchantResponse>> listOfMerchants();
    CompletableFuture<List<MerchantResponse>> pageOfMerchants(int inputPage);
    CompletableFuture<MerchantResponse> merchantOfName(String merchantName);
   CompletableFuture<Merchant> merchantObjectWithName(String merchantName);
}
