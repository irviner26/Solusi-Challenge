package org.binaracademy.service;

import org.binaracademy.model.ModelCart;

import java.util.List;

public interface ServiceCartI {
    void isiQTY(Integer input1, Integer input2);
    void tampilCart(List<ModelCart> cart);
    List<ModelCart> listCart();
}
