package my.fin.project.model.entity;

import my.fin.project.model.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order extends Entity {

    private OrderStatus orderStatus;
    private Long clientId;
    private Long driverId;
    private String originAddress;
    private String arriveAddress;
    private BigDecimal cost;
    private Long carId;
    private LocalDateTime creationDate;
    private String distance;

    public Order() {
    }

    public Order(OrderStatus orderStatus, Long clientId, Long driverId,
                 String originAddress, String arriveAddress, BigDecimal cost,
                 Long carId, LocalDateTime creationDate, String distance) {
        this.orderStatus = orderStatus;
        this.clientId = clientId;
        this.driverId = driverId;
        this.originAddress = originAddress;
        this.arriveAddress = arriveAddress;
        this.cost = cost;
        this.carId = carId;
        this.creationDate = creationDate;
        this.distance = distance;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Long getClientId() {
        return clientId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public String getArriveAddress() {
        return arriveAddress;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public Long getCarId() {
        return carId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getDistance() {
        return distance;
    }

    public static class Builder {

        private OrderStatus orderStatus;
        private Long clientId;
        private Long driverId;
        private String originAddress;
        private String arriveAddress;
        private BigDecimal cost;
        private Long carId;
        private LocalDateTime creationDate;
        private String distance;

        public Order.Builder setOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;

            return this;
        }
        public Order.Builder setClientId(Long clientId) {
            this.clientId = clientId;

            return this;
        }
        public Order.Builder setDriverId(Long driverId) {
            this.driverId = driverId;

            return this;
        }
        public Order.Builder setOriginAddress(String originAddress) {
            this.originAddress = originAddress;

            return this;
        }
        public Order.Builder setArriveAddress(String arriveAddress) {
            this.arriveAddress = arriveAddress;

            return this;
        }
        public Order.Builder setCost(BigDecimal cost) {
            this.cost = cost;

            return this;
        }
        public Order.Builder setCarId(Long carId) {
            this.carId = carId;

            return this;
        }
        public Order.Builder setCrDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;

            return this;
        }
        public Order.Builder setDistance(String distance) {
            this.distance = distance;

            return this;
        }
        public Order build() {
            Order order = new Order();
            order.orderStatus = this.orderStatus;
            order.clientId = this.clientId;
            order.driverId = this.driverId;
            order.originAddress = this.originAddress;
            order.arriveAddress = this.arriveAddress;
            order.cost = this.cost;
            order.carId = this.carId;
            order.creationDate = this.creationDate;
            order.distance = this.distance;

            return order;
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderStatus=" + orderStatus +
                ", clientId=" + clientId +
                ", driverId=" + driverId +
                ", deptAddress='" + originAddress + '\'' +
                ", arriveAddress='" + arriveAddress + '\'' +
                ", cost=" + cost +
                ", carId=" + carId +
                ", creationDate=" + creationDate +
                ", distance='" + distance + '\'' +
                '}';
    }
}
