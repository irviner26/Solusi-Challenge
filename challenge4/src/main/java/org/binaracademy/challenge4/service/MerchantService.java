package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.response.MerchantResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MerchantService {
    // Method ini berfungsi untuk menambahkan merchant
    void addMerchant(Merchant merchant);

    // Method ini berfungsi untuk men-set status dari merchant
    void changeMerchantStat(boolean tof, String mname);

    // Method ini berfungsi untuk menampilkan merchant yang sedang buka
    void openNowMerchant();

    void deleteMerchant(String mname);

    List<MerchantResponse> listOfMerchants();
}
