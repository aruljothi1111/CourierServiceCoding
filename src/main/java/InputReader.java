import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputReader {
    private Scanner scanner;
    private double baseCost;
    private int numPackages;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
        readFirstLine();
    }

    private void readFirstLine() {
        String[] firstLine = scanner.nextLine().trim().split("\\s+");
        baseCost = Double.parseDouble(firstLine[0]);
        numPackages = Integer.parseInt(firstLine[1]);
    }

    public double getBaseCost() {
        return baseCost;
    }

    public List<DetailedPackage> getDetailedPackages() {
        List<DetailedPackage> packages = new ArrayList<>();

        for (int i = 0; i < numPackages; i++) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                i--;
                continue;
            }
            String[] parts = line.split("\\s+");
            if (parts.length != 4) {
                System.out.println("Invalid input. Please enter exactly 4 values for package " + (i + 1));
                i--;
                continue;
            }

            String pkgId = parts[0];
            double weight = Double.parseDouble(parts[1]);
            double distance = Double.parseDouble(parts[2]);
            String offerCode = parts[3];

            packages.add(new DetailedPackage(pkgId, weight, distance, offerCode, 0, 0, 0));
        }

        return packages;
    }

    public VehicleConfig readVehicleConfig() {
        if (!scanner.hasNextLine()) {
            throw new IllegalArgumentException("Missing vehicle information in input");
        }
        String[] vehicleParts = scanner.nextLine().trim().split("\\s+");
        int numVehicles = Integer.parseInt(vehicleParts[0]);
        double maxSpeed = Double.parseDouble(vehicleParts[1]);
        double maxCarriableWeight = Double.parseDouble(vehicleParts[2]);
        return new VehicleConfig(numVehicles, maxSpeed, maxCarriableWeight);
    }
}
