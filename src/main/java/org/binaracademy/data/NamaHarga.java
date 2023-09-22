package org.binaracademy.data;

import lombok.Getter;

@Getter
public class NamaHarga {
    private final String[] nama = {"Nasi Goreng",
                                    "Mie Goreng",
                                    "Nasi + Ayam",
                                    "Es Teh Jeruk",
                                    "Es Jeruk"};
    private final Integer[] harga = {15000, 13000, 18000, 3000, 5000};
}
