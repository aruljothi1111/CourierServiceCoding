import java.util.List;

public class DeliveryCostService{
    public double calculateDeliveryCharge(Package pkg, double baseDeliveryCost) {
        return baseDeliveryCost + (pkg.getWeight() * 10) + (pkg.getDistance() * 5);
    }
    public double calculateDiscount(Package pkg, Offer offer, double baseDeliveryCost) {
        double deliveryCharge = calculateDeliveryCharge(pkg, baseDeliveryCost);

        if (offer != null 
            && pkg.getOfferCode() != null 
            && pkg.getOfferCode().equals(offer.getId())
            && pkg.getDistance() >= offer.getDistanceFrom() 
            && pkg.getDistance() <= offer.getDistanceTo()
            && pkg.getWeight() >= offer.getWeightFrom() 
            && pkg.getWeight() <= offer.getWeightTo()) {
            
            return deliveryCharge * (offer.getDiscountPercentage() / 100);
        }

        return 0;
    }

    public double calculateFinalCost(Package pkg, Offer offer, double baseDeliveryCost) {
        double deliveryCharge = calculateDeliveryCharge(pkg, baseDeliveryCost);
        double discount = calculateDiscount(pkg, offer, baseDeliveryCost);
        return deliveryCharge - discount;
    }

 public void applyDiscountAndTotal(DetailedPackage pkg, List<Offer> offers, double baseDeliveryCost) {
       Double deliveryCharge = baseDeliveryCost + (pkg.getWeight() * 10) + (pkg.getDistance() * 5);
                double discount = 0;
                if (pkg.getOfferCode() != null && pkg.getOfferCode() != "") {
                    Offer off = offers.stream().filter(offs -> {

                        return (pkg.getOfferCode().equals(offs.getId()) && pkg.getDistance() >= offs.getDistanceFrom() && pkg.getDistance() <= offs.getDistanceTo() && pkg.getWeight() >= offs.getWeightFrom() && pkg.getWeight() <= offs.getWeightTo());
                    }).findFirst().orElse(null);

                    if (off != null) {
                        discount = deliveryCharge * (off.getDiscountPercentage() / 100);
                    }
                }
                pkg.setDiscount(truncateDecimal(discount));
                pkg.setTotalPrice(truncateDecimal(deliveryCharge - discount));
 }

    public static double truncateDecimal(double value) {
        return Math.floor(value * 100) / 100.0;
    }
    public static String truncateDecimalString(double value) {
    double truncated = Math.floor(value * 100) / 100.0;
    if (truncated == Math.floor(truncated)) {
        return Integer.toString((int) truncated);
    }
    return String.format(java.util.Locale.US, "%.2f", truncated);
    }
}
