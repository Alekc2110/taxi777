package my.fin.project.model.db.dao.impl;

import my.fin.project.model.db.dao.interfaces.CarDao;
import my.fin.project.model.db.mapper.CarMapper;
import my.fin.project.model.db.mapper.Mapper;
import my.fin.project.model.entity.Car;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static my.fin.project.model.db.dao.constants.Queries.*;

public class JDBCCarDao implements CarDao {
    private static final Logger LOG = Logger.getLogger(JDBCCarDao.class);
    private Connection connection;

    public JDBCCarDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Car> getCarsByType(String carType) {
        List<Car> cars = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_CARS_BY_TYPE)) {
            ps.setString(1, carType);
            ResultSet rs = ps.executeQuery();
            Mapper<Car> carMapper = new CarMapper();
            LOG.debug("Executed query " + GET_CARS_BY_TYPE);
            while (rs.next()) {
                Car car = carMapper.getEntity(rs);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            LOG.error("SQLException when getCarsByType in JdbcCarDao", e);
            return cars;
        }
    }


    @Override
    public Optional<Car> getById(Long carId) {
        Car car = null;
        try (PreparedStatement ps = connection.prepareStatement(GET_CAR_BY_ID)) {
            ps.setLong(1, carId);
            ResultSet rs = ps.executeQuery();
            Mapper<Car> carMapper = new CarMapper();
            LOG.debug("Executed query " + GET_CAR_BY_ID);
            while (rs.next()) {
                car = carMapper.getEntity(rs);
            }
        } catch (SQLException e) {
            LOG.error("SQLException when getCarById in JdbcCarDao", e);
        }
        return Optional.ofNullable(car);
    }

    @Override
    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(GET_ALL_CARS);
            Mapper<Car> carMapper = new CarMapper();
            LOG.debug("Executed query " + GET_ALL_CARS);
            while (rs.next()) {
                Car car = carMapper.getEntity(rs);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            LOG.error("SQLException when findAll in JdbcCarDao", e);
            return cars;
        }
    }

    @Override
    public boolean update(Car car) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_CAR_STATUS)) {
            ps.setString(1, String.valueOf(car.getStatus()));
            ps.setLong(2, car.getId());
            if (ps.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            LOG.error("SQLException when update car status in JdbcCarDao", e);
        }
        return true;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
