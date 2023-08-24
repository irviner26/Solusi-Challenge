package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SolusiChallenge1 {

    // Irvine Ringgo (irvineringgo90@gmail.com)
    // Github: https://github.com/irviner26
    public static void main(String[] args) {

        // Sebelum memulai pembuatan algoritma untuk aplikasi BinarFud,
        // definisikan variabel-variable yang akan digunakan:
        // list untuk menu makanan dan list untuk harganya (dibuat dengan array dan di-assign -
        // supaya indeksnya sama)

        String[] menuMakanan = {"Nasi Goreng", "Mie Goreng", "Nasi + Ayam", "Es Teh Manis", "Es Jeruk"};
        int[] hargaMakanan = {15000, 13000, 18000, 3000, 5000};

        // Mulai definisikan variabel-variabel yang digunakan untuk algoritma nantinya.
        // Array updateQTY digunakan untuk menunjukkan / menyimpan kuantitas menu yang nantinya
        // akan dipilih oleh user.
        // Array updateTotal sama dengan updateQTY hanya saja untuk harganya.
        int[] updateQTY = {0, 0, 0, 0, 0};
        int[] updateTotal = {0, 0, 0, 0, 0};

        // Berikut ini merupakan variabel yang akan digunakan sebagai user input (berupa integer)
        int pilihanMenu;
        int pilihQty;
        int konfirmasi;

        // Gunakan class Scanner untuk mengambil input dari pengguna
        Scanner scanner = new Scanner(System.in);

        /* Berikut ini merupakan algoritma utama yang akan digunakan pada aplikasi BinarFud.
        * Ada tiga menu (tampilan) pada aplikasinya, yaitu tampilan home yang menunjukkan pilihan makanan
        * tampilan pemilihan kuantitas dan tampilan konfirmasi pembayaran */

        /* Semua menu tersebut memiliki kesamaan, yaitu memiliki pilihan exit atau tutup aplikasi.
        * Pada algoritma berikut ini, kita akan menggunakan loop do-while dengan kondisi loop dilakukan selama
        * tiga kondisi valid atau true. Kondisi tersebut adalah user tidak memilih exit pada menu utama DAN
        * user tidak memilih exit pada menu konfirmasi DAN user tidak memilih konfirmasi dan bayar pada menu konfirmasi
        * (kondisi yang terakhir akan mengexit loopnya dan akan dilanjutkan lagi ke struk pembayaran). Jika salah
        * satu dari kondisi tersebut tidak terpenuhi maka loopnya akan break. */
        do {
            // Note: semua penjelasan method ada di bagian method tersebut.
            // Aplikasi akan menapilkan menu utama.
            printMenu(menuMakanan, hargaMakanan);
            // Aplikasi meminta input dari user (input angka 1-5,99, atau 0)
            pilihanMenu = scanner.nextInt();
            // JIKA input dari user adalah 1-5 maka block ini akan dijalankan
            if (pilihanMenu <= menuMakanan.length && pilihanMenu > 0) {
                // Aplikasi menampilkan menu konfirmasi jumlah makanan yang akan dipesan
                printKonfirm(menuMakanan[pilihanMenu-1], hargaMakanan[pilihanMenu-1]);
                // input user untuk kuantitas.
                pilihQty = scanner.nextInt();
                // kuantitas dari makanan yang dipilih ditambahkan pada kuantitas menu yang sesuai
                updateQTY[pilihanMenu-1] += pilihQty;
                // total harganya juga ditambahkan sesuai dengan kuantitasnya
                updateTotal[pilihanMenu-1] += pilihQty * hargaMakanan[pilihanMenu-1];
                // Block ini akan dijalankan terus menerus jika user tidak menginput 99 ATAU 0.
            }
            // Berikut ini block untuk menu konfirmasi
            // pilihan konfirmasi didefaultkan menjadi 3 karena user akan diberi pilihan 0 - 2
            // untuk konfirmasi terakhir. Hal ini juga bertujuan untuk menjaga loopnya tidak exit
            // ketika user memilih makanan
            konfirmasi = 3;
            // JIKA user memilih 99 maka akan muncul menu konfirmasi (block kode berikut).
            if (pilihanMenu == 99) {
                // Aplikasi menampilkan menu konfirmasi
                printKonfirmasi2(menuMakanan, updateQTY, updateTotal);
                // User input untuk menu konfirmasi.
                konfirmasi = scanner.nextInt();
                // bergantung konfirmasinya, loop akan exit atau lanjut
                // untuk user input 2 maka akan ter-loop kembali ke menu utama
                // untuk user input 0 ATAU 1 maka loop akan exit
            }
        } while ((konfirmasi != 0 && konfirmasi !=1) && pilihanMenu != 0 );

        // Setelah user keluar dari loop (bayar atau exit), kode block ini akan dijalankan
        // JIKA user memilih konfirmasi dan bayar (user input 1)
        if (konfirmasi == 1) {
            // User memilih lokasi file struk akan disimpan
            scanner.nextLine();
            System.out.println("Masukkan lokasi simpan struk: ");
            String lokasiFile = scanner.nextLine();
            // File struk pembayaran akan disimpan
            strukPembayaran(lokasiFile, menuMakanan, updateQTY, updateTotal);
        }

    }

    // Berikut ini metode untuk tampilan menu utama pada aplikasi binarfud
    static void printMenu(String[] menu, int[] harga) {
        // Print out headernya
        System.out.println("===========================");
        System.out.println("Selamat datang di BinarFuud");
        System.out.println("===========================");
        System.out.println();
        // List menu dan harga dapat diautomasikan
        System.out.println("Silahkan pilih menu: ");
        for (int indeks = 0; indeks <= menu.length - 1; indeks++) {
            // Agar rapih kita dapat atur dengan mengunakan print bukan println
            System.out.print(indeks+1+". ");
            System.out.print(menu[indeks]);
            for (int j = 0; j <= 15 - menu[indeks].length(); j++) {
                System.out.print(" ");
            }
            System.out.print("|     Rp ");
            if (harga[indeks] >= 10000) {
                System.out.print(harga[indeks]);
            }
            else {
                System.out.print(" "+harga[indeks]);
            }
            System.out.println();
        }
        System.out.println("99. Pesan & Bayar");
        System.out.println("0. Exit");
    }

    // Berikut ini metode untuk menu kuantitas
    static void printKonfirm(String menu, int harga) {
        System.out.println("===========================");
        System.out.println("Berapa pesanan anda?");
        System.out.println("===========================");
        //Tampilan menu yang dipilih juga dibuat dengan print agar rapih
        System.out.print(menu);
        for (int i = 0; i <= 15 - menu.length(); i++) {
            System.out.print(" ");
        }
        System.out.print("|     Rp ");
        if (harga >= 10000) {
            System.out.print(harga);
        }
        else {
            System.out.print(" "+harga);
        }
        System.out.println();
        System.out.print("qty => ");
        System.out.println("(input 0 untuk kembali)");
    }

    // Berikut ini untuk menu konfirmasi terakhir untuk pembayaran
    static void printKonfirmasi2(String[] menu, int[] quantitas, int[] harga) {
        // Definisikan variabel berikut dengan nilai default 0.
        // quant untuk menyatakan total kuantitas dari keseluruhan makanan yang kita pesan
        // totalH untuk total harganya
        int quant = 0;
        int totalH = 0;
        System.out.println("===========================");
        System.out.println("Konfirmasi dan Bayar");
        System.out.println("===========================");
        for (int i = 0; i <= harga.length - 1; i++) {
            //Gunakan if agar yang di print out hanya menu yang dipesan saja
            if (harga[i] != 0) {
                System.out.print(menu[i]);
                for (int j = 0; j <= 15 - menu[i].length(); j++) {
                    System.out.print(" ");
                }
                System.out.print(quantitas[i]);
                System.out.print("      Rp ");
                if (harga[i] >= 10000) {
                    System.out.print(harga[i]);
                }
                else {
                    System.out.print(" "+harga[i]);
                }
                System.out.println();
                // Hitung penambahan kuantitasnya
                quant+=quantitas[i];
                totalH+=harga[i];
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

    static void strukPembayaran(String txtFile, String[] menu, int[] QTY, int[] Total) {
        int finalQTY = 0;
        int finalTotal = 0;
        try {
            File file = new File(txtFile);
            if (file.createNewFile()) {
                System.out.println("File sudah dibuat!");
            }
            FileWriter writer = new FileWriter(file);
            BufferedWriter bwr = new BufferedWriter(writer);
            bwr.write("================================");
            bwr.newLine();
            bwr.write("BinarFuud");
            bwr.newLine();
            bwr.write("================================");
            bwr.newLine();
            for (int i = 0; i <= Total.length - 1; i++) {
                if (Total[i] != 0) {
                    //bwr.write(menu[i]+"   "+QTY[i]+"   Rp "+Total[i]);
                    bwr.write(menu[i]);
                    for (int j = 0; j<= 20 - menu[i].length(); j++) {
                        bwr.write(" ");
                    }
                    bwr.write(String.valueOf(QTY[i]));
                    bwr.write("      Rp ");
                    if (Total[i] >= 10000) {
                        bwr.write(String.valueOf(Total[i]));
                    }
                    else {
                        bwr.write(" "+Total[i]);
                    }
                    bwr.newLine();
                    finalQTY+=QTY[i];
                    finalTotal+=Total[i];
                }
            }
            bwr.write("---------------------------------");
            bwr.newLine();
            // bwr.write("Total"+"   "+finalQTY+"   Rp "+finalTotal);
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
            bwr.write("=================================");
            bwr.newLine();
            bwr.write("  Terima kasih sudah berbelanja");
            bwr.newLine();
            bwr.write("=================================");
            bwr.flush();
            bwr.close();
            System.out.println("File sudah diisi");
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }

    }
}
