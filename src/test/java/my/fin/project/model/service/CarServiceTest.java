package my.fin.project.model.service;

import my.fin.project.model.db.DaoFactory;
import my.fin.project.model.db.dao.interfaces.CarDao;
import my.fin.project.model.db.dao.interfaces.OrderDao;
import my.fin.project.model.db.dao.interfaces.UserDao;
import my.fin.project.model.entity.Car;
import my.fin.project.model.entity.enums.CarStatus;
import my.fin.project.model.entity.enums.CarType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {
    @Mock
    private CarDao carDao;
    @Mock
    private DaoFactory factory;

    private CarService carService;

    @BeforeEach
    void setUp() {
        setMock(factory);
        carService = new CarService();
        when(factory.createCarDao()).thenReturn(carDao);
    }

    @AfterEach
    public void resetSingleton() throws Exception {
        Field instance = DaoFactory.class.getDeclaredField("daoFactory");
        instance.setAccessible(true);
        instance.set(null, null);
    }


    @Test
    @DisplayName("should return list of cars")
    void getCars() {
        List<Car> cars = Arrays.asList(new Car.Builder().setCarType(CarType.STANDARD).build(), new Car.Builder().setCarType(CarType.STANDARD).build());
        when(carDao.getCarsByType(CarType.STANDARD.toString())).thenReturn(cars);
        Assertions.assertEquals(2, carService.getCars(CarType.STANDARD.toString()).size());
    }

    @Test
    @DisplayName("should update car status")
    void updateCarStatus() {
        Car car = new Car.Builder()
                .setCarStatus(CarStatus.FREE)
                .setCarType(CarType.STANDARD)
                .setNumberSeats(4)
                .build();

        when(carDao.update(car)).thenReturn(true);

        Assertions.assertTrue(carService.updateCarStatus(car));
    }

    @Test
    @DisplayName("should return list of free cars, searching by number of seats")
    void getFreeCarsByNumSeats() {
        List<Car> cars = Arrays.asList(new Car.Builder().setCarStatus(CarStatus.FREE).setNumberSeats(4).setCarType(CarType.STANDARD).build(),
                new Car.Builder().setCarStatus(CarStatus.BOOKED).setNumberSeats(4).setCarType(CarType.STANDARD).build());
        when(carDao.findAll()).thenReturn(cars);

        Assertions.assertEquals(1, carService.getFreeCarsByNumSeats(4).size());

    }

    @Test
    @DisplayName("should return car by carId")
    void getCarById() {
        Car car = new Car.Builder()
                .setCarStatus(CarStatus.FREE)
                .setCarType(CarType.STANDARD)
                .setNumberSeats(4)
                .build();
        car.setId(25L);
        when(carDao.getById(25L)).thenReturn(Optional.of(car));

        Assertions.assertAll(()-> {
           assertEquals( CarType.STANDARD, carService.getCarById(25L).getCarType());
           assertEquals(CarStatus.FREE , carService.getCarById(25L).getStatus());
           assertEquals(4, carService.getCarById(25L).getSeats());
           assertEquals(25L, carService.getCarById(25L).getId());
        });

    }

    private void setMock(DaoFactory mock) {
        Field instance;
        try {
            instance = DaoFactory.class.getDeclaredField("daoFactory");
            instance.setAccessible(true);
            instance.set(instance, mock);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}