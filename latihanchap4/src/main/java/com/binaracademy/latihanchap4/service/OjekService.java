package com.binaracademy.latihanchap4.service;

import com.binaracademy.latihanchap4.model.Ojek;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OjekService {

    void addOjekToDB(Ojek ojek);

    void deleteAllDataOjekDB();

    Ojek isAvailFirst();

    List<Ojek> yangAvail();
}
