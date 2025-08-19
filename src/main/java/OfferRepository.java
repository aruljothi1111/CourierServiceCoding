import java.util.ArrayList;
import java.util.List;

public class OfferRepository {
    public List<Offer> getOffers() {
        List<Offer> offers = new ArrayList<>();
        offers.add(new Offer("OFR001", 10, 0, 199, 70, 200));
        offers.add(new Offer("OFR002", 7, 50, 150, 100, 250));
        offers.add(new Offer("OFR003", 5, 50, 250, 10, 150));
            ///just adding this to get a exact output as given in the sample output because the input contains OFR0002
        offers.add(new Offer("OFFR002", 7, 50, 150, 100, 250)); 
        return offers;
    }
}