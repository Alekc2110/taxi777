package my.fin.project.model.service;

import my.fin.project.model.db.DaoFactory;
import my.fin.project.model.db.dao.interfaces.OrderDao;
import my.fin.project.model.db.dao.interfaces.UserDao;
import my.fin.project.model.entity.Discount;
import my.fin.project.model.entity.Order;
import my.fin.project.model.entity.User;
import my.fin.project.model.entity.enums.CarType;
import my.fin.project.model.entity.enums.Role;
import my.fin.project.model.entity.pojo.DistancePojo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderDao orderDao;
    @Mock
    private UserDao userDao;
    @Mock
    private DaoFactory factory;

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        setMock(factory);
        orderService = new OrderService();
    }

    @AfterEach
    public void resetSingleton() throws Exception {
        Field instance = DaoFactory.class.getDeclaredField("daoFactory");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    @DisplayName("should return DistancePojo object from google")
    void getDistanceFromGoogleService() {
        DistancePojo distanceFromGS = orderService.getDistanceFromGoogleService("Entuziastiv Street, 6", "Entuziastiv Street, 16");
        Assertions.assertAll(()-> {
            assertEquals(1,distanceFromGS.getOrigin_addresses().length);
            assertEquals(1,distanceFromGS.getDestination_addresses().length);
            assertEquals(1,distanceFromGS.getRows().length);
            assertEquals("OK",distanceFromGS.getStatus());
        });
    }

    @Test
    @DisplayName("should calculate order price without discount")
    void calculateOrderPrice() {
        User client = new User.Builder().
                setUserName("name").
                setPhoneNumber("+380507788999").
                setEmail("test@mail.com").
                setRole(Role.CLIENT).
                setPassword("1111").build();
        client.setId(1L);
        Integer distanceOrder = 15000;
        Discount discountZero = new Discount.Builder().setDiscountRate(0).setTotalSumRides(0).build();
        discountZero.setClientId(client.getId());
        when(factory.createUserDao()).thenReturn(userDao);
        when(userDao.getUserDiscount(client.getId())).thenReturn(Optional.of(discountZero));
        BigDecimal orderPrice = orderService.calculateOrderPrice(distanceOrder, CarType.STANDARD, client);
        Assertions.assertEquals(155, orderPrice.intValue());

    }

    @Test
    @DisplayName("should calculate order price with discount 5%")
    void calculateOrderPriceWithDisc5() {
        User client = new User.Builder().
                setUserName("name").
                setPhoneNumber("+380507788999").
                setEmail("test@mail.com").
                setRole(Role.CLIENT).
                setPassword("1111").build();
        client.setId(1L);
        Integer distanceOrder = 15000;
        Discount disc5Percent = new Discount.Builder().setDiscountRate(5).setTotalSumRides(5100).build();
        disc5Percent.setClientId(client.getId());
        when(factory.createUserDao()).thenReturn(userDao);
        when(userDao.getUserDiscount(client.getId())).thenReturn(Optional.of(disc5Percent));
        BigDecimal orderPrice = orderService.calculateOrderPrice(distanceOrder, CarType.STANDARD, client);
        Assertions.assertEquals(147, orderPrice.intValue());

    }

    @Test
    @DisplayName("should calculate order price with discount 10%")
    void calculateOrderPriceWithDisc10() {
        User client = new User.Builder().
                setUserName("name").
                setPhoneNumber("+380507788999").
                setEmail("test@mail.com").
                setRole(Role.CLIENT).
                setPassword("1111").build();
        client.setId(1L);
        Integer distanceOrder = 15000;
        Discount disc10Percent = new Discount.Builder().setDiscountRate(10).setTotalSumRides(10050).build();
        disc10Percent.setClientId(client.getId());
        when(factory.createUserDao()).thenReturn(userDao);
        when(userDao.getUserDiscount(client.getId())).thenReturn(Optional.of(disc10Percent));
        BigDecimal orderPrice = orderService.calculateOrderPrice(distanceOrder, CarType.STANDARD, client);
        Assertions.assertEquals(139, orderPrice.intValue());

    }

    @Test
    @DisplayName("should create order")
    void createOrder() {
        Long orderId = 21L;
        User client = new User.Builder().
                setUserName("name").
                setPhoneNumber("+380507788999").
                setEmail("test@mail.com").
                setRole(Role.CLIENT).
                setPassword("1111").build();
        client.setId(1L);
        BigDecimal orderPrice = new BigDecimal("200");
        when(factory.createOrderDao()).thenReturn(orderDao);
        when(orderDao.save(any(Order.class))).thenReturn(Optional.of(orderId));

        Long order = orderService.createOrder(client.getId(), 22L, "origAddr", "destAddr", orderPrice, "10500", 1L);

        Assertions.assertEquals(21L, order);

    }

    @Test
    void getAllOrdersCountByDriver() {
        when(factory.createOrderDao()).thenReturn(orderDao);
        when(orderDao.getCountOrders(22L)).thenReturn(10);

        int allOrdersCountByDriver = orderService.getAllOrdersCountByDriver(22L);

        Assertions.assertEquals(10, allOrdersCountByDriver);

    }

    @Test
    void getAllOrdersCount() {
        when(factory.createOrderDao()).thenReturn(orderDao);
        when(orderDao.getCountAllOrders()).thenReturn(50);

        int allOrdersCount = orderService.getAllOrdersCount();

        Assertions.assertEquals(50, allOrdersCount);

    }

    @Test
    void getAllOrdersByDriverId() {
        List<Order> orders = Arrays.asList(new Order.Builder().build(), new Order.Builder().build(), new Order.Builder().build(),
                new Order.Builder().build(), new Order.Builder().build());

        when(factory.createOrderDao()).thenReturn(orderDao);
        when(orderDao.getAllOrdersByDriverId(22L, 0, 5)).thenReturn(orders);

        List<Order> allOrdersByDriverId = orderService.getAllOrdersByDriverId(22L, 0, 5);

        Assertions.assertEquals(5, allOrdersByDriverId.size());

    }

    @Test
    void getAllOrders() {
        List<Order> orders = Arrays.asList(new Order.Builder().build(), new Order.Builder().build());

        when(factory.createOrderDao()).thenReturn(orderDao);
        when(orderDao.getAllOrders(0, 5)).thenReturn(orders);

        List<Order> allOrders = orderService.getAllOrders(0, 5);

        Assertions.assertEquals(2, allOrders.size());

    }

    @Test
    void getTimeWaiting() {
        Assertions.assertTrue(orderService.getTimeWaiting() > 0);
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