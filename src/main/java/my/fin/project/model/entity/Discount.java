package my.fin.project.model.entity;

public class Discount extends Entity {

    private User client;
    private int discountRate;
    private int totalSumRides;

    public Discount() {
    }

    public Discount(User client, int discountRate, int totalSumRides) {
        this.client = client;
        this.discountRate = discountRate;
        this.totalSumRides = totalSumRides;
    }

    public User getClient() {
        return client;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public int getTotalSumRides() {
        return totalSumRides;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "client=" + client +
                ", discountRate=" + discountRate +
                ", totalSumRides=" + totalSumRides +
                '}';
    }
}
