package org.binaracademy.repository;

import org.binaracademy.databaseapp.DataMakananClass;
import org.binaracademy.model.ModelMakananClass;

import java.util.Arrays;
import java.util.List;

public class ProductRepo {
    ModelMakananClass modelMakananClass = new ModelMakananClass();
    DataMakananClass dataMakananClass = new DataMakananClass();
    // Update model makanan dengan data yang diambil di databaseapp
    String[] defArrayMakanan = dataMakananClass.getDataMakanan();
    Integer[] defArrayHarga = dataMakananClass.getHargaMakanan();
    public List<String> repoMakanan() {
        List<String> defRepoMakanan;
        defRepoMakanan = Arrays.asList(defArrayMakanan);
        return defRepoMakanan;
    }
    public List<Integer> repoHargaMakanan() {
        List<Integer> defRepoHarga = modelMakananClass.getHargaMakanan();
        for (int i = 0; i <= dataMakananClass.getHargaMakanan().length - 1; i++) {
            defRepoHarga.add(defArrayHarga[i]);
        }
        return defRepoHarga;
    }
}
