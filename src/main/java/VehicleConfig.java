public class VehicleConfig {
    private final int numVehicles;
    private final double maxSpeed;
    private final double maxCarriableWeight;

    public VehicleConfig(int numVehicles, double maxSpeed, double maxCarriableWeight) {
        this.numVehicles = numVehicles;
        this.maxSpeed = maxSpeed;
        this.maxCarriableWeight = maxCarriableWeight;
    }

    public int getNumVehicles() { return numVehicles; }
    public double getMaxSpeed() { return maxSpeed; }
    public double getMaxCarriableWeight() { return maxCarriableWeight; }
}
