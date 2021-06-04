package my.fin.project.model.entity;

import my.fin.project.model.entity.enums.CarStatus;


public class Car extends Entity {

    private String carNumber;
    private String model;
    private String color;
    private String carType;
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

    public String getCarType() {
        return carType;
    }

    public CarStatus getStatus() {
        return status;
    }

    public int getSeats() {
        return seats;
    }

    public static class Builder {
        private String carNumber;
        private String model;
        private String color;
        private String carType;
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

        public Car.Builder setCarType(String carType) {
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
}
