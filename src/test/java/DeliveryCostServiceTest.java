import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCostServiceTest {

    @Test
    void testDeliveryChargeWithoutOffer() {
        DeliveryCostService service = new DeliveryCostService();
        Package pkg = new Package("PKG1", 5, 5, null);
        double cost = service.calculateDeliveryCharge(pkg, 100);
        assertEquals(175, cost, 0.01);
    }

    
    @Test
    void testDeliveryChargeWithValidOffer() {
        DeliveryCostService service = new DeliveryCostService();
        Offer offer = new Offer("OFR001", 10, 0, 199, 70, 200);
        Package pkg = new Package("PKG2", 80, 100, "OFR001");

        double discount = service.calculateDiscount(pkg, offer, 100);
        assertEquals(140, discount, 0.01);
    }

    @Test
    void testDeliveryChargeWithInvalidOffer() {
        DeliveryCostService service = new DeliveryCostService();
        Offer offer = new Offer("OFR002", 7, 50, 150, 100, 250);
        Package pkg = new Package("PKG3", 80, 40, "OFR002");

        double discount = service.calculateDiscount(pkg, offer, 100);
        assertEquals(0, discount, 0.01);
    }

     @Test
    void testTruncateDecimalWholeNumber() {
        assertEquals("100", DeliveryCostService.truncateDecimalString(100.0));
    }

    @Test
    void testTruncateDecimalWithDecimals() {
        assertEquals("123.45", DeliveryCostService.truncateDecimalString(123.456));
    }

    @Test
    void testTruncateDecimalFunction() {
        assertEquals(12.34, DeliveryCostService.truncateDecimal(12.349), 0.0001);
        assertEquals(12.30, DeliveryCostService.truncateDecimal(12.309), 0.0001);
    }
}
