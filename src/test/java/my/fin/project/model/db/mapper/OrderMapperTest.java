package my.fin.project.model.db.mapper;

import my.fin.project.model.entity.Order;
import my.fin.project.model.entity.enums.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class OrderMapperTest {

    @Test
    void getEntity() throws SQLException {
        OrderMapper orderMapper = new OrderMapper();
        ResultSet rs = mock(ResultSet.class);
        Order expected = new Order.Builder()
              .setOrderStatus(OrderStatus.CONFIRMED)
                .setClientId(1L)
                .setDriverId(25L)
                .setOriginAddress("origin address")
                .setArriveAddress("arrive address")
                .setCost(new BigDecimal(150))
                .setCarId(7L)
                .setCrDate(LocalDateTime.now())
                .setDistance("15000")
                .build();
        expected.setId(1L);

        lenient().when(rs.getString(eq("order_status"))).thenReturn(String.valueOf(OrderStatus.CONFIRMED));
        lenient().when(rs.getLong(eq("client_id"))).thenReturn(1L);
        lenient().when(rs.getLong(eq("driver_id"))).thenReturn(25L);
        lenient().when(rs.getString(eq("depart_address"))).thenReturn("origin address");
        lenient().when(rs.getString(eq("arr_address"))).thenReturn("arrive address");
        lenient().when(rs.getLong(eq("cost"))).thenReturn(150L);
        lenient().when(rs.getLong(eq("car_id"))).thenReturn(7L);
        lenient().when(rs.getDate(eq("creation_date"))).thenReturn(java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
        lenient().when(rs.getString(eq("distance"))).thenReturn("15000");
        lenient().when(rs.getLong(eq("order_id"))).thenReturn(1L);
        Order result = orderMapper.getEntity(rs);

        Assertions.assertAll(()-> {
            assertEquals(expected.getOrderStatus(), result.getOrderStatus());
            assertEquals(expected.getClientId(), result.getClientId());
            assertEquals(expected.getOriginAddress(), result.getOriginAddress());
            assertEquals(expected.getArriveAddress(), result.getArriveAddress());
            assertEquals(expected.getId(), result.getId());
        });

    }
}