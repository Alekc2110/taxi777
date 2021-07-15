package my.fin.project.model.db.mapper;

import my.fin.project.model.entity.Order;
import my.fin.project.model.entity.User;
import my.fin.project.model.entity.enums.OrderStatus;
import my.fin.project.model.entity.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class DriverMapperTest {

    @Test
    void getEntity() throws SQLException {
        DriverMapper driverMapper = new DriverMapper();
        ResultSet rs = mock(ResultSet.class);
        User expected = new User.Builder()
                .setUserName("Alex")
                .setPhoneNumber("+380675590555")
                .setRole(Role.DRIVER)
                .build();
        expected.setId(21L);

        lenient().when(rs.getString(eq("username"))).thenReturn("Alex");
        lenient().when(rs.getString(eq("phone_number"))).thenReturn("+380675590555");
        lenient().when(rs.getLong(eq("driver_id"))).thenReturn(21L);
        User result = driverMapper.getEntity(rs);

        Assertions.assertAll(()-> {
            assertEquals(expected.getUsername(), result.getUsername());
            assertEquals(expected.getPhoneNumber(), result.getPhoneNumber());
            assertEquals(expected.getId(), result.getId());
        });

    }
}