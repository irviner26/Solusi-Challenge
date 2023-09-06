package org.binaracademy.repository;

import org.binaracademy.databaseapp.DataMakananClass;
import org.binaracademy.model.ModelMakananClass;

public class ProductRepo {
    ModelMakananClass modelMakananClass = new ModelMakananClass();
    DataMakananClass dataMakananClass = new DataMakananClass();
    // Update model makanan dengan data yang diambil di databaseapp
    public void setModelMakananClass() {
        modelMakananClass.setNamaMakanan(dataMakananClass.getDataMakanan());
        modelMakananClass.setHargaMakanan(dataMakananClass.getHargaMakanan());
    }
    public String[] repoMakanan() {
        this.setModelMakananClass();
        return modelMakananClass.getNamaMakanan();
    }
    public int[] repoHargaMakanan() {
        this.setModelMakananClass();
        return modelMakananClass.getHargaMakanan();
    }


}
