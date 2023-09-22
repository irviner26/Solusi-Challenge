package org.binaracademy.service;

import org.binaracademy.repository.RepoPage1;

public class ServicePage1 extends ServiceAdditional implements ServicePage1I{

    public ServicePage1() {
        super();
    }

    @Override
    // Method penampilan nama dan harga makanan/minuman menggunakan
    public void tampilNH() {
        RepoPage1 repoPage1 = new RepoPage1();
        this.header();
        System.out.println("Selamat datang di BinarFuud");
        this.header();
        System.out.println("Silahkan pilih menu berikut:");
        repoPage1.assignedNama()
                .forEach(val -> {
                    StringBuffer whitespace = new StringBuffer();
                    System.out.println(
                            repoPage1.assignedNama().indexOf(val)+1+ ". "
                                    +val
                                    +this.whiteSpace(20 - val.length(), whitespace)
                                    +repoPage1.assignedHarga().get(repoPage1.assignedNama().indexOf(val)));
                });
        System.out.println("99. Pesan & Bayar");
        System.out.println("0. Exit");
    }
}
