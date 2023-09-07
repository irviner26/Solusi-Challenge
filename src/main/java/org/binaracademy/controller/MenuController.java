package org.binaracademy.controller;

import org.binaracademy.service.PesanService;
import org.binaracademy.service.PesanServiceImpl;

import java.util.List;

public class MenuController {
    PesanService pesanService = new PesanServiceImpl();
    public void tampilanMenu() {
        List<String> tampilanMakanan = pesanService.ambilListMakanan();
        List<Integer> tampilanHargaMakanan = pesanService.ambilListHargaMakanan();
        System.out.println("===========================");
        System.out.println("Selamat datang di BinarFuud");
        System.out.println("===========================");
        System.out.println();
        System.out.println("Silahkan pilih menu: ");
        for (int indeks = 0; indeks <= tampilanMakanan.size() - 1; indeks++) {
            System.out.print(indeks+1+". ");
            System.out.print(tampilanMakanan.get(indeks));
            for (int j = 0; j <= 15 - tampilanMakanan.get(indeks).length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|     Rp ");
            if (tampilanHargaMakanan.get(indeks) >= 10000) {
                System.out.print(tampilanHargaMakanan.get(indeks));
            }
            else {
                System.out.print(" "+tampilanHargaMakanan.get(indeks));
            }
            System.out.println();
        }
        System.out.println("99. Pesan & Bayar");
        System.out.println("0. Exit");
    }

    public void tampilanPilihKuantitas() {

    }

}