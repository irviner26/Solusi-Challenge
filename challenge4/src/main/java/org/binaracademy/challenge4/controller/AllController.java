package org.binaracademy.challenge4.controller;

import lombok.extern.slf4j.Slf4j;
import org.binaracademy.challenge4.model.Detail;
import org.binaracademy.challenge4.model.Merchant;
import org.binaracademy.challenge4.model.User;
import org.binaracademy.challenge4.model.response.DetailResponse;
import org.binaracademy.challenge4.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AllController {

    private Scanner scanner = new Scanner(System.in);

    @Autowired
    private UserService userService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DetailService detailService;

    @Autowired
    private OrderService orderService;

    @PostConstruct
    public void init() {
        this.mainMenu();
    }


    public void mainMenu() {
        System.out.println("BinarFud");
        System.out.println("Silahkan pilih tujuan: ");
        System.out.println("1. User");
        System.out.println("2. Merchant");
        System.out.println("3. Product");
        System.out.println("4. Order");
        System.out.println("0. Exit");
        System.out.print("=> ");
        int userInput = scanner.nextInt();
        switch (userInput) {
            case 1:
                scanner.nextLine();
                this.userMenu();
                break;
            case 2:
                scanner.nextLine();
                this.merchantMenu();
                break;
            case 3:
                scanner.nextLine();
                this.productMenu();
                break;
            case 4:
                scanner.nextLine();
                this.orderMenu();
                break;
        }
    }

    public void userMenu() {
        System.out.println();
        System.out.println("BinarFud - User Menu");
        System.out.println("Silahkan pilih tujuan: ");
        System.out.println("1. Tambah user");
        System.out.println("2. Update user");
        System.out.println("0. Kembali");
        int userInput = scanner.nextInt();
        switch (userInput) {
            case 1:
                scanner.nextLine();
                this.addUserMenu();
                break;
            case 2:
                scanner.nextLine();
                this.updateUserMenu();
                break;
            case 0:
                scanner.nextLine();
                this.mainMenu();
                break;
        }
    }

    public void addUserMenu() {
        System.out.println();
        System.out.println("Masukkan username, password, dan e-mail yang baru");
        System.out.print("Username baru: ");
        String usernameInput = scanner.nextLine();
        System.out.print("Password baru: ");
        String passwordInput = scanner.nextLine();
        System.out.print("E-mail baru: ");
        String emailInput = scanner.nextLine();
        userService.addUser(User.builder()
                .uname(usernameInput)
                .gmail(emailInput)
                .password(passwordInput)
                .build());
        log.info("Succesfully added user to database");
        this.userMenu();
    }

    public void updateUserMenu() {
        System.out.println();
        System.out.println("Pilih peng-update-an: ");
        System.out.println("1. Update username baru");
        System.out.println("2. Update password baru");
        System.out.println("3. Hapus user");
        System.out.println("0. Kembali");
        int userInput = scanner.nextInt();
        switch (userInput) {
            case 1:
                System.out.println();
                scanner.nextLine();
                System.out.println("Silahkan masukkan username yang akan diubah usernamenya: ");
                String usernameInput1 = scanner.nextLine();
                System.out.println("Silahkan masukkan username baru yang diinginkan: ");
                String usernameInput2 = scanner.nextLine();
                userService.updateUsername(usernameInput2,usernameInput1);
                log.info("Succesfully updated user to database");
                this.updateUserMenu();
                break;
            case 2:
                System.out.println();
                scanner.nextLine();
                System.out.println("Silahkan masukkan username yang akan diubah passwordnya: ");
                String usernameInput3 = scanner.nextLine();
                System.out.println("Silahkan masukkan password baru yang diinginkan: ");
                String passwordInput1 = scanner.nextLine();
                userService.updatePassword(passwordInput1, usernameInput3);
                log.info("Succesfully updated user to database");
                this.updateUserMenu();
                break;
            case 3:
                System.out.println();
                scanner.nextLine();
                System.out.println("Silahkan masukkan username yang akan dihapus: ");
                String usernameInput = scanner.nextLine();
                userService.deleteUser(usernameInput);
                log.info("Succesfully deleted user from database");
                this.updateUserMenu();
                break;
            case 0:
                System.out.println();
                scanner.nextLine();
                this.userMenu();
                break;
        }
    }

    public void merchantMenu() {
        System.out.println();
        System.out.println("BinarFud - Merchant Menu");
        System.out.println("Silahkan pilih tujuan: ");
        System.out.println("1. Tambah Merchant");
        System.out.println("2. Update Status Merchant");
        System.out.println("3. Tampilkan merchant yang sedang buka");
        System.out.println("0. Kembali");
        int userInput = scanner.nextInt();
        switch (userInput) {
            case 1:
                scanner.nextLine();
                this.addMerchantMenu();
                break;
            case 2:
                scanner.nextLine();
                this.updateMerchantMenu();
                break;
            case 3:
                scanner.nextLine();
                this.showMerchantMenu();
                break;
            case 0:
                scanner.nextLine();
                this.mainMenu();
                break;
        }
    }

    public void addMerchantMenu() {
        System.out.println();
        System.out.println("Silahkan tambahkan nama dan status merchant yang akan dibuka: ");
        System.out.print("Nama merchant: ");
        String merchantNameInput = scanner.nextLine();
        System.out.print("Lokasi merchant: ");
        String merchantLocInput = scanner.nextLine();
        System.out.print("Status (Y/N) merchant: ");
        boolean merchantStatInput = scanner.hasNext("Y");
        merchantService.addMerchant(Merchant.builder()
                .name(merchantNameInput)
                .location(merchantLocInput)
                .status(merchantStatInput)
                .build());
        log.info("Succesfully added merchant to database");
        scanner.nextLine();
        this.merchantMenu();
    }

    public void updateMerchantMenu() {
        System.out.println();
        System.out.println();
        System.out.println("Pilih peng-update-an: ");
        System.out.println("1. Update status merchant");
        System.out.println("2. Hapus merchant");
        System.out.println("0. Kembali");
        System.out.println();
        int userInput = scanner.nextInt();
        switch (userInput) {
            case 1:
                scanner.nextLine();
                System.out.println();
                System.out.println("Masukkan nama merchant: ");
                System.out.print("Nama merchant: ");
                String merchantNameInput = scanner.nextLine();
                System.out.print("Aktifkan (Y/N): ");
                boolean merchantStatInput = scanner.hasNext("Y");
                merchantService.changeMerchantStat(merchantStatInput, merchantNameInput);
                scanner.nextLine();
                log.info("Succesfully updated merchant to database");
                this.updateMerchantMenu();
                break;
            case 2:
                System.out.println();
                scanner.nextLine();
                System.out.println("Masukkan nama merchant: ");
                System.out.print("Nama merchant: ");
                String merchantNameInput1 = scanner.nextLine();
                merchantService.deleteMerchant(merchantNameInput1);
                log.info("Succesfully deleted merchant from database");
                this.updateMerchantMenu();
                break;
            case 0:
                System.out.println();
                scanner.nextLine();
                this.merchantMenu();
                break;
        }
    }

    public void showMerchantMenu() {
        System.out.println();
        System.out.println("Berikut ini ada list merchant yang sedang aktif: ");
        System.out.println("Nama merchant \t|\t Lokasi Merchant");
        merchantService.openNowMerchant();
        this.merchantMenu();
    }

    public void productMenu() {
        System.out.println();
        System.out.println("BinarFud - Product Menu");
        System.out.println("1. Tambah produk baru");
        System.out.println("2. Update detail produk");
        System.out.println("3. Hapus produk");
        System.out.println("4. Tampilkan produk");
        System.out.println("0. Kembali");
        int userInput = scanner.nextInt();
        switch (userInput) {
            case 1:
                scanner.nextLine();
                this.addProductMenu();
                break;
            case 2:
                scanner.nextLine();
                this.updateProductMenu();
                break;
            case 3:
                scanner.nextLine();
                this.deleteProductMenu();
                break;
            case 4:
                scanner.nextLine();
                this.showProductMenu();
                break;
            case 0:
                scanner.nextLine();
                this.mainMenu();
                break;
        }
    }

    public void addProductMenu() {
        System.out.println();
        merchantService.openNowMerchant();
        System.out.println("Pilih salah satu dari merchant tersebut");
        System.out.print("=> ");
        int userInput = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nama produk yang ingin ditambahkan: ");
        String newProdInput = scanner.nextLine();
        System.out.print("Harga produk yang ingin ditambahkan: ");
        double newProdPriceInput = scanner.nextDouble();
        productService.addProduct(productService.productBuilder(newProdInput,newProdPriceInput,merchantService.listOfMerchants().get(userInput).getMerchantName()));
        System.out.println("Berhasil ditambahkan");
        this.productMenu();
    }

    public void showProductMenu() {
        System.out.println();
        merchantService.openNowMerchant();
        System.out.println("Pilih salah satu dari merchant tersebut");
        System.out.print("=> ");
        int userInput = scanner.nextInt();
        scanner.nextLine();
        String chosenMerchant = merchantService.listOfMerchants().get(userInput).getMerchantName();
        System.out.println("Berikut ini adalah produk-produk dari "+chosenMerchant);
        productService.viewListOfProduct(productService.ListOfAvailableProduct(chosenMerchant, 0));
        System.out.println();
        this.productMenu();
    }

    public void updateProductMenu() {
        System.out.println();
        merchantService.openNowMerchant();
        System.out.println("Pilih salah satu dari merchant tersebut");
        System.out.print("=> ");
        int userInput = scanner.nextInt();
        scanner.nextLine();
        String chosenMerchant = merchantService.listOfMerchants().get(userInput).getMerchantName();
        System.out.println("Berikut ini adalah produk-produk dari "+chosenMerchant);
        productService.viewListOfProduct(productService.ListOfAvailableProduct(chosenMerchant,0));
        System.out.println("Pilih salah satu produk yang akan di-update");
        System.out.print("=> ");
        int userInput1 = scanner.nextInt();
        scanner.nextLine();
        String oldProdName = productService.ListOfAvailableProduct(chosenMerchant,0).get(userInput1).getProductName();
        double oldProdPrice = productService.ListOfAvailableProduct(chosenMerchant,0).get(userInput1).getProductPrice();
        System.out.print("Input nama baru produk tersebut ('-' jika tidak ada): ");
        String newProdName = scanner.nextLine();
        if (newProdName.equalsIgnoreCase("-")) {
            newProdName = oldProdName;
        }
        System.out.print("Input nama harga baru produk tersebut ('0' jika tidak ada): ");
        double newProdPrice = scanner.nextDouble();
        scanner.nextLine();
        if (newProdPrice == 0) {
            newProdPrice = oldProdPrice;
        }
        productService.updateProductName(chosenMerchant,oldProdName,newProdName);
        productService.updateProductPrice(chosenMerchant,newProdName,newProdPrice);
        System.out.println("Sukses Meng-update produk");
        this.productMenu();
    }

    public void deleteProductMenu() {
        System.out.println();
        merchantService.openNowMerchant();
        System.out.println("Pilih salah satu dari merchant tersebut");
        System.out.print("=> ");
        int userInput = scanner.nextInt();
        scanner.nextLine();
        String chosenMerchant = merchantService.listOfMerchants().get(userInput).getMerchantName();
        System.out.println("Berikut ini adalah produk-produk dari "+chosenMerchant);
        productService.viewListOfProduct(productService.ListOfAvailableProduct(chosenMerchant,0));
        System.out.println("Pilih salah satu produk yang akan di-delete");
        System.out.print("=> ");
        int userInput1 = scanner.nextInt();
        scanner.nextLine();
        String prodName = productService.ListOfAvailableProduct(chosenMerchant,0).get(userInput1).getProductName();
        productService.removeProductOf(prodName, chosenMerchant);
        System.out.println("Produk "+prodName+" sukses dihapus");
        this.productMenu();
    }

    String unameFromUser = null;
    int merchFromUser = -1;
    int prodFromUser = -1;
    String chosenMerchant = null;
    int quantFromUser = 0;
    List<Detail> detailOfUserOrder = new ArrayList<>();
    public void orderMenu() {
        System.out.println();
        System.out.println("Selamat Datang di Binar Food");
        System.out.println("Silahkan Masukkan Username");
        System.out.print("Username: ");
        this.unameFromUser = scanner.nextLine();
        try {
            userService.getUserByName(unameFromUser);
        } catch (Exception e) {
            System.out.println("Silahkan Ulang Pengisian");
            this.orderMenu();
        }
        System.out.print("Destinasi: ");
        String destinasi = scanner.nextLine();
        orderService.addOrderToDB(orderService.orderBuilder(unameFromUser,new Date(),destinasi,true));
        System.out.println();
        System.out.println("Silahkan pilih merchant: ");
        System.out.println("Berikut ini ada list merchant yang sedang aktif: ");
        System.out.println("Nama merchant \t|\t Lokasi Merchant");
        merchantService.openNowMerchant();
        System.out.println();
        System.out.println("Pilih merchant yang diinginkan: ");
        System.out.print("=> ");
        this.merchFromUser = scanner.nextInt();
        scanner.nextLine();
        this.chosenMerchant = merchantService.listOfMerchants().get(merchFromUser).getMerchantName();
        try {
            System.out.println("Berikut ini adalah produk-produk dari "+chosenMerchant);
            productService.viewListOfProduct(productService.ListOfAvailableProduct(chosenMerchant,0));
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan silahkan ulangi");
            this.merchantMenu();
        }
        this.addOrderMenu();
    }

    public void addOrderMenu() {
        System.out.println();
        System.out.println("Berikut ini adalah produk-produk dari "+chosenMerchant);
        productService.viewListOfProduct(productService.ListOfAvailableProduct(chosenMerchant,0));
        System.out.println("Pilih produk yang mana?");
        System.out.println("(-1 untuk kembali)");
        System.out.print("=>");
        this.prodFromUser = scanner.nextInt();
        scanner.nextLine();
        if (prodFromUser != -1) {
            System.out.print("Kuantitasnya: ");
            quantFromUser = scanner.nextInt();
            scanner.nextLine();
            double fp = detailService.finalPrice(productService.ListOfAvailableProduct(chosenMerchant,0).get(prodFromUser), quantFromUser);
            this.detailOfUserOrder.add(detailService.buildOrderDetail(quantFromUser
                    ,fp
                    ,unameFromUser
                    ,productService.ListOfAvailableProduct(chosenMerchant,0).get(prodFromUser).getProductName()
                    ,chosenMerchant));
            System.out.println("Berikut ini ordernya: ");
            Double total = detailOfUserOrder.stream()
                    .map(val -> {
                        System.out.println(val.getProduct()+"\t|\t"+val.getQuantity()+"\t|\t"+val.getTotal());
                        return val;
                    })
                    .reduce(0.0, (aDouble, detailResponse) -> aDouble + detailResponse.getTotal(), Double::sum);
            System.out.println("Total: "+total);
            System.out.println();
            System.out.println("Konfirmasi? ");
            System.out.println("1. Ya");
            System.out.println("2. Batal / Mau Pilih lagi");
            int confirmChoice = scanner.nextInt();
            scanner.nextLine();
            if (confirmChoice != 2) {
                detailOfUserOrder
                        .stream()
                        .map(val -> {
                            detailService.addDetailsToDB(val);
                            return val;
                        })
                        .collect(Collectors.toList());
                this.detailOfUserOrder = new ArrayList<>();
            } else {
                this.addOrderMenu();
            }
        }
        else {
            this.orderMenu();
        }
    }

}
