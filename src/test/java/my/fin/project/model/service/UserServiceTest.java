package my.fin.project.model.service;

import my.fin.project.exceptions.EmailExistException;
import my.fin.project.exceptions.EntityNotFoundException;
import my.fin.project.exceptions.PhoneNumExistException;
import my.fin.project.model.db.DaoFactory;
import my.fin.project.model.db.dao.interfaces.UserDao;
import my.fin.project.model.entity.Discount;
import my.fin.project.model.entity.User;
import my.fin.project.model.entity.enums.Role;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDao userDao;
    @Mock
    private DaoFactory factory;

    private UserService userService;

    @BeforeEach
    void setUp() {
        setMock(factory);
        userService = new UserService();
        when(factory.createUserDao()).thenReturn(userDao);
    }

    @AfterEach
    public void resetSingleton() throws Exception {
        Field instance = DaoFactory.class.getDeclaredField("daoFactory");
        instance.setAccessible(true);
        instance.set(null, null);
    }


    @Test
    @DisplayName("check if user saved in DB")
    void checkUserExists() {
        String password = "1111";
        String encPassword = "0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c";
        String phoneNumber = "+380504447788";
        when(userDao.checkUser(phoneNumber, encPassword)).thenReturn(true);
        boolean result = userService.checkUserExists(phoneNumber, password);

        Assertions.assertTrue(result);

    }

    @Test
    @DisplayName("should return user by phone and pass")
    void getUser() {
        String password = "1111";
        String encPassword = "0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c";
        String phoneNumber = "+380504447788";
        User user = new User.Builder()
                .setUserName("test")
                .setPhoneNumber("+380504447788")
                .setPassword("1111")
                .build();
        when(userDao.getUser(phoneNumber, encPassword)).thenReturn(Optional.of(user));

        User returnedUser = userService.getUser(phoneNumber, password);

        Assertions.assertEquals("test", returnedUser.getUsername());

    }

    @Test
    @DisplayName("should throw exception if user does not exists")
    void getUserIfNull() {
        String password = "1111";
        String encPassword = "0ffe1abd1a08215353c233d6e009613e95eec4253832a761af28ff37ac5a150c";
        String phoneNumber = "+380504447788";
        when(userDao.getUser(phoneNumber, encPassword)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> userService.getUser(phoneNumber, password));

    }

    @Test
    @DisplayName("should save new user")
    void saveUser() {
        Long newId = 1L;
        User client = new User.Builder().
                setUserName("name").
                setPhoneNumber("+380507788999").
                setEmail("test@mail.com").
                setRole(Role.CLIENT).
                setPassword("1111").build();
        Discount discount = new Discount.Builder().build();
        discount.setClientId(newId);

        when(userDao.save(client)).thenReturn(Optional.of(newId));
        when(userDao.isEmailExists(client.getEmail())).thenReturn(false);
        when(userDao.isPhoneNumberExists(client.getPhoneNumber())).thenReturn(false);

        when(userDao.saveUserDiscount(any(Discount.class))).thenReturn(true);
        when(userDao.saveUserRole(newId, client.getRole())).thenReturn(true);

        boolean saved = userService.saveUser(client);
        Assertions.assertTrue(saved);
    }

    @Test
    @DisplayName("should throw exception if email already exists in DB")
    void saveUserThrowEx() {
        User client = new User.Builder().
                setUserName("name").
                setPhoneNumber("+380507788999").
                setEmail("test@mail.com").
                setRole(Role.CLIENT).
                setPassword("1111").build();
        when(userDao.isEmailExists(client.getEmail())).thenReturn(true);

        Assertions.assertThrows(EmailExistException.class, ()-> userService.saveUser(client));
    }
    @Test
    @DisplayName("should throw exception if phoneNumber already exists in DB")
    void saveUserThrowEx2() {
        User client = new User.Builder().
                setUserName("name").
                setPhoneNumber("+380507788999").
                setEmail("test@mail.com").
                setRole(Role.CLIENT).
                setPassword("1111").build();

        when(userDao.isPhoneNumberExists(client.getPhoneNumber())).thenReturn(true);

        Assertions.assertThrows(PhoneNumExistException.class, ()-> userService.saveUser(client));
    }

    @Test
    @DisplayName("should return driver when making order")
    void getDriver() {
      Long carId = 55L;
        User driver = new User.Builder()
                .setUserName("driver")
                .setPhoneNumber("+380504447788")
                .setPassword("1111")
                .build();
        when(userDao.findDriverByCarId(carId)).thenReturn(Optional.of(driver));

        User returnedDriver = userService.getDriver(55L);

        Assertions.assertEquals("+380504447788", returnedDriver.getPhoneNumber());
    }

    @Test
    @DisplayName("should update total sum of client rides")
    void updateTotalSumRides() {
        BigDecimal orderPrice = new BigDecimal("255");
        User client = new User.Builder().
                setUserName("name").
                setPhoneNumber("+380507788999").
                setEmail("test@mail.com").
                setRole(Role.CLIENT).
                setPassword("1111").build();
        when(userDao.updateUserDiscount(client, orderPrice)).thenReturn(true);

        Assertions.assertTrue(userService.updateTotalSumRides(client, orderPrice));


    }

    private void setMock(DaoFactory mock) {
        Field instance;
        try {
            instance = DaoFactory.class.getDeclaredField("daoFactory");
            instance.setAccessible(true);
            instance.set(instance, mock);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}