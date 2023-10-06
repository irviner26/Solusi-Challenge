package com.binaracademy.latihanchap4.service;

import com.binaracademy.latihanchap4.model.Pesan;
import com.binaracademy.latihanchap4.model.response.PesanResponse;
import com.binaracademy.latihanchap4.repository.PesanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PesanServiceImpl implements PesanService{
    @Autowired
    private PesanRepo pesanRepo;
    @Autowired
    private OjekService ojekService;

    @Override
    public void addPesanToDB(Pesan pesan) {
        this.pesanRepo.tambahDb(pesan.getAlamat(), pesan.getPrice(), pesan.getOjek().getNama());
    }

    @Override
    public PesanResponse doPesan(String alamat, Integer harga) {
        this.addPesanToDB(Pesan.builder()
                .price(harga)
                .alamat(alamat)
                .ojek(this.ojekService.isAvailFirst())
                .build());
        return PesanResponse.builder()
                .namaDriver(this.ojekService.isAvailFirst().getNama())
                .harga(harga)
                .alamat(alamat)
                .build();
    }



}
