import org.binaracademy.service.ServiceCart;
import org.binaracademy.service.ServicePage1;
import org.binaracademy.service.ServicePage2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestService {

    ServicePage1 servicePage1;
    ServicePage2 servicePage2;
    ServiceCart serviceCart;

    @BeforeEach
    public void initialize() {
        servicePage1 = new ServicePage1();
        servicePage2 = new ServicePage2();
        serviceCart = new ServiceCart();
    }
    @Test
    public void testTampilNH() {
        servicePage1.tampilNH();
    }
    @Test
    public void testTampilPesanan__SUCCESS() {
        servicePage2.tampilPes(1);
        servicePage2.tampilPes(2);
        servicePage2.tampilPes(3);
        servicePage2.tampilPes(4);
        servicePage2.tampilPes(5);
    }
    @Test
    public void testTampilPesanan__FAILED__outofbound() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> servicePage2.tampilPes(6));
    }

    @Test
    public void testisiQTY() {
        serviceCart.isiQTY(1,1);
        serviceCart.isiQTY(2,1);
        serviceCart.isiQTY(3,1);
        serviceCart.isiQTY(4,1);
        serviceCart.isiQTY(5,1);
        System.out.println(serviceCart.qty);
    }

    @Test
    public void testlistCart__SUCCESS() {
        serviceCart.isiQTY(1,1);
        System.out.println(serviceCart.listCart());
    }

    @Test
    public void testtampilCart() {
        serviceCart.isiQTY(3,1);
        serviceCart.tampilCart(serviceCart.listCart());
    }

}
