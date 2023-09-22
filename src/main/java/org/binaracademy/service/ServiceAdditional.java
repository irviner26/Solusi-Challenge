package org.binaracademy.service;

public class ServiceAdditional implements ServiceAdditionalI {
    @Override
    public void header() {
        System.out.println("================================");
    }

    @Override
    public void warning() {
        header();
        System.out.println("Input anda salah!");
        header();
        System.out.println("(Y) untuk lanjut");
        System.out.println("(N) untuk keluar");
    }

    @Override
    public void arrow() {
        System.out.println("=>");
    }

    @Override
    public StringBuffer whiteSpace(Integer n, StringBuffer ws) {
        if (n > 0) {
            ws.append(" ");
            whiteSpace(n-1, ws);
        }
        return ws;
    }
}
