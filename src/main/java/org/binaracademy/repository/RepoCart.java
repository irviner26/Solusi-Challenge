package org.binaracademy.repository;

import org.binaracademy.model.ModelCart;
import org.binaracademy.service.ServiceCart;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RepoCart {
    public void struk(List<ModelCart> modelCarts, String txtFile) {
        ServiceCart serviceCart = new ServiceCart();
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
            serviceCart.tampilCart(modelCarts);
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
