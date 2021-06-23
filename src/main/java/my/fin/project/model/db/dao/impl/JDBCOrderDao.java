package my.fin.project.model.db.dao.impl;

import my.fin.project.model.db.dao.interfaces.OrderDao;
import my.fin.project.model.db.mapper.Mapper;
import my.fin.project.model.db.mapper.OrderMapper;
import my.fin.project.model.entity.Order;
import my.fin.project.model.entity.enums.OrderStatus;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static my.fin.project.model.db.dao.constants.Queries.*;


public class JDBCOrderDao implements OrderDao {
    private static final Logger LOG = Logger.getLogger(JDBCOrderDao.class);
    private Connection connection;

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Optional<Long> save(Order order) {
        ResultSet generatedKey = null;
        try (PreparedStatement ps = connection.prepareStatement(SAVE_ORDER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, String.valueOf(order.getOrderStatus()));
            ps.setLong(2, order.getClientId());
            ps.setLong(3, order.getDriverId());
            ps.setString(4, order.getOriginAddress());
            ps.setString(5, order.getArriveAddress());
            ps.setBigDecimal(6, order.getCost());
            ps.setLong(7, order.getCarId());
            ps.setTimestamp(8, Timestamp.valueOf(order.getCreationDate()));
            ps.setString(9, order.getDistance());
            LOG.debug("Executed query: " + SAVE_ORDER);
            if (ps.executeUpdate() != 1) {
                return Optional.empty();
            }
            generatedKey = ps.getGeneratedKeys();
            if (generatedKey.next()) {
                Long id = generatedKey.getLong(1);
                return Optional.of(id);
            }
        } catch (SQLException e) {
            LOG.info("SQLException in 'saveOrder' OrderDao: " + e);
            return Optional.empty();
        } finally {
            try {
                if (generatedKey != null) generatedKey.close();
            } catch (SQLException e) {
                LOG.info("SQLException when closing ResultSet in 'saveOrder': " + e);
            }
        }
        return Optional.empty();
    }

    @Override
    public int getCountOrders(Long driverId) {
        int countOrders = 0;
        try (PreparedStatement ps = connection.prepareStatement(COUNT_DRIVER_ORDERS)) {
            ps.setLong(1, driverId);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query: " + COUNT_DRIVER_ORDERS);
            if (rs.next()) {
                LOG.debug("check if rs has next");
                countOrders = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error("SQLException in 'getCountOrders' OrderDao", e);
        }
        return countOrders;
    }

    @Override
    public int getCountAllOrders() {
        int countOrders = 0;
        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery(COUNT_ALL_ORDERS);
            LOG.debug("Executed query: " + COUNT_ALL_ORDERS);
            if (rs.next()) {
                LOG.debug("check if rs has next");
                countOrders = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOG.error("SQLException in 'getCountAllOrders' OrderDao", e);
        }
        return countOrders;
    }


    @Override
    public List<Order> getAllOrdersByDriverId(Long driverId, int row, int limit) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ORDERS_DRIVER_LIMIT)) {
            ps.setLong(1, driverId);
            ps.setInt(2, row);
            ps.setInt(3, limit);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query: " + GET_ORDERS_DRIVER_LIMIT);
            Mapper<Order> orderMapper = new OrderMapper();
            while (rs.next()) {
                LOG.debug("check if rs has next");
                Order order = orderMapper.getEntity(rs);
                orders.add(order);
            }

            return orders;
        } catch (SQLException e) {
            LOG.error("SQLException in 'getAllOrdersByDriverId' in JdbcOrderDao", e);
            return orders;
        }
    }

    @Override
    public List<Order> getAllOrders(int row, int limit) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_ORDERS)) {
            ps.setInt(1, row);
            ps.setInt(2, limit);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query: " + GET_ALL_ORDERS);
            Mapper<Order> orderMapper = new OrderMapper();
            while (rs.next()) {
                LOG.debug("check if rs has next");
                Order order = orderMapper.getEntity(rs);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            LOG.error("SQLException in 'getAllOrders' in JdbcOrderDao", e);
            return orders;
        }
    }


    @Override
    public Optional<Order> getById(Long id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public boolean update(Order order) {
        return false;
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
