package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.response.MerchantResponse;
import org.binaracademy.challenge4.repository.MerchantRepository;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MerchantServiceImpl implements MerchantService{

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean merchantExist(String merchantName) {
        try {
            Merchant merchant = merchantRepository.queryFindMerchantByName(merchantName);
            return (Objects.nonNull(merchant));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addMerchant(Merchant merchant) {
        try {
            log.info("Attempting to add merchant");
            merchantRepository.save(Objects.requireNonNull(Optional.ofNullable(merchant)
                    .filter(val -> val.getName() != null && val.getLocation() != null)
                    .orElse(null)));
            log.info("Successfully added merchant {} to database.", merchant.getName());
            return true;
        } catch (Exception e) {
            log.error("Merchant failed to be added.");
            log.info("Cause: "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean changeMerchantStat(boolean tof, String mname) {
        if (this.merchantExist(mname)) {
            log.info("Attempting to edit merchant");
            merchantRepository.queryUpdateMerchantStat(tof, mname);
            log.info("Successfully changed merchant {} status.", mname);
            return true;
        } else {
            log.error("Failed to edit merchant status.");
            return false;
        }
    }

    @Override
    public boolean deleteMerchant(String mname) {
        if (this.merchantExist(mname)) {
            log.warn("Deleting all products from merchant");
            productRepository.queryListOfProdFromMerch(mname).forEach(product -> productRepository.delete(product));
            log.info("Attempting to delete merchant");
            merchantRepository.queryDeleteByName(mname);
            log.info("Successfully deleted merchant {}.", mname);
            return true;
        } else {
            log.error("Failed to delete merchant.");
            return false;
        }
    }

    @Override
    public List<MerchantResponse> listOfMerchants() {
        return merchantRepository.queryActiveMerchant()
                .stream()
                .map(merchant -> MerchantResponse
                        .builder()
                        .merchantName(merchant.getName())
                        .merchantAddress(merchant.getLocation())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<MerchantResponse> pageOfMerchants(int inputPage) {
        Page<Merchant> merchants = merchantRepository.queryPagedMerchantList(PageRequest.of(inputPage, 2));
        return merchants.stream().map(merchant -> MerchantResponse.builder()
                .merchantName(merchant.getName())
                .merchantAddress(merchant.getLocation()).build())
                .collect(Collectors.toList());
    }

    @Override
    public MerchantResponse merchantOfName(String merchantName) {
        if (this.merchantExist(merchantName)) {
            Merchant merchant = merchantRepository.queryFindMerchantByName(merchantName);
            return MerchantResponse.builder().merchantName(merchant.getName()).merchantAddress(merchant.getLocation()).build();
        } else {
            log.error("Could not find merchant with name {}", merchantName);
            return null;
        }
    }

    @Override
    public Merchant merchantObjectWithName(String merchantName) {
        return merchantRepository.queryFindMerchantByName(merchantName);
    }
}
