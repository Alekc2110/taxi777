package my.fin.project.model.db.mapper;

import my.fin.project.model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements Mapper<Order> {
    @Override
    public Order getEntity(ResultSet resultSet) throws SQLException {
        Order order = new Order();

        return order;
    }
}
