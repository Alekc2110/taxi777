package my.fin.project.model.db.dao.interfaces;

import my.fin.project.model.entity.Order;
import my.fin.project.model.entity.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public interface OrderDao extends Dao<Order> {

    List<Order> getAllOrdersByUserId(int userId, int row, int limit);

    List<Order> getOrderByDate(LocalDate date);

    boolean updateOrderStatus(int orderId, OrderStatus orderStatus);


}
