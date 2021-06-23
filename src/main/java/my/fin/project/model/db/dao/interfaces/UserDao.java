package my.fin.project.model.db.dao.interfaces;

import my.fin.project.model.entity.Discount;
import my.fin.project.model.entity.User;
import my.fin.project.model.entity.enums.Role;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserDao extends Dao<User> {

    boolean checkUser(String phoneNumber, String password);

    Optional<User> getUser(String phoneNumber, String password);

    boolean saveUserRole(Long id, Role userRole);

    boolean isEmailExists(String email);

    boolean isPhoneNumberExists(String phoneNumber);

    Optional<User> findDriverByCarId(Long carId);

    Optional<Discount> getUserDiscount(Long clientId);

    boolean updateUserDiscount(User client, BigDecimal orderPrice);

    boolean saveUserDiscount(Discount discount);
}
