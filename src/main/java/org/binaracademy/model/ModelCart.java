package org.binaracademy.model;

import lombok.Builder;
import lombok.Data;



@Builder
@Data
public class ModelCart {
    // POJO class untuk shopping cart dari user.
    private String nama;
    private Integer harga;
    private Integer kuantitas;
}
