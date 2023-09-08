package org.binaracademy.service;

import org.binaracademy.repository.ProductRepo;

public class PilihanServiceImpl implements PilihanService{
    ProductRepo productRepo = new ProductRepo();
    @Override
    public String pilihanMakanan(int i) {
        return productRepo.repoMakanan().get(i);
    }
    @Override
    public int hargaPilihanMakanan(int i) {
        return productRepo.repoHargaMakanan().get(i);
    }
}
