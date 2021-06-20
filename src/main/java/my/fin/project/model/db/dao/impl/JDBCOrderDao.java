package my.fin.project.model.db.dao.impl;

import my.fin.project.model.db.dao.interfaces.OrderDao;
import my.fin.project.model.entity.Order;
import my.fin.project.model.entity.enums.OrderStatus;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static my.fin.project.model.db.dao.constants.Queries.SAVE_ORDER;


public class JDBCOrderDao implements OrderDao {
    private static final Logger LOG = Logger.getLogger(JDBCOrderDao.class);
    private Connection connection;

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Long save(Order order) {
        ResultSet generatedKey = null;
        Long id = null;
        try (PreparedStatement ps = connection.prepareStatement(SAVE_ORDER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, String.valueOf(order.getOrderStatus()));
            ps.setLong(2, order.getClient().getId());
            ps.setLong(3, order.getDriver().getId());
            ps.setString(4, order.getDeptAddress());
            ps.setString(5, order.getArriveAddress());
            ps.setBigDecimal(6, order.getCost());
            ps.setLong(7, order.getCar().getId());
            ps.setTimestamp(8, Timestamp.valueOf(order.getCreationDate()));
            ps.setString(9, order.getDistance());
            if (ps.executeUpdate() != 1) {
                return -1L;
            }
            generatedKey = ps.getGeneratedKeys();
            if (generatedKey.next()) {
                id = generatedKey.getLong(1);
                return id;
            }
        } catch (SQLException e) {
            LOG.info("SQLException in 'saveOrder' OrderDao: " + e);
            return -1L;
        } finally {
            try {
                if (generatedKey != null) generatedKey.close();
            } catch (SQLException e) {
                LOG.info("SQLException when closing ResultSet in 'saveOrder': " + e);
            }
        }
        return id;
    }

    @Override
    public List<Order> getAllOrdersByUserId(int userId, int row, int limit) {
        return null;
    }

    @Override
    public List<Order> getOrderByDate(LocalDate date) {
        return null;
    }

    @Override
    public boolean updateOrderStatus(int orderId, OrderStatus orderStatus) {
        return false;
    }

    @Override
    public Order getById(Long id) {
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
    public boolean delete(Long id) {
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
