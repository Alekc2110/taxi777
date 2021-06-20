package my.fin.project.model.entity;

import my.fin.project.model.entity.enums.CarStatus;
import my.fin.project.model.entity.enums.CarType;

import java.util.Objects;


public class Car extends Entity {

    private String carNumber;
    private String model;
    private String color;
    private CarType carType;
    private CarStatus status;
    private int seats;

    public Car() {
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public CarType getCarType() {
        return carType;
    }

    public CarStatus getStatus() {
        return status;
    }

    public int getSeats() {
        return seats;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public static class Builder {
        private String carNumber;
        private String model;
        private String color;
        private CarType carType;
        private CarStatus status;
        private int seats;

        public Car.Builder setCarNumber(String carNumber) {
            this.carNumber = carNumber;

            return this;
        }

        public Car.Builder setModel(String model) {
            this.model = model;

            return this;
        }

        public Car.Builder setColor(String color) {
            this.color = color;

            return this;
        }

        public Car.Builder setCarType(CarType carType) {
            this.carType = carType;

            return this;
        }
        public Car.Builder setCarStatus(CarStatus status) {
            this.status = status;

            return this;
        }
        public Car.Builder setNumberSeats(int seats) {
            this.seats = seats;

            return this;
        }

        public Car build() {
           Car car = new Car();
            car.carNumber = this.carNumber;
            car.model = this.model;
            car.color = this.color;
            car.carType = this.carType;
            car.status = this.status;
            car.seats = this.seats;
            return car;
        }

    }

    @Override
    public String toString() {
        return "Car{" +
                "carNumber='" + carNumber + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", carType='" + carType + '\'' +
                ", status=" + status +
                ", seats=" + seats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return seats == car.seats &&
                Objects.equals(carNumber, car.carNumber) &&
                Objects.equals(model, car.model) &&
                Objects.equals(color, car.color) &&
                Objects.equals(carType, car.carType) &&
                status == car.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(carNumber, model, color, carType, status, seats);
    }
}
