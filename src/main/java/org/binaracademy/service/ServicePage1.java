package org.binaracademy.service;

import org.binaracademy.repository.RepoPage1;

public class ServicePage1 implements ServicePage1I{

    // Method ini hanya digunakan untuk membuat view rapih (Mungkin akan dipindahkan ke Class baru)
    public StringBuffer whiteSpace(Integer n, StringBuffer ws) {
        if (n > 0) {
            ws.append(" ");
            whiteSpace(n-1, ws);
        }
        return ws;
    }
    @Override
    // Method penampilan nama dan harga makanan/minuman menggunakan
    public void tampilNH() {
        RepoPage1 repoPage1 = new RepoPage1();
        repoPage1.assignedNama()
                .forEach(val -> {
                    StringBuffer whitespace = new StringBuffer();
                    System.out.println(
                            repoPage1.assignedNama().indexOf(val)+1+ ". "
                                    +val
                                    +whiteSpace(20 - val.length(), whitespace)
                                    +repoPage1.assignedHarga().get(repoPage1.assignedNama().indexOf(val)));
                });
    }
}
