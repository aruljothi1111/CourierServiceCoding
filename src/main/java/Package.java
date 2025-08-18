public class Package {
    private final String id;
    private final double weight;
    private final double distance;
    private final String offerCode;

    public Package(String id, double weight, double distance, String offerCode) {
        this.id = id;
        this.weight = weight;
        this.distance = distance;
        this.offerCode = offerCode;
    }
 public String getId() { return id; }
    public double getWeight() { return weight; }
    public double getDistance() { return distance; }
    public String getOfferCode() { return offerCode; }
}
