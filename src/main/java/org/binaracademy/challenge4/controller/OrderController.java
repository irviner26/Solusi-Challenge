package org.binaracademy.challenge4.controller;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.binaracademy.challenge4.model.Detail;
import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.response.OrderResponse;
import org.binaracademy.challenge4.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Date;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    DetailService detailService;

    @Autowired
    InvoiceService invoiceService;

    //TODO: Membuat Pesanan

    @PostMapping(value = "/api/binarfud/ordering/{username}", consumes = "application/json")
    public ResponseEntity<String> requestMakeOrder(@PathVariable("username") String username,
                                                   @RequestBody OrderResponse orderResponse) {
        Order orderUser =  orderService.orderBuilder(username,new Date(),orderResponse.getOrderDestination(),false);

        if (orderService.addOrderToDB(orderUser)) {
            orderResponse.getDetailResponses().forEach(detailResponse ->
            {
                Detail detail = detailService.buildOrderDetail(
                        detailResponse.getProductQuantity(),
                        detailService.finalPrice(detailResponse.getProductPrice(),detailResponse.getProductQuantity()),
                        orderUser,
                        detailResponse.getProductName(),
                        orderResponse.getMerchantName());
                detailService.addDetailsToDB(detail);
            });
            return new ResponseEntity<>("Successfully added order", HttpStatus.OK);
        } else return new ResponseEntity<>("Failed to add order", HttpStatus.NOT_ACCEPTABLE);
    }


    @GetMapping (value = "/api/binarfud/ordered/{username}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity generateInvoice(@PathVariable("username") String username) throws JRException, FileNotFoundException {
        return ResponseEntity.ok().body(invoiceService.generateInvoice(username));
    }

}
