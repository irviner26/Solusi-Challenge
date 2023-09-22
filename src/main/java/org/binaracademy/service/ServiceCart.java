package org.binaracademy.service;

import org.binaracademy.model.ModelCart;
import org.binaracademy.repository.RepoPage1;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class ServiceCart extends ServiceAdditional implements ServiceCartI {

    public ServiceCart() {
        super();
    }
    // Inisialisasi list kuantitas dengan nilai null
    public List<Integer> qty = Arrays.asList(null,null,null,null,null);

    // Method untuk isi list kuantitas di atas
    @Override
    public void isiQTY(Integer input1,Integer input2) {
        this.qty.set(input1-1, input2);
    }

    // Membuat list dengan objek model dari cart
    @Override
    public List<ModelCart> listCart() {
        RepoPage1 repoPage1 = new RepoPage1();
        return repoPage1.assignedNama().stream()
                .filter(Objects::nonNull)
                .map(k -> ModelCart.builder()
                        .nama(k)
                        .harga(repoPage1.assignedHarga().get(repoPage1.assignedNama().indexOf(k)))
                        .kuantitas(this.qty.get(repoPage1.assignedNama().indexOf(k)))
                        .build())
                .collect(Collectors.toList());
    }

    // Menampilkan cart pembeli
    @Override
    public void tampilCart(List<ModelCart> cart) {
        StringBuffer ws2 = new StringBuffer();
        this.header();
        System.out.println("Konfirmasi & Pembayaran");
        this.header();
        System.out.println();
        Integer totalBelanja = cart.stream()
                .filter(cls -> cls.getKuantitas() != null)
                .map(val -> {
                    StringBuffer ws = new StringBuffer();
                    System.out.println(val.getNama()
                            +this.whiteSpace(20-val.getNama().length(), ws)
                            +val.getKuantitas()
                            +"   "
                            +val.getHarga()*val.getKuantitas());
                    return val;
                })
                .reduce(0, (result, order) -> result + (order.getHarga() * order.getKuantitas()), Integer::sum);
        System.out.println("Total"+this.whiteSpace(16,ws2)+"   "+totalBelanja);
        System.out.println();
        System.out.println("1. Konfirmasi dan bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi");
    }
}
