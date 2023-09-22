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

    public List<Integer> qty = Arrays.asList(null,null,null,null,null);
    @Override
    public void isiQTY(Integer input1,Integer input2) {
        this.qty.set(input1-1, input2);
    }

    @Override
    public List<ModelCart> listCart() {
        RepoPage1 repoPage1 = new RepoPage1();
        return this.qty.stream()
                .filter(Objects::nonNull)
                .map(k -> ModelCart.builder()
                        .nama(repoPage1.assignedNama().get(this.qty.indexOf(k)))
                        .harga(repoPage1.assignedHarga().get(this.qty.indexOf(k)))
                        .kuantitas(k)
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void tampilCart(List<ModelCart> cart) {
        StringBuffer ws2 = new StringBuffer();
        this.header();
        System.out.println("Konfirmasi & Pembayaran");
        this.header();
        System.out.println();
        Integer totalBelanja = cart.stream()
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
        System.out.println("Total"+this.whiteSpace(15,ws2)+"   "+totalBelanja);
        System.out.println();
        System.out.println("1. Konfirmasi dan bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi");
    }
}
