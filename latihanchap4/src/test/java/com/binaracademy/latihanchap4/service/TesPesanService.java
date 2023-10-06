package com.binaracademy.latihanchap4.service;


import com.binaracademy.latihanchap4.model.Ojek;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TesPesanService {

    @Test
    public void tesOjekSer() {
        OjekService ojekService = new OjekServiceImpl();
        ojekService.addOjekToDB(Ojek.builder()
                .nama("Jarip")
                .availability(false)
                .build());
        ojekService.addOjekToDB(Ojek.builder()
                .nama("Geri")
                .availability(false)
                .build());
        ojekService.addOjekToDB(Ojek.builder()
                .nama("Beni")
                .availability(true)
                .build());
        ojekService.addOjekToDB(Ojek.builder()
                .nama("Botak")
                .availability(false)
                .build());
        ojekService.addOjekToDB(Ojek.builder()
                .nama("Roni")
                .availability(true)
                .build());
        System.out.println(ojekService.yangAvail());
    }

}
