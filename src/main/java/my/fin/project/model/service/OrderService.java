package my.fin.project.model.service;

import com.google.gson.Gson;
import my.fin.project.model.db.DaoFactory;
import my.fin.project.model.db.dao.interfaces.OrderDao;
import my.fin.project.model.db.dao.interfaces.UserDao;
import my.fin.project.model.entity.Discount;
import my.fin.project.model.entity.Order;
import my.fin.project.model.entity.User;
import my.fin.project.model.entity.enums.CarType;
import my.fin.project.model.entity.enums.OrderStatus;
import my.fin.project.model.entity.pojo.DistancePojo;
import my.fin.project.utils.PriceUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

public class OrderService {
    private static final Logger LOG = Logger.getLogger(OrderService.class);
    private DaoFactory factory = DaoFactory.getInstance();

    private static final String REQUEST_URL = "request.url";
    private static final String MODE = "mode";
    private static final String KEY = "key";
    private static final String CITY = "city";
    private static final String ENCODING = "UTF-8";

    public DistancePojo getDistanceFromGoogleService(String departAddress, String arrAddress) {
        departAddress = departAddress.replaceAll(" ", "");
        arrAddress = arrAddress.replaceAll(" ", "");
        HttpURLConnection conn;
        String line;
        StringBuilder outputString = new StringBuilder();
        Properties prop = new Properties();
        String propFileName = "googleRequest.properties";
        try (InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(propFileName)) {
            LOG.debug("InputStream: " + inputStream);

            if (inputStream != null) {
                LOG.debug("inputStream != null");
                prop.load(inputStream);
            } else {
                LOG.debug("property file '" + propFileName + "' not found in the classpath");
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            String propUrl = prop.getProperty(REQUEST_URL);
            String propMode = prop.getProperty(MODE);
            String propKey = prop.getProperty(KEY);
            String propCity = prop.getProperty(CITY);
            departAddress = URLEncoder.encode(departAddress, ENCODING);
            arrAddress = URLEncoder.encode(arrAddress, ENCODING);
            URL url = new URL(propUrl + "origins=" + departAddress + propCity + "&destinations=" + arrAddress + propCity + "&mode=" + propMode + "&key=" + propKey);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                outputString.append(line);
            }

        } catch (IOException e) {
            LOG.error("IOException in OrderService: " + e);
        }


        return new Gson().fromJson(outputString.toString(), DistancePojo.class);
    }

    public BigDecimal calculateOrderPrice(Integer distance, CarType carType, User client) {
        try (UserDao dao = factory.createUserDao()) {
            Discount userDiscount = dao.getUserDiscount(client.getId());
            if (userDiscount.getId() != -1) {
                return PriceUtils.getOrderPriceDisc(distance, carType, userDiscount.getDiscountRate());
            }
            return PriceUtils.getOrderPrice(distance, carType);
        }
    }

    public Long createOrder(Long clientId, Long driverId, String originAddress, String destAddress, BigDecimal orderPrice, String distance, Long carId) {
        Order order = new Order.Builder()
                .setOrderStatus(OrderStatus.CONFIRMED)
                .setClientId(clientId)
                .setDriverId(driverId)
                .setOriginAddress(originAddress)
                .setArriveAddress(destAddress)
                .setCarId(carId)
                .setCost(orderPrice)
                .setCrDate(LocalDateTime.now())
                .setDistance(distance)
                .build();
        try (OrderDao dao = factory.createOrderDao()) {
            return dao.save(order);
        }
    }

    public int getAllOrdersCountByDriver(Long driverId) {
        try (OrderDao dao = factory.createOrderDao()) {
            return dao.getCountOrders(driverId);
        }
    }

    public int getAllOrdersCount(){
        try (OrderDao dao = factory.createOrderDao()) {
            return dao.getCountAllOrders();
        }
    }

    public List<Order> getAllOrdersByDriverId(Long driverId, int row, int limit) {
        try (OrderDao dao = factory.createOrderDao()) {
            return dao.getAllOrdersByDriverId(driverId, row, limit);
        }

    }

    public List<Order> getAllOrders(int row, int limit) {
        try (OrderDao dao = factory.createOrderDao()) {
            return dao.getAllOrders(row, limit);
        }

    }


    public int getTimeWaiting() {
        return (int) (Math.random() * 10);
    }


}

