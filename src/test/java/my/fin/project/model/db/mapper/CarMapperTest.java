package my.fin.project.model.db.mapper;

import my.fin.project.model.entity.Car;
import my.fin.project.model.entity.Discount;
import my.fin.project.model.entity.enums.CarStatus;
import my.fin.project.model.entity.enums.CarType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class CarMapperTest {

    @Test
    void getEntity() throws SQLException {
        CarMapper carMapper = new CarMapper();
        ResultSet rs = mock(ResultSet.class);
        Car expected = new Car.Builder()
                .setCarNumber("KA2255AK")
                .setModel("skoda")
                .setColor("green")
                .setCarType(CarType.STANDARD)
                .setCarStatus(CarStatus.BOOKED)
                .setNumberSeats(4)
                .build();
        expected.setId(1L);

        lenient().when(rs.getLong("car_id")).thenReturn(1L);
        lenient().when(rs.getString("number")).thenReturn("KA2255AK");
        lenient().when(rs.getString("model")).thenReturn("skoda");
        lenient().when(rs.getString("color")).thenReturn("green");
        lenient().when(rs.getString("type")).thenReturn(CarType.STANDARD.toString());
        lenient().when(rs.getString("status")).thenReturn(CarStatus.BOOKED.toString());
        lenient().when(rs.getInt("num_seats")).thenReturn(4);

       Car result = carMapper.getEntity(rs);

        Assertions.assertAll(()-> {
            assertEquals(expected.getCarNumber(), result.getCarNumber());
            assertEquals(expected.getModel(), result.getModel());
            assertEquals(expected.getColor(), result.getColor());
            assertEquals(expected.getCarType(), result.getCarType());
            assertEquals(expected.getStatus(), result.getStatus());
            assertEquals(expected.getId(), result.getId());
        });
    }
}