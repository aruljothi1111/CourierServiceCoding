import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VehicleService {
      public List<Vehicle> initializeVehicles(int numVehicles) {
        List<Vehicle> vehicles = new ArrayList<>();
        for (int k = 1; k <= numVehicles; k++) {
            vehicles.add(new Vehicle(k, 0.0));
        }
        return vehicles;
    }

    public void assignPackagesToVehicles(List<DetailedPackage> packages,
                                         List<List<Double>> groupedWeights,
                                         List<Vehicle> vehicles,
                                         VehicleConfig config) {
        for (List<Double> lst : groupedWeights) {
            int i = 0;
            double maxDist = 0;
            for (Double arr : lst) {
                DetailedPackage firstMatch = packages.stream()
                        .filter(pkg -> pkg.getWeight() == arr)
                        .findFirst().orElse(null);

                Vehicle vehicle = vehicles.stream()
                        .min(Comparator.comparingDouble(Vehicle::getTime))
                        .orElse(null);

                if (firstMatch != null && vehicle != null) {
                    firstMatch.setDuration(DeliveryCostService.truncateDecimal(
                            vehicle.getTime() + (firstMatch.getDistance() / config.getMaxSpeed())));
                    if (firstMatch.getDistance() > maxDist) {
                        maxDist = firstMatch.getDistance();
                    }
                    if (i == lst.size() - 1) {
                        vehicle.setTime(vehicle.getTime()
                                + (2 * DeliveryCostService.truncateDecimal(maxDist / config.getMaxSpeed())));
                    }
                }
                i++;
            }
        }
    }
}
