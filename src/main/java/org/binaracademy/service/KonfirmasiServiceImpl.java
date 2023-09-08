package org.binaracademy.service;

import org.binaracademy.repository.PilihanRepo;

import java.util.List;


public class KonfirmasiServiceImpl implements KonfirmasiService{
    PilihanRepo pilihanRepo = new PilihanRepo();
    @Override
    public List<Integer> updateQTY() {
        return pilihanRepo.defaultQTY();
    }

    @Override
    public List<Integer> updateHarga() {
        return pilihanRepo.defaultHarga();
    }

    @Override
    public List<Integer> updateKuantitas(Integer inputKuantitas, Integer pilihanPesanan, List<Integer> defaultV) {
        defaultV.set(pilihanPesanan - 1, inputKuantitas);
        return defaultV;
    }
    @Override
    public List<Integer> updateHargaKuantitas(Integer inputKuantitas, Integer pilihanPesanan,List<Integer> defaultV) {
        defaultV.set(pilihanPesanan - 1,
                pilihanRepo.repoHargaMakanan().get(pilihanPesanan - 1) * inputKuantitas);
        return defaultV;
    }
    @Override
    public List<String> ambilListMenu() {
        return pilihanRepo.repoMakanan();
    }

}
