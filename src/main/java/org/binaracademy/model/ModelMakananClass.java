package org.binaracademy.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ModelMakananClass {
    // Kelas untuk makanan (atau minuman)
    private List<String> namaMakanan = new ArrayList<>();
    private List<Integer> hargaMakanan = new ArrayList<>();
    public void addNamaMakanan(String insertNamaMakanan) {
        namaMakanan.add(insertNamaMakanan);
    }
    public void addHargaMakanan(Integer insertHargaMakanan) {
        hargaMakanan.add(insertHargaMakanan);
    }
}
