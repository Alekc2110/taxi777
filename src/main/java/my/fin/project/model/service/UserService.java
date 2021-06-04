package my.fin.project.model.service;

import my.fin.project.controller.command.common.RegistrationCommand;
import my.fin.project.exceptions.EmailExistException;
import my.fin.project.exceptions.PhoneNumExistException;
import my.fin.project.model.db.DaoFactory;
import my.fin.project.model.db.dao.interfaces.UserDao;
import my.fin.project.model.entity.User;
import my.fin.project.model.entity.enums.Role;
import my.fin.project.utils.SimplePasswordEncoder;
import org.apache.log4j.Logger;


public class UserService {
    private final Logger LOG = Logger.getLogger(UserService.class);

    private DaoFactory factory = DaoFactory.getInstance();
    private SimplePasswordEncoder passwordEncoder;

    public UserService() {
        passwordEncoder = new SimplePasswordEncoder();
    }

    public boolean checkUserExists(String phoneNumber, String password) {
        try (UserDao dao = factory.createUserDao()) {
            return dao.checkUser(phoneNumber, passwordEncoder.encode(password));
        }

    }

    public User getUser(String phoneNumber, String password) {
        try (UserDao dao = factory.createUserDao()) {
            return dao.getUser(phoneNumber, passwordEncoder.encode(password));
        }
    }

    public Long saveUser(User client) {
        try (UserDao dao = factory.createUserDao()) {
            LOG.debug("created UserDao");
            boolean isTakenEmail = dao.isEmailExists(client.getEmail());
            boolean isTakenPhoneNumber = dao.isPhoneNumberExists(client.getPhoneNumber());
            if (isTakenEmail){
                LOG.debug("e-mail is already taken, exception occurred");
                throw new EmailExistException("This email is already registered in DB");
            }
            if (isTakenPhoneNumber){
                LOG.debug("PhoneNumber is already taken, exception occurred");
                throw new PhoneNumExistException("This phone number is already registered in DB");
            }
            client.setPassword(passwordEncoder.encode(client.getPassword()));
            Long userId = dao.save(client);
            saveUserRole(userId, client.getRole());
            return userId;
        }
    }

    private void saveUserRole(Long id, Role userRole) {
        try (UserDao dao = factory.createUserDao()) {
            if (id != -1) {
               dao.saveUserRole(id, userRole);
            }
        }
    }
}
