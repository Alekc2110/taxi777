package my.fin.project.model.db.mapper;

import my.fin.project.model.entity.Car;
import my.fin.project.model.entity.enums.CarStatus;
import my.fin.project.model.entity.enums.CarType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements Mapper<Car>{
    @Override
    public Car getEntity(ResultSet resultSet) throws SQLException {
        Car car = new Car.Builder()
                .setCarNumber(resultSet.getString("number"))
                .setModel(resultSet.getString("model"))
                .setColor(resultSet.getString("color"))
                .setCarType(CarType.valueOf(resultSet.getString("type")))
                .setCarStatus(CarStatus.valueOf(resultSet.getString("status")))
                .setNumberSeats(resultSet.getInt("num_seats"))
                .build();
        car.setId(resultSet.getLong("car_id"));
        return car;
    }
}
