package org.binaracademy.service;

import org.binaracademy.repository.ProductRepo;

import java.util.List;

public class PesanServiceImpl implements PesanService {
    ProductRepo productRepo = new ProductRepo();
    @Override
    public List<String> ambilListMakanan() {
        return productRepo.repoMakanan();
    }
    @Override
    public List<Integer> ambilListHargaMakanan() {
        return productRepo.repoHargaMakanan();
    }
}
