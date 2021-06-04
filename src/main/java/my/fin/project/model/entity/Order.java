package my.fin.project.model.entity;

import my.fin.project.model.entity.enums.OrderStatus;

import java.time.LocalDate;

public class Order extends Entity {

    private OrderStatus orderStatus;
    private User client;
    private User driver;
    private Address destAddress;
    private Address arriveAddress;
    private int cost;
    private int costWithDiscount;
    private Car car;
    private LocalDate creationDate;

    public Order() {
    }

    public Order(OrderStatus orderStatus, User client, User driver,
                 Address destAddress, Address arriveAddress, int cost,
                 int costWithDiscount, Car car, LocalDate creationDate) {
        this.orderStatus = orderStatus;
        this.client = client;
        this.driver = driver;
        this.destAddress = destAddress;
        this.arriveAddress = arriveAddress;
        this.cost = cost;
        this.costWithDiscount = costWithDiscount;
        this.car = car;
        this.creationDate = creationDate;
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

    public Address getDestAddress() {
        return destAddress;
    }

    public Address getArriveAddress() {
        return arriveAddress;
    }

    public int getCost() {
        return cost;
    }

    public int getCostWithDiscount() {
        return costWithDiscount;
    }

    public Car getCar() {
        return car;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public static class Builder {

        private OrderStatus orderStatus;
        private User client;
        private User driver;
        private Address destAddress;
        private Address arriveAddress;
        private int cost;
        private int costWithDiscount;
        private Car car;
        private LocalDate creationDate;

        public Order.Builder setOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;

            return this;
        }

        public Order.Builder setClient(User client) {
            this.client = client;

            return this;
        }

        public Order.Builder setOrder(User driver) {
            this.driver = driver;

            return this;
        }

        public Order.Builder setDestAddress(Address destAddress) {
            this.destAddress = destAddress;

            return this;
        }

        public Order.Builder setArriveAddress(Address arriveAddress) {
            this.arriveAddress = arriveAddress;

            return this;
        }

        public Order.Builder setCost(int cost) {
            this.cost = cost;

            return this;
        }

        public Order.Builder setCostWithDiscount(int costWithDiscount) {
            this.costWithDiscount = costWithDiscount;

            return this;
        }

        public Order.Builder setCar(Car car) {
            this.car = car;

            return this;
        }
        public Order.Builder setCrDate(LocalDate creationDate) {
            this.creationDate = creationDate;

            return this;
        }

        public Order build() {
            Order order = new Order();
            order.orderStatus = this.orderStatus;
            order.client = this.client;
            order.driver = this.driver;
            order.destAddress = this.destAddress;
            order.arriveAddress = this.arriveAddress;
            order.cost = this.cost;
            order.costWithDiscount = this.costWithDiscount;
            order.car = this.car;
            order.creationDate = this.creationDate;

            return order;
        }

    }

    @Override
    public String toString() {
        return "Order{" +
                "orderStatus=" + orderStatus +
                ", client=" + client +
                ", driver=" + driver +
                ", destAddress=" + destAddress +
                ", arriveAddress=" + arriveAddress +
                ", cost=" + cost +
                ", costWithDiscount=" + costWithDiscount +
                ", car=" + car +
                ", creationDate=" + creationDate +
                '}';
    }
}
