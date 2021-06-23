package my.fin.project.model.entity;

public class Discount extends Entity {

    private Long clientId;
    private int discountRate;
    private int totalSumRides;

    public Discount() {
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

    public static class Builder {
        private Long clientId;
        private int discountRate;
        private int totalSumRides;

        public Discount.Builder setClientId(Long clientId) {
            this.clientId = clientId;

            return this;
        }

        public Discount.Builder setDiscountRate(int discountRate) {
            this.discountRate = discountRate;

            return this;
        }

        public Discount.Builder setTotalSumRides(int totalSumRides) {
            this.totalSumRides = totalSumRides;

            return this;
        }

        public Discount build() {
            Discount discount = new Discount();
            discount.clientId = this.clientId;
            discount.discountRate = this.discountRate;
            discount.totalSumRides = this.totalSumRides;
            return discount;
        }

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
