package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.response.MerchantResponse;
import org.binaracademy.challenge4.repository.MerchantRepository;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MerchantServiceImpl implements MerchantService{

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private ProductRepository productRepository;

    @Async
    @Transactional(readOnly = true)
    @Override
    public CompletableFuture<Boolean> merchantExist(String merchantName) {
        try {
            Merchant merchant = merchantRepository.queryFindMerchantByName(merchantName);
            return CompletableFuture.supplyAsync(() -> Objects.nonNull(merchant) && merchantName.equals(merchant.getName()));
        } catch (Exception e) {
            return CompletableFuture.supplyAsync(() -> Boolean.FALSE);
        }
    }

    @Async
    @Transactional
    @Override
    public CompletableFuture<Boolean> addMerchant(Merchant merchant) {
        try {
            log.info("Attempting to add merchant");
            merchantRepository.save(Objects.requireNonNull(Optional.ofNullable(merchant)
                    .filter(val -> val.getName() != null &&
                            val.getLocation() != null &&
                            !val.getName().isEmpty() &&
                            !val.getLocation().isEmpty())
                    .orElse(null)));
            log.info("Successfully added merchant {} to database.", merchant.getName());
            return CompletableFuture.supplyAsync(() -> Boolean.TRUE);
        } catch (Exception e) {
            log.error("Merchant failed to be added.");
            log.info("Cause: "+e.getMessage());
            return CompletableFuture.supplyAsync(() -> Boolean.FALSE);
        }
    }

    @Async
    @Transactional
    @Override
    public CompletableFuture<Boolean> changeMerchantStat(boolean tof, String mname) {
        try {
            if (this.merchantExist(mname).get()) {
                log.info("Attempting to edit merchant");
                merchantRepository.queryUpdateMerchantStat(tof, mname);
                log.info("Successfully changed merchant {} status.", mname);
                return CompletableFuture.supplyAsync(() -> Boolean.TRUE);
            } else {
                log.error("Failed to edit merchant status.");
                return CompletableFuture.supplyAsync(() -> Boolean.FALSE);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    @Transactional
    @Override
    public CompletableFuture<Boolean> deleteMerchant(String mname) {
        try {
            if (this.merchantExist(mname).get()) {
                log.warn("Deleting all products from merchant");
                productRepository.queryListOfProdFromMerch(mname).forEach(product -> productRepository.delete(product));
                log.info("Attempting to delete merchant");
                merchantRepository.queryDeleteByName(mname);
                log.info("Successfully deleted merchant {}.", mname);
                return CompletableFuture.supplyAsync(() -> Boolean.TRUE);
            } else {
                log.error("Failed to delete merchant.");
                return CompletableFuture.supplyAsync(() -> Boolean.FALSE);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    @Transactional(readOnly = true)
    @Override
    public CompletableFuture<List<MerchantResponse>> listOfMerchants() {
        return CompletableFuture.supplyAsync(() -> merchantRepository.queryActiveMerchant()
                .stream()
                .map(merchant -> MerchantResponse
                        .builder()
                        .merchantName(merchant.getName())
                        .merchantAddress(merchant.getLocation())
                        .build())
                .collect(Collectors.toList()));
    }

    @Async
    @Transactional(readOnly = true)
    @Override
    public CompletableFuture<List<MerchantResponse>> pageOfMerchants(int inputPage) {
        Page<Merchant> merchants = merchantRepository.queryPagedMerchantList(PageRequest.of(inputPage, 2));
        return CompletableFuture.supplyAsync(() -> merchants.stream().map(merchant -> MerchantResponse.builder()
                .merchantName(merchant.getName())
                .merchantAddress(merchant.getLocation()).build())
                .collect(Collectors.toList()));
    }

    @Async
    @Transactional(readOnly = true)
    @Override
    public CompletableFuture<MerchantResponse> merchantOfName(String merchantName) {
        try {
            if (this.merchantExist(merchantName).get()) {
                Merchant merchant = merchantRepository.queryFindMerchantByName(merchantName);
                return CompletableFuture.supplyAsync(() -> MerchantResponse.builder()
                        .merchantName(merchant.getName())
                        .merchantAddress(merchant.getLocation())
                        .build());
            } else {
                log.error("Could not find merchant with name {}", merchantName);
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    @Override
    public CompletableFuture<Merchant> merchantObjectWithName(String merchantName) {
        return CompletableFuture.supplyAsync(() -> merchantRepository.queryFindMerchantByName(merchantName));
    }
}
