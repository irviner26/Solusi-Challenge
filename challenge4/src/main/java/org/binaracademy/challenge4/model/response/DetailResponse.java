package org.binaracademy.challenge4.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailResponse {
    private String productName;
    private double productFinalPrice;
    private int productQuantity;
}
