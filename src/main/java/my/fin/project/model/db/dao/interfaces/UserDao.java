package my.fin.project.model.db.dao.interfaces;

import my.fin.project.model.entity.Discount;
import my.fin.project.model.entity.User;
import my.fin.project.model.entity.enums.Role;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserDao extends Dao<User> {

    boolean checkUser(String phoneNumber, String password);

    User findUserByEmail(String email);

    User getUser(String phoneNumber, String password);

    Role checkUserRole(User user);

    boolean saveUserRole(Long id, Role userRole);


    boolean isEmailExists(String email);

    boolean isPhoneNumberExists(String phoneNumber);

   User findDriverByCarId(Long carId);

    Discount getUserDiscount(Long clientId);

    boolean updateUserDiscount(User client, BigDecimal orderPrice);

}
