package org.binaracademy.service;

import org.binaracademy.repository.RepoPage2;

public class ServicePage2 extends ServiceAdditional implements ServicePage2I{

    public ServicePage2() {
        super();
    }

    @Override
    public void tampilPes(Integer input) {
        RepoPage2 repoPage2 = new RepoPage2();
        StringBuffer whitespace = new StringBuffer();
        this.header();
        System.out.println("Berapa pesanan anda?");
        this.header();
        System.out.println(repoPage2.assignedNamaPesanan(input)
                            +whiteSpace(repoPage2.assignedNamaPesanan(input).length(),whitespace)
                            +repoPage2.assignedHargaPesanan(input)
        );
        this.header();
        System.out.println("QTY => ");
    }
}
