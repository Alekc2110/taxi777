package my.fin.project.model.entity;


public class Discount extends Entity {

    private Long clientId;
    private int discountRate;
    private int totalSumRides;

    public Discount() {
    }

    public Discount(Long clientId, int discountRate, int totalSumRides) {
        this.clientId = clientId;
        this.discountRate = discountRate;
        this.totalSumRides = totalSumRides;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }

    public int getTotalSumRides() {
        return totalSumRides;
    }

    public void setTotalSumRides(int totalSumRides) {
        this.totalSumRides = totalSumRides;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "clientId=" + clientId +
                ", discountRate=" + discountRate +
                ", totalSumRides=" + totalSumRides +
                '}';
    }
}
