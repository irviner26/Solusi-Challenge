package org.binaracademy.model;

import lombok.Data;

@Data
public class ModelUserInput {
    // ini merupakan POJO untuk user input

    // Yang pertama ada user input langsung yang bernilai integer (1 - 5, 99, dan 0)
    // dan yang kedua dan ketiga adalah variabel konfirmasi yang digunakan untuk
    // melakukan pengulangan kembali menu jika input tidak sesuai
    // (selebihnya ada di folder controller)
    private Integer input;
    private boolean cycle;
    private String cycleWarning;
}
