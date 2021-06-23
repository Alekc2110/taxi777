package my.fin.project.model.service;

import my.fin.project.exceptions.EmailExistException;
import my.fin.project.exceptions.EntityNotFoundException;
import my.fin.project.exceptions.EntitySaveDaoException;
import my.fin.project.exceptions.PhoneNumExistException;
import my.fin.project.model.db.DaoFactory;
import my.fin.project.model.db.dao.interfaces.UserDao;
import my.fin.project.model.entity.Discount;
import my.fin.project.model.entity.User;
import my.fin.project.utils.SimplePasswordEncoder;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Optional;


public class UserService {
    private static final Logger LOG = Logger.getLogger(UserService.class);

    private DaoFactory factory = DaoFactory.getInstance();
    private SimplePasswordEncoder passwordEncoder;

    public UserService() {
        passwordEncoder = new SimplePasswordEncoder();
    }

    public boolean checkUserExists(String phoneNumber, String password) {
        try (UserDao dao = factory.createUserDao()) {
            LOG.debug("check if user exists UserDao");
            return dao.checkUser(phoneNumber, passwordEncoder.encode(password));
        }

    }

    public User getUser(String phoneNumber, String password) {
        try (UserDao dao = factory.createUserDao()) {
            LOG.debug("getUser UserDao");
            Optional<User> userOptional = dao.getUser(phoneNumber, passwordEncoder.encode(password));
            return userOptional.orElseThrow(EntityNotFoundException::new);
        }
    }

    public boolean saveUser(User client) {
        try (UserDao dao = factory.createUserDao()) {
            LOG.debug("created UserDao");
            boolean isTakenEmail = dao.isEmailExists(client.getEmail());
            boolean isTakenPhoneNumber = dao.isPhoneNumberExists(client.getPhoneNumber());
            if (isTakenEmail) {
                LOG.debug("e-mail is already taken, exception occurred");
                throw new EmailExistException("This email is already registered in DB");
            }
            if (isTakenPhoneNumber) {
                LOG.debug("PhoneNumber is already taken, exception occurred");
                throw new PhoneNumExistException("This phone number is already registered in DB");
            }
            client.setPassword(passwordEncoder.encode(client.getPassword()));
            Optional<Long> userIdOpt = dao.save(client);
            Long userId = userIdOpt.orElseThrow(EntitySaveDaoException::new);
            boolean isDiscCreated = createClientDiscount(userId, dao);
            boolean isSavedRole = dao.saveUserRole(userId, client.getRole());

            return isDiscCreated && isSavedRole;
        }
    }

    public User getDriver(Long carId) {
        try (UserDao dao = factory.createUserDao()) {
            Optional<User> driverOpt = dao.findDriverByCarId(carId);
            return driverOpt.orElseThrow(EntityNotFoundException::new);
        }
    }

    public boolean updateTotalSumRides(User client, BigDecimal orderPrice) {
        try (UserDao dao = factory.createUserDao()) {
            return dao.updateUserDiscount(client, orderPrice);
        }
    }

    private boolean createClientDiscount(Long clientId, UserDao dao) {
        Discount discount = new Discount.Builder()
                .setClientId(clientId)
                .setDiscountRate(0)
                .setTotalSumRides(0)
                .build();
        return dao.saveUserDiscount(discount);
    }
}
