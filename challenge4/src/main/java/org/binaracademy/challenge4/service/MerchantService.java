package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.response.MerchantResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MerchantService {

    boolean merchantExist(String merchantName);

    boolean addMerchant(Merchant merchant);
    boolean changeMerchantStat(boolean tof, String mname);
    boolean deleteMerchant(String mname);
    List<MerchantResponse> listOfMerchants();
    List<MerchantResponse> pageOfMerchants(int inputPage);
}
