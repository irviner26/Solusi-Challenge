package com.binaracademy.latihanchap4.model.Controller;

import com.binaracademy.latihanchap4.model.Ojek;
import com.binaracademy.latihanchap4.service.OjekService;
import com.binaracademy.latihanchap4.service.PesanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.Scanner;

@Component
public class MenuController {
    @Autowired
    private PesanService pesanService;

    @Autowired
    private OjekService ojekService;

    private Scanner scanner = new Scanner(System.in);

    @PostConstruct
    public void init() {
        this.menu1();
    }

    public void menu1() {
        System.out.println("1. Ojek");
        System.out.println("2. Pesan");
        System.out.print("=> ");
        int input = scanner.nextInt();
        if (input == 1) {
            scanner.nextLine();
            this.menu2();
        }
        if (input == 2) {
            scanner.nextLine();
            this.menu3();
        }
    }

    public void menu2() {
        System.out.print("Nama: ");
        String inputNama = scanner.nextLine();
        boolean status = true;
        ojekService.addOjekToDB(Ojek.builder().nama(inputNama).availability(status).build());
        this.menu1();
    }

    public void menu3() {
        System.out.println("Alamat: ");
        String alamat = scanner.nextLine();
        System.out.println("Harga: ");
        Integer harga = scanner.nextInt();
        try {
            pesanService.doPesan(alamat,harga);
        } catch (Exception e) {
            System.out.println("Ulang lagi");
            scanner.nextLine();
            this.menu3();
        }
        scanner.nextLine();
        this.menu1();
    }

}
