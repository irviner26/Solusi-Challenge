package com.binaracademy.latihanchap4.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PesanResponse {
    private String alamat;
    private Integer harga;
    private String namaDriver;
}
