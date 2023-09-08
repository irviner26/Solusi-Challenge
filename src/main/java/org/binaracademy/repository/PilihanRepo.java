package org.binaracademy.repository;

import org.binaracademy.databaseapp.DataMakananClass;
import org.binaracademy.model.ModelPilihanClass;
import java.util.List;

public class PilihanRepo extends ProductRepo {


    DataMakananClass dataMakananClass = new DataMakananClass();
    ModelPilihanClass modelPilihanClass = new ModelPilihanClass();
    String[] defArrayMakanan = dataMakananClass.getDataMakanan();
    public List<Integer> defaultQTY() {
        List<Integer> defaultKuantitas;
        defaultKuantitas = modelPilihanClass.getKuantitasPesanan();
        for (int i = 0; i <= dataMakananClass.getHargaMakanan().length - 1; i++) {
            defaultKuantitas.add(0);
        }
        return defaultKuantitas;
    }
    public List<Integer> defaultHarga() {
        List<Integer> defaultHargaList;
        defaultHargaList = modelPilihanClass.getHargaMakanan();
        for (int i = 0; i <= dataMakananClass.getHargaMakanan().length - 1; i++) {
            defaultHargaList.add(0);
        }
        return defaultHargaList;
    }
    @Override
    public List<String> repoMakanan() {
        List<String> defRepoMakanan = modelMakananClass.getNamaMakanan();
        for (int i = 0; i <= dataMakananClass.getDataMakanan().length - 1; i++) {
            defRepoMakanan.add(defArrayMakanan[i]);
        }
        return defRepoMakanan;
    }
}
