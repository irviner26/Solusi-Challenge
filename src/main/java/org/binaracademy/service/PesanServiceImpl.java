package org.binaracademy.service;

import org.binaracademy.repository.ProductRepo;

public class PesanServiceImpl implements PesanService {
    ProductRepo productRepo = new ProductRepo();

    @Override
    public String[] ambilListMakanan() {
        return productRepo.repoMakanan();
    }

    @Override
    public int[] ambilListHargaMakanan() {
        return productRepo.repoHargaMakanan();
    }

}
