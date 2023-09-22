import org.binaracademy.repository.RepoCart;
import org.binaracademy.repository.RepoPage1;
import org.binaracademy.repository.RepoPage2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TestRepository {
    RepoPage1 rp1;
    RepoPage2 rp2;
    @BeforeEach
    public void initialized() {
        rp1 = new RepoPage1();
        rp2 = new RepoPage2();
    }
    @Test
    public void testAssignedNama__SUCCESS() {
        List<String> ekspektasi = Arrays.asList("Nasi Goreng", "Mie Goreng", "Nasi + Ayam", "Es Teh Jeruk", "Es Jeruk");
        List<String> hasil = rp1.assignedNama();
        Assertions.assertEquals(ekspektasi, hasil);
        System.out.println(hasil);
    }

    @Test
    public void testAssignedHarga_SUCCESS() {
        List<Integer> ekspektasi = Arrays.asList(15000, 13000, 18000, 3000, 5000);
        List<Integer> hasil = rp1.assignedHarga();
        Assertions.assertEquals(ekspektasi, hasil);
        System.out.println(hasil);
    }

    @Test
    public void testassignedNamaPesanan__SUCCESS() {
        String hasil = rp2.assignedNamaPesanan(3);
        Assertions.assertEquals("Nasi + Ayam", hasil);
    }
}
