package org.binaracademy.controller;

import org.binaracademy.model.ModelUserInput;
import org.binaracademy.service.ServiceAdditional;
import org.binaracademy.service.ServiceAdditionalI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ControlUI {
    // User input yaitu berupa input angka / integer (bukan huruf atau karakter apapun)
    // Pada class ini, akan dibuat handler untuk input-input tersebut.
    public void handleInput(ModelUserInput modelUserInput) {
        ServiceAdditionalI serviceAdditional = new ServiceAdditional();
        Scanner scanner = new Scanner(System.in);
        try {
            modelUserInput.setInput(scanner.nextInt());
            modelUserInput.setCycle(false);
        } catch (InputMismatchException ime) {
            do{
                scanner.nextLine();
                serviceAdditional.warning();
                try {
                    modelUserInput.setCycleWarning(scanner.next());
                } catch (InputMismatchException ime2) {
                    serviceAdditional.arrow();
                }
                if (modelUserInput.getCycleWarning().equalsIgnoreCase("n")) {
                    break;
                }
            } while (!modelUserInput.getCycleWarning().equalsIgnoreCase("y")
                    && !modelUserInput.getCycleWarning().equalsIgnoreCase("n"));
            modelUserInput.setCycle(true);
        }
    }
}
