package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.response.MerchantResponse;
import org.binaracademy.challenge4.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class MerchantServiceImpl implements MerchantService{

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public void addMerchant(Merchant merchant) {
        merchantRepository.save(Optional.ofNullable(merchant)
                .filter(val -> val.getName() != null && val.getLocation() != null)
                .orElse(Merchant.builder()
                        .code("DELETETHIS1")
                        .build()));
        try {
            deleteMerchant("DELETETHIS1");
            System.out.println("Merchant berhasil ditambahkan!");
        } catch (Exception e) {
            System.out.println("Merchant berhasil ditambahkan!");
        }
    }

    @Override
    public void changeMerchantStat(boolean tof, String mname) {
        merchantRepository.queryUpdateMerchantStat(tof, mname);
    }

    @Override
    public void openNowMerchant() {
        List<MerchantResponse> merchantResponseList = this.listOfMerchants();
        AtomicInteger index = new AtomicInteger(0);
        merchantResponseList.forEach(val ->
            System.out.println(index.getAndIncrement()+". "+val.getMerchantName()+"\t|\t"+val.getMerchantAddress()));
    }

    @Override
    public void deleteMerchant(String mname) {
        merchantRepository.queryDeleteByName(mname);
    }

    @Override
    public List<MerchantResponse> listOfMerchants() {
        return merchantRepository.queryActiveMerchant(PageRequest.of(0,5))
                .stream()
                .map(merchant -> MerchantResponse
                        .builder()
                        .merchantName(merchant.getName())
                        .merchantAddress(merchant.getLocation())
                        .build())
                .collect(Collectors.toList());
    }
}
