package org.binaracademy.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ModelPage2 {
    // Class model untuk page 2 berisikan variabel yang digunakan untuk penampilan menu yang dipilih
    // dan juga sebagai model untuk kuantitas pilihan dari user
    private String pilihan;
    private Integer hargaPilihan;
}
