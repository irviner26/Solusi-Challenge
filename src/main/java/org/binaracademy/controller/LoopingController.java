package org.binaracademy.controller;

import org.binaracademy.service.KonfirmasiService;
import org.binaracademy.service.KonfirmasiServiceImpl;
import org.binaracademy.service.PesanService;
import org.binaracademy.service.PesanServiceImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LoopingController {
    MenuController menuController = new MenuController();
    PesanService pesanService = new PesanServiceImpl();
    KonfirmasiService konfirmasiService = new KonfirmasiServiceImpl();

    private int pilihanMenu;
    public void loopingMenu() {
        int pilihQTY;
        int konfirmasi;
        List<Integer> updateQTY = konfirmasiService.updateQTY();
        List<Integer> updateHarga = konfirmasiService.updateHarga();

        Scanner scanner = new Scanner(System.in);
        String pilihanLanjut = "Y";
        do {
            boolean pengulanganMenu1;
            boolean pengulanganMenu2;
            boolean pengulanganMenu3;
            List<String> tampilanMakanan = menuController.getTampilanMakanan();
            List<Integer> tampilanHarga = menuController.getTampilanHargaMakanan();
            do {
                menuController.tampilanMenu(tampilanMakanan, tampilanHarga);
                try {
                    pilihanMenu = scanner.nextInt();
                    pengulanganMenu1 = false;
                } catch (InputMismatchException ime) {
                    do {
                        scanner.nextLine();
                        System.out.println("================================");
                        System.out.println("Mohon masukkan input yang benar!");
                        System.out.println("================================");
                        System.out.println("(Y) untuk lanjut");
                        System.out.println("(n) untuk keluar");
                        try {
                            pilihanLanjut = scanner.next();
                        } catch (InputMismatchException ime2) {
                            System.out.println("=>");
                        }
                    } while (!pilihanLanjut.equalsIgnoreCase("Y")
                            && !pilihanLanjut.equalsIgnoreCase("n"));
                    if (pilihanLanjut.equalsIgnoreCase("n")) {
                        break;
                    }
                    pengulanganMenu1 = true;
                }
                if (pilihanMenu > pesanService.ambilListMakanan().size() && pilihanMenu != 99) {
                    System.out.println("Menu tidak tersedia");
                    pengulanganMenu1 = true;
                }
            } while (pengulanganMenu1);
            pilihQTY = 0;
            if (pilihanMenu <= pesanService.ambilListMakanan().size() && pilihanMenu > 0) {
                do {
                    menuController.tampilanPilihKuantitas(pilihanMenu);
                    try {
                        pilihQTY = scanner.nextInt();
                        pengulanganMenu2 = false;
                    } catch (InputMismatchException ime) {
                        do {
                            scanner.nextLine();
                            System.out.println("================================");
                            System.out.println("Mohon masukkan input yang benar!");
                            System.out.println("================================");
                            System.out.println("(Y) untuk lanjut");
                            System.out.println("(n) untuk keluar");
                            pilihanLanjut = scanner.next();
                        } while (!pilihanLanjut.equalsIgnoreCase("Y")
                                && !pilihanLanjut.equalsIgnoreCase("n"));
                        if (pilihanLanjut.equalsIgnoreCase("n")) {
                            break;
                        }
                        pengulanganMenu2 = true;
                    }
                    if (pilihQTY >= Integer.MAX_VALUE/pesanService.ambilListHargaMakanan().get(pilihanMenu)) {
                        System.out.println("Jumlah pesanan melebihi batas!");
                        System.out.println("Silahkan kurangi jumlahnya");
                        pengulanganMenu2 = true;
                    }
                } while (pengulanganMenu2);
                updateQTY = konfirmasiService.updateKuantitas(pilihQTY, pilihanMenu, updateQTY);
                updateHarga = konfirmasiService.updateHargaKuantitas(pilihQTY, pilihanMenu, updateHarga);
            }
            konfirmasi = 2;
            if (pilihanMenu == 99) {
                do {
                    menuController.tampilanKonfirmasi(updateQTY, updateHarga);
                    try {
                        konfirmasi = scanner.nextInt();
                        pengulanganMenu3 = false;
                    } catch (InputMismatchException ime) {
                        do {
                            scanner.nextLine();
                            System.out.println("================================");
                            System.out.println("Mohon masukkan input yang benar!");
                            System.out.println("================================");
                            System.out.println("(Y) untuk lanjut");
                            System.out.println("(n) untuk keluar");
                            pilihanLanjut = scanner.next();
                        } while (!pilihanLanjut.equalsIgnoreCase("Y")
                                && !pilihanLanjut.equalsIgnoreCase("n"));
                        if (pilihanLanjut.equalsIgnoreCase("n")) {
                            break;
                        }
                        pengulanganMenu3 = true;
                    }
                } while (pengulanganMenu3);
            }
        } while ((konfirmasi != 0 && konfirmasi !=1) && pilihanMenu != 0);
        if (konfirmasi == 1) {
            scanner.nextLine();
            System.out.println("Masukkan lokasi simpan struk: ");
            String lokasiFile = scanner.nextLine();
            menuController.strukPembayaran(lokasiFile, konfirmasiService.ambilListMenu(), updateQTY, updateHarga);
        }
    }
}
