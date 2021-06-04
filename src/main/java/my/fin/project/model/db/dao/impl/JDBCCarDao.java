package my.fin.project.model.db.dao.impl;

import my.fin.project.model.db.dao.constants.Queries;
import my.fin.project.model.db.dao.interfaces.CarDao;
import my.fin.project.model.db.mapper.CarMapper;
import my.fin.project.model.db.mapper.Mapper;
import my.fin.project.model.entity.Car;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCarDao implements CarDao {
    private static final Logger LOG = Logger.getLogger(JDBCCarDao.class);
    private Connection connection;

    public JDBCCarDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Car> getCars(String carType) {
        List<Car> cars = new ArrayList<>();
        final String query = Queries.GET_ALL_CARS;
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            Mapper<Car> carMapper = new CarMapper();
            LOG.debug("Executed query" + Queries.GET_ALL_CARS);
            while (rs.next()) {
                Car car = carMapper.getEntity(rs);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            LOG.error("SQLException occurred in JdbcCarDao" , e);
            return null;
        }
    }

    @Override
    public Long save(Car entity) {
        return null;
    }

    @Override
    public Car getById(Long id) {
        return null;
    }

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public boolean update(Car car) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void close()  {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
