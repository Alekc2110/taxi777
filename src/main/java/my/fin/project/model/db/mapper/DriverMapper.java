package my.fin.project.model.db.mapper;

import my.fin.project.model.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverMapper implements Mapper<User> {
    @Override
    public User getEntity(ResultSet resultSet) throws SQLException {
        User user = new User.Builder()
                .setUserName(resultSet.getString("username"))
                .setPhoneNumber(resultSet.getString("phone_number"))
                .build();
        user.setId(resultSet.getLong("driver_id"));
        return user;
    }
}
