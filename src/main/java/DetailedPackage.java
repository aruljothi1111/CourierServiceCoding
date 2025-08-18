public class DetailedPackage extends Package {
     private double duration;
     private double discount;
     private double totalPrice;

    public DetailedPackage(String id, double weight, double distance, String offerCode,
                           double duration, double discount, double totalPrice) {
        super(id, weight, distance, offerCode);
        this.duration = duration;
        this.discount = discount;
        this.totalPrice = totalPrice;
    }

    public DetailedPackage(Package base, double duration, double discount, double totalPrice) {
        super(base.getId(), base.getWeight(), base.getDistance(), base.getOfferCode());
        this.duration = duration;
        this.discount = discount;
        this.totalPrice = totalPrice;
    }

    public DetailedPackage(DetailedPackage other) {
        super(other.getId(), other.getWeight(), other.getDistance(), other.getOfferCode());
        this.duration = other.duration;
        this.discount = other.discount;
        this.totalPrice = other.totalPrice;
    }

    public double getDuration() { return duration; }
    public void setDuration(double duration) { this.duration = duration; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    @Override
    public String toString() {
        return String.format(
            "DetailedPackage[id=%s, weight=%.2f, distance=%.2f, offerCode=%s, duration=%.2f, discount=%.2f, totalPrice=%.2f]",
            getId(), getWeight(), getDistance(), getOfferCode(), duration, discount, totalPrice
        );
    }
}
