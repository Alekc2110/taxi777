package my.fin.project.model.db.mapper;

import my.fin.project.model.entity.Order;
import my.fin.project.model.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements Mapper<Order> {
    @Override
    public Order getEntity(ResultSet resultSet) throws SQLException {
        Order order = new Order.Builder()
                .setOrderStatus(OrderStatus.valueOf(resultSet.getString("order_status")))
                .setClientId(resultSet.getLong("client_id"))
                .setDriverId(resultSet.getLong("driver_id"))
                .setOriginAddress(resultSet.getString("depart_address"))
                .setArriveAddress(resultSet.getString("arr_address"))
                .setCost(BigDecimal.valueOf(resultSet.getLong("cost")))
                .setCarId(resultSet.getLong("car_id"))
                .setCrDate(new java.sql.Timestamp(resultSet.getDate("creation_date").getTime()).toLocalDateTime())
                .setDistance(resultSet.getString("distance"))
                .build();
        order.setId(resultSet.getLong("order_id"));
        return order;
    }
}
