package org.binaracademy.challenge4.service;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.repository.MerchantRepository;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.binaracademy.challenge4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ScheduleServiceImpl {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MerchantRepository merchantRepository;

    @Scheduled(cron = "0 0 18 * * *")
    public void databaseStatus() {
        System.out.println("Numbers of various data in the database.");
        System.out.println();
        System.out.println("1. User data: ");
        System.out.println("No. of users: "
                +userRepository.findAll().size());
        System.out.println("No. of regular user: "
                +userRepository.userRegular().size());
        System.out.println("No. of merchant user: "
                +userRepository.userMerchant().size());
        System.out.println();
        System.out.println("2. Merchant and product data: ");
        System.out.println("No. of merchant: "+merchantRepository.findAll().size());
        System.out.println("No. of product: "+productRepository.findAll().size());
    }
}
