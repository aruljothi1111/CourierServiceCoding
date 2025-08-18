public class Vehicle {
    private final int id;
    private double time;
    public int getId() {
        return id;
    }
    public double getTime() {
        return time;
    }
        public void setTime(double time) { this.time = time; }

    public Vehicle(int id, double time) {
        this.id = id;
        this.time = time;

    }
    public Vehicle(Vehicle other) {
        this.id = other.id;
        this.time = other.time;
    }

    @Override
    public String toString() {
        double truncatedDuration = Math.floor(time * 100) / 100.0;
        return String.format("id: %s, time: %.2f",
            id, truncatedDuration);
    }
}