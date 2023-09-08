package org.binaracademy.controller;

import lombok.Getter;
import org.binaracademy.service.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
public class MenuController {
    PesanService pesanService = new PesanServiceImpl();
    private List<String> tampilanMakanan = pesanService.ambilListMakanan();
    private List<Integer> tampilanHargaMakanan = pesanService.ambilListHargaMakanan();

    public void tampilanMenu(List<String> tampilMakanan, List<Integer> tampilHargaMakanan) {
        System.out.println("===========================");
        System.out.println("Selamat datang di BinarFuud");
        System.out.println("===========================");
        System.out.println();
        System.out.println("Silahkan pilih menu: ");
        for (int indeks = 0; indeks <= tampilMakanan.size() - 1; indeks++) {
            System.out.print(indeks+1+". ");
            System.out.print(tampilMakanan.get(indeks));
            for (int j = 0; j <= 15 - tampilMakanan.get(indeks).length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|     Rp ");
            if (tampilHargaMakanan.get(indeks) >= 10000) {
                System.out.print(tampilHargaMakanan.get(indeks));
            }
            else {
                System.out.print(" "+tampilHargaMakanan.get(indeks));
            }
            System.out.println();
        }
        System.out.println("99. Pesan & Bayar");
        System.out.println("0. Exit");
    }

    PilihanService pilihanService = new PilihanServiceImpl();
    public void tampilanPilihKuantitas(int pilihanUser) {
        System.out.println("===========================");
        System.out.println("Berapa pesanan anda?");
        System.out.println("===========================");
        System.out.print(pilihanService.pilihanMakanan(pilihanUser-1));
        for (int i = 0; i <= 15 - pilihanService.pilihanMakanan(pilihanUser-1).length(); i++) {
            System.out.print(" ");
        }
        System.out.print("|     Rp ");
        if (pilihanService.hargaPilihanMakanan(pilihanUser-1) >= 10000) {
            System.out.print(pilihanService.hargaPilihanMakanan(pilihanUser-1));
        }
        else {
            System.out.print(" "+pilihanService.hargaPilihanMakanan(pilihanUser-1));
        }
        System.out.println();
        System.out.print("qty => ");
        System.out.println("(input 0 untuk kembali)");
    }

    KonfirmasiService konfirmasiService = new KonfirmasiServiceImpl();
    public void tampilanKonfirmasi(List<Integer> listQuant, List<Integer> listHarga) {
        Integer quant = 0;
        Integer totalH = 0;
        System.out.println("===========================");
        System.out.println("Konfirmasi dan Bayar");
        System.out.println("===========================");
        for (int i = 0; i <= listQuant.size() - 1; i++) {
            //Gunakan if agar yang di print out hanya menu yang dipesan saja
            if (listHarga.get(i) != 0) {
                System.out.print(konfirmasiService.ambilListMenu().get(i));
                for (int j = 0; j <= 15 - konfirmasiService.ambilListMenu().get(i).length(); j++) {
                    System.out.print(" ");
                }
                System.out.print(listQuant.get(i));
                System.out.print("      Rp ");
                if (listHarga.get(i) >= 10000) {
                    System.out.print(listHarga.get(i));
                }
                else {
                    System.out.print(" "+listHarga.get(i));
                }
                System.out.println();
                // Hitung penambahan kuantitasnya
                quant += listQuant.get(i);
                totalH += listHarga.get(i);
            }
        }
        System.out.println("---------------------------------");
        System.out.print("Total");
        for (int i = 0; i <= 10; i++) {
            System.out.print(" ");
        }
        System.out.print(quant);
        System.out.print("      Rp ");
        if (totalH >= 10000) {
            System.out.print(totalH);
        }
        else {
            System.out.print(" "+totalH);
        }
        System.out.println();
        System.out.println("1. Konfirmasi dan bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi");
    }

    void strukPembayaran(String txtFile,
                         List<String> menu,
                         List<Integer> QTY,
                         List<Integer> Total) {
        Integer finalQTY = 0;
        Integer finalTotal = 0;
        // Gunakan class Date dan DateFormat untuk nama file yang unik
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmmss");
        String tanggalPembelian = sdf.format(new Date());
        try {
            File file = new File(txtFile+"\\Struk"+tanggalPembelian+".txt");
            if (file.createNewFile()) {
                System.out.println("File sudah dibuat!");
            }
            FileWriter writer = new FileWriter(file);
            BufferedWriter bwr = new BufferedWriter(writer);
            bwr.write("======================================");
            bwr.newLine();
            bwr.write("BinarFuud");
            bwr.newLine();
            bwr.write("======================================");
            bwr.newLine();
            for (int i = 0; i <= Total.size() - 1; i++) {
                if (Total.get(i) != 0) {
                    bwr.write(menu.get(i));
                    for (int j = 0; j<= 20 - menu.get(i).length(); j++) {
                        bwr.write(" ");
                    }
                    bwr.write(String.valueOf(QTY.get(i)));
                    bwr.write("      Rp ");
                    if (Total.get(i) >= 10000) {
                        bwr.write(String.valueOf(Total.get(i)));
                    }
                    else {
                        bwr.write(" "+Total.get(i));
                    }
                    bwr.newLine();
                    finalQTY+=QTY.get(i);
                    finalTotal+=Total.get(i);
                }
            }
            bwr.write("--------------------------------------");
            bwr.newLine();
            bwr.write("Total");
            for (int i = 0; i <= 15; i++) {
                bwr.write(" ");
            }
            bwr.write(String.valueOf(finalQTY));
            bwr.write("      Rp ");
            if (finalTotal >= 10000) {
                bwr.write(String.valueOf(finalTotal));
            }
            else {
                bwr.write(" "+finalTotal);
            }
            bwr.newLine();
            bwr.newLine();
            bwr.write("Metode pembayaran: BinarPay");
            bwr.newLine();
            bwr.write("=======================================");
            bwr.newLine();
            bwr.write("  Terima kasih sudah berbelanja");
            bwr.newLine();
            bwr.write("=======================================");
            bwr.flush();
            bwr.close();
            System.out.println("File sudah diisi");
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }

    }

}