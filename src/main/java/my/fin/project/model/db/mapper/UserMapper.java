package my.fin.project.model.db.mapper;

import my.fin.project.model.entity.User;
import my.fin.project.model.entity.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {
    @Override
    public User getEntity(ResultSet resultSet) throws SQLException {
        User user = new User.Builder()
                .setUserName(resultSet.getString("username"))
                .setPassword(resultSet.getString("password"))
                .setPhoneNumber(resultSet.getString("phone_number"))
                .setRole(Role.valueOf(resultSet.getString("role_name")))
                .setEmail(resultSet.getString("email"))
                .build();
        user.setId(resultSet.getLong("id"));

        return user;
    }
}
