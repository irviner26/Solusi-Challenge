package org.binaracademy.repository;

import org.binaracademy.model.ModelPage2;

import java.util.Optional;


public class RepoPage2 {
    // berikut ini merupakan repository untuk page ke-2

    //Method berikut ini mengambil data dari repository page 1 yang cocok atau sesuai
    //dengan user input dan mengisinya pada model
    RepoPage1 repoPage1;
    ModelPage2 modelPage2;
    public void setPesanan(Integer indeks) {
        repoPage1 = new RepoPage1();
        this.modelPage2 = ModelPage2.builder()
                .pilihan(repoPage1.assignedNama().get(indeks - 1))
                .hargaPilihan(repoPage1.assignedHarga().get(indeks - 1))
                .build();
    }
    public String assignedNamaPesanan(Integer indeks) {
        this.setPesanan(indeks);
        return modelPage2.getPilihan();
    }

    public Integer assignedHargaPesanan(Integer indeks) {
        this.setPesanan(indeks);
        return modelPage2.getHargaPilihan();
    }
}
