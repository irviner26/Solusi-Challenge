import org.binaracademy.repository.RepoPage1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TestRepository {
    @Test
    public void testAssignedNama__SUCCESS() {
        RepoPage1 rp1 = new RepoPage1();
        List<String> ekspektasi = Arrays.asList("A", "B", "C", "D", "E");
        List<String> hasil = rp1.assignedNama();
        Assertions.assertEquals(ekspektasi, hasil);
        System.out.println(hasil);
    }

    @Test
    public void testAssignedHarga_SUCCESS() {
        RepoPage1 rp1 = new RepoPage1();
        List<Integer> ekspektasi = Arrays.asList(1000, 2000, 3000, 4000, 5000);
        List<Integer> hasil = rp1.assignedHarga();
        Assertions.assertEquals(ekspektasi, hasil);
        System.out.println(hasil);
    }
}
