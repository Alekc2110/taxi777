package my.fin.project.model.db.mapper;

import my.fin.project.model.entity.User;
import my.fin.project.model.entity.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @Test
    void getEntity() throws SQLException {
        UserMapper userMapper = new UserMapper();
        ResultSet rs = mock(ResultSet.class);
        User expected = new User.Builder()
                .setUserName("Alex")
                .setPassword("1111")
                .setPhoneNumber("+380675590555")
                .setRole(Role.CLIENT)
                .setEmail("aaa@mail.ua")
                .build();
        expected.setId(1L);

        lenient().when(rs.getString(eq("username"))).thenReturn("Alex");
        lenient().when(rs.getString(eq("password"))).thenReturn("1111");
        lenient().when(rs.getString(eq("phone_number"))).thenReturn("+380675590555");
        lenient().when(rs.getString(eq("role_name"))).thenReturn(Role.CLIENT.toString());
        lenient().when(rs.getString(eq("email"))).thenReturn("aaa@mail.ua");
        lenient().when(rs.getLong(eq("id"))).thenReturn(1L);
        User result = userMapper.getEntity(rs);

        Assertions.assertAll(()-> {
            assertEquals(expected.getUsername(), result.getUsername());
            assertEquals(expected.getPassword(), result.getPassword());
            assertEquals(expected.getPhoneNumber(), result.getPhoneNumber());
            assertEquals(expected.getEmail(), result.getEmail());
            assertEquals(expected.getId(), result.getId());
        });
    }
}