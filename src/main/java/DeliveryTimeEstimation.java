import java.util.*;
import java.util.stream.Collectors;

public class DeliveryTimeEstimation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            //adding offers given in the problem
            OfferRepository offerRepo = new OfferRepository();

            //gives list of offers
            List<Offer> offers = offerRepo.getOffers(); 
            DeliveryCostService deliveryCostService = new DeliveryCostService();
            DeliveryTimeService deliveryTimeService = new DeliveryTimeService();
            VehicleService vehicleService = new VehicleService();

            InputReader inputReader = new InputReader(scanner);
            
            // gives base delivery cost
            double baseDeliveryCost = inputReader.getBaseCost();

            //list of packages
            List<DetailedPackage> packages = inputReader.getDetailedPackages();

            //vehicle datas
            VehicleConfig vehicleConfig = inputReader.readVehicleConfig();
            
            // calculation of delivery cost
            for (DetailedPackage pkg: packages) {
                 deliveryCostService.applyDiscountAndTotal(pkg, offers, baseDeliveryCost);
            
                // System.out.println(pkg.id + " "+truncateDecimal(discount) + " "+ truncateDecimal(deliveryCharge - discount));
            }
   
            //gives the combination of packages which deliver together
            List<List<Double>> result = deliveryTimeService.groupPackages(packages, vehicleConfig);


            // System.out.println(result);
            // initially vehicle current time is 0 before start
            List<Vehicle> vehicles = vehicleService.initializeVehicles(vehicleConfig.getNumVehicles());

            //assigning vehicles to the package and updating the time of vehicle
            vehicleService.assignPackagesToVehicles(packages, result, vehicles, vehicleConfig);


            //calculation vehicle time and adding it into vehicle object
            //final calculation of delivery time
            for (DetailedPackage pkg: packages.stream()
                .sorted(Comparator.comparing(Package::getId))
                .collect(Collectors.toList())) {
                System.out.println(pkg.getId() + " " + DeliveryCostService.truncateDecimalString(pkg.getDiscount()) + " " + DeliveryCostService.truncateDecimalString(pkg.getTotalPrice()) + " " + pkg.getDuration());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unexpected error: " + e.getMessage());
        }

        scanner.close();
    }

}
