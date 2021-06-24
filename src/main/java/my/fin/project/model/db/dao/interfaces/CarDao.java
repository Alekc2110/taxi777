package my.fin.project.model.db.dao.interfaces;

import my.fin.project.model.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarDao extends AutoCloseable {

    List<Car> getCarsByType(String carType);

    Optional<Car> getById(Long carId);

    List<Car> findAll();

    boolean update(Car car);

    void close();

}
