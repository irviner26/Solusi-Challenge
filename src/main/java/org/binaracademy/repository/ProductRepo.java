package org.binaracademy.repository;

import org.binaracademy.databaseapp.DataMakananClass;
import org.binaracademy.model.ModelMakananClass;

import java.util.List;

public class ProductRepo {
    ModelMakananClass modelMakananClass = new ModelMakananClass();
    DataMakananClass dataMakananClass = new DataMakananClass();
    // Update model makanan dengan data yang diambil di databaseapp
    public void setModelMakananClass() {
        String[] dataMakanan = dataMakananClass.getDataMakanan();
        for (int i=0; i <= dataMakanan.length - 1; i++) {
            modelMakananClass.addNamaMakanan(dataMakanan[i]);
        }
    }
    public void setModelHargaMakananClass() {
        int[] hargaMakanan = dataMakananClass.getHargaMakanan();
        for (int i=0; i <= hargaMakanan.length - 1; i++) {
            modelMakananClass.addHargaMakanan(hargaMakanan[i]);
        }
    }
    public List<String> repoMakanan() {
        this.setModelMakananClass();
        return modelMakananClass.getNamaMakanan();
    }
    public List<Integer> repoHargaMakanan() {
        this.setModelHargaMakananClass();
        return modelMakananClass.getHargaMakanan();
    }
}
