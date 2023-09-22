package org.binaracademy;

import org.binaracademy.controller.ControlUI;
import org.binaracademy.model.ModelUserInput;
import org.binaracademy.repository.RepoCart;
import org.binaracademy.service.*;

import java.util.Scanner;

public class BinarFud {
    public static void main(String[] args) {
        ServicePage1I servicePage1 = new ServicePage1();
        ServicePage2I servicePage2 = new ServicePage2();
        ServiceCartI serviceCart = new ServiceCart();
        RepoCart repoCart = new RepoCart();
        // Input berikut ini merupakan input pilihan makanan/minuman dari user
        ModelUserInput modelUI1 = new ModelUserInput();
        ControlUI cuip1 = new ControlUI();
        ModelUserInput modelUI2 = new ModelUserInput();
        ControlUI cuip2 = new ControlUI();
        ModelUserInput modelUI3 = new ModelUserInput();
        ControlUI cuip3 = new ControlUI();
        Integer konfirmasi;
        do{
            do {
                servicePage1.tampilNH();
                cuip1.handleInput(modelUI1);
            } while (modelUI1.isCycle());
            if (modelUI1.getInput() > 0 && modelUI1.getInput() <= 5) {
                do {
                    servicePage2.tampilPes(modelUI1.getInput());
                    cuip2.handleInput(modelUI2);
                    serviceCart.isiQTY(modelUI1.getInput(), modelUI2.getInput());
                    serviceCart.listCart();
                } while (modelUI2.isCycle());
            }
            konfirmasi = 2;
            if (modelUI1.getInput() == 99) {
                do {
                    serviceCart.tampilCart(serviceCart.listCart());
                    cuip3.handleInput(modelUI3);
                    konfirmasi = modelUI3.getInput();
                } while (modelUI3.isCycle());
            }
        } while (modelUI1.getInput() != 0 && konfirmasi != 1);
        if (konfirmasi == 1) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Masukkan lokasi simpan struk: ");
            String lokasiFile = scanner.nextLine();
            repoCart.struk(serviceCart.listCart(), lokasiFile, (ServiceCart) serviceCart);
        }
    }
}
