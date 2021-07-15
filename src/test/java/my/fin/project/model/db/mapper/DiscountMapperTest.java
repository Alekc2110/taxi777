package my.fin.project.model.db.mapper;

import my.fin.project.model.entity.Discount;
import my.fin.project.model.entity.User;
import my.fin.project.model.entity.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class DiscountMapperTest {

    @Test
    void getEntity() throws SQLException {
        DiscountMapper discountMapper = new DiscountMapper();
        ResultSet rs = mock(ResultSet.class);
        Discount expected = new Discount.Builder()
                .setClientId(7L)
                .setDiscountRate(5)
                .setTotalSumRides(5000)
                .build();
        expected.setId(1L);

        lenient().when(rs.getLong("dis_id")).thenReturn(1L);
        lenient().when(rs.getLong("user_id")).thenReturn(7L);
        lenient().when(rs.getInt("discount_rate")).thenReturn(5);
        lenient().when(rs.getInt("total_sum")).thenReturn(5000);

        Discount result = discountMapper.getEntity(rs);

        Assertions.assertAll(()-> {
            assertEquals(expected.getClientId(), result.getClientId());
            assertEquals(expected.getDiscountRate(), result.getDiscountRate());
            assertEquals(expected.getTotalSumRides(), result.getTotalSumRides());
            assertEquals(expected.getId(), result.getId());
        });


    }
}