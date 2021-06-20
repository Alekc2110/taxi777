package my.fin.project.model.entity;

import my.fin.project.model.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order extends Entity {

    private OrderStatus orderStatus;
    private User client;
    private User driver;
    private String deptAddress;
    private String arriveAddress;
    private BigDecimal cost;
    private Car car;
    private LocalDateTime creationDate;
    private String distance;

    public Order() {
    }

    public Order(OrderStatus orderStatus, User client, User driver,
                 String deptAddress, String arriveAddress, BigDecimal cost,
                 Car car, LocalDateTime creationDate,
                 String distance) {
        this.orderStatus = orderStatus;
        this.client = client;
        this.driver = driver;
        this.deptAddress = deptAddress;
        this.arriveAddress = arriveAddress;
        this.cost = cost;
        this.car = car;
        this.creationDate = creationDate;
        this.distance = distance;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public User getClient() {
        return client;
    }

    public User getDriver() {
        return driver;
    }

    public String getDeptAddress() {
        return deptAddress;
    }

    public String getArriveAddress() {
        return arriveAddress;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public Car getCar() {
        return car;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getDistance() {
        return distance;
    }


    public static class Builder {

        private OrderStatus orderStatus;
        private User client;
        private User driver;
        private String destAddress;
        private String arriveAddress;
        private BigDecimal cost;
        private Car car;
        private LocalDateTime creationDate;
        private String distance;

        public Order.Builder setOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;

            return this;
        }
        public Order.Builder setClient(User client) {
            this.client = client;

            return this;
        }
        public Order.Builder setDriver(User driver) {
            this.driver = driver;

            return this;
        }
        public Order.Builder setDestAddress(String destAddress) {
            this.destAddress = destAddress;

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
        public Order.Builder setCar(Car car) {
            this.car = car;

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
            order.client = this.client;
            order.driver = this.driver;
            order.deptAddress = this.destAddress;
            order.arriveAddress = this.arriveAddress;
            order.cost = this.cost;
            order.car = this.car;
            order.creationDate = this.creationDate;
            order.distance = this.distance;

            return order;
        }

    }
    @Override
    public String toString() {
        return "Order{" +
                "orderStatus=" + orderStatus +
                ", client=" + client +
                ", driver=" + driver +
                ", destAddress=" + deptAddress +
                ", arriveAddress=" + arriveAddress +
                ", cost=" + cost +
                ", car=" + car +
                ", creationDate=" + creationDate +
                ", distance='" + distance + '\'' +
                '}';
    }
}
