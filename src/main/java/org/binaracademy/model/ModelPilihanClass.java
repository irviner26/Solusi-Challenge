package org.binaracademy.model;

import lombok.Getter;
import org.binaracademy.databaseapp.DataMakananClass;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ModelPilihanClass extends ModelMakananClass {
    DataMakananClass dataMakananClass = new DataMakananClass();
    public ModelPilihanClass() {
        super();
    }
    private List<Integer> kuantitasPesanan = new ArrayList<>();
}
