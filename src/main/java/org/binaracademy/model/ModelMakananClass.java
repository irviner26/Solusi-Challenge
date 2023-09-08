package org.binaracademy.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.binaracademy.databaseapp.DataMakananClass;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ModelMakananClass {
    DataMakananClass dataMakananClass = new DataMakananClass();
    // Kelas untuk makanan (atau minuman)
    private List<String> namaMakanan = new ArrayList<>();
    private List<Integer> hargaMakanan = new ArrayList<>();
}
