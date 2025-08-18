public class Offer {
    private final String id;
    private final double discountPercentage;
    private final double distanceFrom;
    private final double distanceTo;
    private final double weightFrom;
    private final double weightTo;

    public Offer(String id, double discountPercentage,
                 double distanceFrom, double distanceTo,
                 double weightFrom, double weightTo) {
        this.id = id;
        this.discountPercentage = discountPercentage;
        this.distanceFrom = distanceFrom;
        this.distanceTo = distanceTo;
        this.weightFrom = weightFrom;
        this.weightTo = weightTo;
    }

    public String getId() {
        return id;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public double getDistanceFrom() {
        return distanceFrom;
    }

    public double getDistanceTo() {
        return distanceTo;
    }

    public double getWeightFrom() {
        return weightFrom;
    }

    public double getWeightTo() {
        return weightTo;
    }
 public boolean isApplicable(Package pkg) {
        return pkg.getDistance() >= distanceFrom &&
               pkg.getDistance() <= distanceTo &&
               pkg.getWeight() >= weightFrom &&
               pkg.getWeight() <= weightTo;
    }
}