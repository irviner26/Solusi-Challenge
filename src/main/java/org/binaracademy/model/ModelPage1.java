package org.binaracademy.model;

import lombok.Data;
import java.util.List;

@Data
public class ModelPage1 {
    // POJO class untuk menu pesanan
    // Berisikan list untuk nama dan harganya
    private List<String> nama;
    private List<Integer> harga;
}
