import java.util.*;

public class DeliveryCostEstimation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
             //adding offers given in the problem
            OfferRepository offerRepo = new OfferRepository();

            //gives list of offers
            List<Offer> offers = offerRepo.getOffers(); 
            DeliveryCostService deliveryCostService = new DeliveryCostService();

            InputReader inputReader = new InputReader(scanner);
            
            // gives base delivery cost
            double baseDeliveryCost = inputReader.getBaseCost();

            //list of packages
            List<DetailedPackage> packages = inputReader.getDetailedPackages();
            for (Package pkg: packages) {
                Double discount = (double) 0;
                 Offer off=null;
                if (pkg.getOfferCode() != null && pkg.getOfferCode() != "") {
                     off = offers.stream().filter(offs -> {

                        return (pkg.getOfferCode().equals(offs.getId()) && pkg.getDistance() >= offs.getDistanceFrom() && pkg.getDistance() <= offs.getDistanceTo() && pkg.getWeight() >= offs.getWeightFrom() && pkg.getWeight()  <= offs.getWeightTo());
                    }).findFirst().orElse(null);

                    if (off != null) {
                         discount = deliveryCostService.calculateDiscount(pkg,off,baseDeliveryCost);
                        // discount = deliveryCharge * (off.discount / 100);
                    }
                }
                Double totalCost = deliveryCostService.calculateFinalCost(pkg,off,baseDeliveryCost);
                System.out.println(pkg.getId() + " "+DeliveryCostService.truncateDecimalString(discount) + " "+ DeliveryCostService.truncateDecimalString(totalCost));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unexpected error: " + e.getMessage());
        }

        scanner.close();
    }

}
