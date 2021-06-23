package my.fin.project.model.service;

import my.fin.project.exceptions.EntityNotFoundException;
import my.fin.project.model.db.DaoFactory;
import my.fin.project.model.db.dao.interfaces.CarDao;
import my.fin.project.model.entity.Car;
import my.fin.project.model.entity.enums.CarStatus;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CarService {
    private static final Logger LOG = Logger.getLogger(CarService.class);

    private DaoFactory factory = DaoFactory.getInstance();

    public List<Car> getCars(String carType) {
        try (CarDao dao = factory.createCarDao()) {
            LOG.info(String.format("returned list cars of type %s ", carType));
            return dao.getCarsByType(carType);
        }
    }

    public boolean updateCarStatus(Car car) {
        try (CarDao dao = factory.createCarDao()) {
            LOG.debug("update car status in CarDao: " + car);
            return dao.update(car);
        }
    }

    public List<Car> getFreeCarsByNumSeats(int reqSeats) {
        try (CarDao dao = factory.createCarDao()) {
            LOG.debug("get free cars available in CarDao with num seats: " + reqSeats);
            return dao.findAll().stream().filter(car -> car.getSeats() == reqSeats)
                    .filter(car -> car.getStatus().equals(CarStatus.FREE)).collect(Collectors.toList());

        }
    }

    public Car getCarById(Long carId) {
        try (CarDao dao = factory.createCarDao()) {
            LOG.debug("find car by carId: " + carId);
            Optional<Car> carOpt = dao.getById(carId);
            return carOpt.orElseThrow(EntityNotFoundException::new);
        }
    }
}
