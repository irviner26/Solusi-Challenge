package org.binaracademy.challenge4.service;

import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public interface InvoiceService {

    byte[] generateInvoice (String username) throws FileNotFoundException, JRException;

}
