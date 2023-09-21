package org.binaracademy.repository;

import org.binaracademy.data.NamaHarga;
import org.binaracademy.model.ModelPage1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RepoPage1 {
    // Class pada repository ini berisikan repository yang akan digunakan
    // pada page pertama (meng-assign model dengan data nama dan harga)

    // Panggil class model untuk page 1 dan data nama & harga
    // sebagai object dalam class repository untuk page 1
    NamaHarga namaHarga;
    ModelPage1 modelPage1;

    // Method berikut berfungsi untuk men-set nama dan harga dari
    // POJO class di model untuk page 1.
    public void setNamaHarga() {
        namaHarga = new NamaHarga();
        modelPage1 = new ModelPage1();
        // Kedua set dilakukan dengan IntStream untuk menjadikan array sebagai list sesuai model
        // Filter dijalankan untuk memastikan bahwa array dari data nilainya tidak ada null
        // Kalau null pada salah satu array saja maka pada kedua list yang diset nantinya tidak akan mencakup
        // null
        this.modelPage1.setNama(
                IntStream.range(0, namaHarga.getNama().length)
                        .filter(i -> namaHarga.getNama()[i] != null && namaHarga.getHarga()[i] != null)
                        .mapToObj(i -> namaHarga.getNama()[i])
                        .collect(Collectors.toList())
        );
        this.modelPage1.setHarga(
                IntStream.range(0, namaHarga.getHarga().length)
                        .filter(i -> namaHarga.getNama()[i] != null && namaHarga.getHarga()[i] != null)
                        .mapToObj(i -> namaHarga.getHarga()[i])
                        .collect(Collectors.toList())
        );
    }

    public List<String> assignedNama() {
        this.setNamaHarga();
        return this.modelPage1.getNama();
    }

    public List<Integer> assignedHarga() {
        this.setNamaHarga();
        return this.modelPage1.getHarga();
    }
}
