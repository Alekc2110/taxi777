package my.fin.project.model.db.dao.impl;

import my.fin.project.model.db.dao.interfaces.OrderDao;
import my.fin.project.model.entity.Order;
import my.fin.project.model.entity.enums.OrderStatus;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class JDBCOrderDao implements OrderDao {
    private static final Logger LOG = Logger.getLogger(JDBCOrderDao.class);
    private Connection connection;

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
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
    public Long save(Order entity) {
        return null;
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
