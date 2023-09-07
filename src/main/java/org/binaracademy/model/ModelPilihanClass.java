package org.binaracademy.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ModelPilihanClass extends ModelMakananClass {
    public ModelPilihanClass() {
        super();
    }
    private List<Integer> kuantitasPesanan = new ArrayList<>();
    public void addKuantitasPesanan(Integer insertKuantitasPesanan) {
        kuantitasPesanan.add(insertKuantitasPesanan);
    }
}
