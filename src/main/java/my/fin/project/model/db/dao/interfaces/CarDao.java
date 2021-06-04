package my.fin.project.model.db.dao.interfaces;

import my.fin.project.model.entity.Car;

import java.util.List;

public interface CarDao extends Dao<Car> {

    List<Car> getCars(String carType);

}
