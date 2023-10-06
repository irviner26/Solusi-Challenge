package com.binaracademy.latihanchap4.service;

import com.binaracademy.latihanchap4.model.Pesan;
import com.binaracademy.latihanchap4.model.response.PesanResponse;
import org.springframework.stereotype.Service;

@Service
public interface PesanService {
    void addPesanToDB(Pesan pesan);
    PesanResponse doPesan(String alamat, Integer harga);
}
