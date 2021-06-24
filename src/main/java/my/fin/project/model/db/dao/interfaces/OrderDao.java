package my.fin.project.model.db.dao.interfaces;

import my.fin.project.model.entity.Order;
import my.fin.project.model.entity.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderDao extends AutoCloseable {

    Optional<Long> save(Order order);

    int getCountOrders(Long driverId);

    List<Order> getAllOrdersByDriverId(Long driverId, int row, int limit);

    List<Order> getAllOrders(int row, int limit);

    int getCountAllOrders();

    void close();

}
