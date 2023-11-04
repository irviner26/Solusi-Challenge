package org.binaracademy.challenge4.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.binaracademy.challenge4.model.Order;
import org.binaracademy.challenge4.model.response.DetailResponse;
import org.binaracademy.challenge4.model.response.InvoiceResponse;
import org.binaracademy.challenge4.repository.DetailRepository;
import org.binaracademy.challenge4.repository.OrderRepository;
import org.binaracademy.challenge4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DetailRepository detailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DetailService detailService;

    @Override
    public byte[] generateInvoice(String username) throws FileNotFoundException, JRException {
        List<InvoiceResponse> invoiceResponses = new ArrayList<>();
        List<DetailResponse> detailResponses = new ArrayList<>();
        Order userLatestOrder = orderRepository.getLatestOrderOfUser(username);
        detailRepository.querySelectDetail(userLatestOrder.getId())
                .forEach(detail -> {
                    invoiceResponses.add(InvoiceResponse.builder()
                            .productName(productRepository.getReferenceById(detail.getProduct().getCode()).getName())
                            .price("Rp. "+detail.getTotal())
                            .quantity((long) detail.getQuantity())
                            .build());

                    detailResponses.add(DetailResponse
                            .builder()
                                    .productFinalPrice(detail.getTotal())
                            .build());
                        }
                );

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("username", username);
        parameterMap.put("finalPrice",Double.toString(detailService.totalPriceInCart(detailResponses)));
        parameterMap.put("orderDetail", invoiceResponses);
        JasperPrint invoice = JasperFillManager.fillReport(
                JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:invoice_v2.jrxml").getAbsolutePath()),
                parameterMap,
                new JRBeanCollectionDataSource(invoiceResponses)
        );

        return JasperExportManager.exportReportToPdf(invoice);
    }
}
