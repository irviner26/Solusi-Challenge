package org.binaracademy.service;

import java.util.List;

public interface KonfirmasiService {
    List<Integer> updateQTY();
    List<Integer> updateHarga();
    List<Integer> updateKuantitas(Integer inputKuantitas, Integer pilihanPesanan, List<Integer> def);
    List<Integer> updateHargaKuantitas(Integer inputKuantitas, Integer pilihanPesanan, List<Integer> def);
    List<String> ambilListMenu();

}
