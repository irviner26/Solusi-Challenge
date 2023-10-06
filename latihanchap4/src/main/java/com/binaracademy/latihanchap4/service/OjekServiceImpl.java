package com.binaracademy.latihanchap4.service;

import com.binaracademy.latihanchap4.model.Ojek;
import com.binaracademy.latihanchap4.repository.OjekRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OjekServiceImpl implements OjekService{

    @Autowired
    private OjekRepo ojekRepo;

    @Override
    public void addOjekToDB(Ojek ojek) {
        ojekRepo.save(ojek);
    }

    @Override
    public void deleteAllDataOjekDB() {
        ojekRepo.deleteAll();
    }

    @Override
    public Ojek isAvailFirst() {
        return ojekRepo.driverStat(true).get(0);
    }

    @Override
    public List<Ojek> yangAvail() {
        return ojekRepo.driverStat(true);
    }
}
