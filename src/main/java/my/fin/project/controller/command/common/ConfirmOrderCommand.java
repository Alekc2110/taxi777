package my.fin.project.controller.command.common;

import my.fin.project.controller.command.Command;
import my.fin.project.exceptions.EntityNotFoundException;
import my.fin.project.exceptions.EntitySaveDaoException;
import my.fin.project.model.db.dao.constants.Fields;
import my.fin.project.model.entity.Car;
import my.fin.project.model.entity.User;
import my.fin.project.model.entity.enums.CarStatus;
import my.fin.project.model.service.CarService;
import my.fin.project.model.service.OrderService;
import my.fin.project.model.service.UserService;
import my.fin.project.utils.CookiesUtils;
import my.fin.project.utils.LoginUserUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;


import static my.fin.project.controller.command.Path.*;

public class ConfirmOrderCommand extends Command {
    private final Logger LOG = Logger.getLogger(ConfirmOrderCommand.class);
    private OrderService orderService;
    private CarService carService;
    private UserService userService;

    public ConfirmOrderCommand(OrderService orderService, CarService carService, UserService userService) {
        this.orderService = orderService;
        this.carService = carService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean confirmed = false;
        User loginedClient = LoginUserUtils.getLoginedUser(request.getSession());
        String driverName = CookiesUtils.readCookies(request, CookiesUtils.DRIVER_NAME);
        String phoneNumber = CookiesUtils.readCookies(request, CookiesUtils.DRIVER_PHONE);
        String carModel = CookiesUtils.readCookies(request, CookiesUtils.CAR_MODEL);
        String carNumber = CookiesUtils.readCookies(request, CookiesUtils.CAR_NUMBER);
        String carType = CookiesUtils.readCookies(request, CookiesUtils.CAR_TYPE);
        String timeWait = CookiesUtils.readCookies(request, CookiesUtils.TIME_WAIT);
        String orderPrice = CookiesUtils.readCookies(request, CookiesUtils.PRICE);
        String driverId = CookiesUtils.readCookies(request, CookiesUtils.DRIVER_ID);
        String carId = CookiesUtils.readCookies(request, CookiesUtils.CAR_ID);
        String distanceVal = CookiesUtils.readCookies(request, CookiesUtils.DIST_VALUE);
        String origAddress = CookiesUtils.readCookies(request, CookiesUtils.ORIG_ADDRESS);
        String arrAddress = CookiesUtils.readCookies(request, CookiesUtils.DEST_ADDRESS);
        LOG.info("get cookies: " + driverName + "," + phoneNumber + "," + carModel + ","
                + carNumber + "," + carType + "," + timeWait + "," + orderPrice + "," + driverId + "," + carId + "," + distanceVal + "/" + origAddress);
        request.setAttribute(CookiesUtils.DRIVER_NAME, driverName);
        request.setAttribute(CookiesUtils.DRIVER_PHONE, phoneNumber);
        request.setAttribute(CookiesUtils.CAR_MODEL, carModel);
        request.setAttribute(CookiesUtils.CAR_NUMBER, carNumber);
        request.setAttribute(CookiesUtils.CAR_TYPE, carType);
        request.setAttribute(CookiesUtils.PRICE, orderPrice);
        request.setAttribute(CookiesUtils.TIME_WAIT, timeWait);

        if (userService.updateTotalSumRides(loginedClient, BigDecimal.valueOf(Long.parseLong(orderPrice)))) {
            confirmed = confirmOrder(loginedClient.getId(), Long.parseLong(driverId), origAddress,
                    arrAddress, BigDecimal.valueOf(Long.parseLong(orderPrice)), distanceVal, Long.parseLong(carId));
        }
        if (confirmed) {
            LOG.info("return order status");
            return PAGE_ORDER_STATUS;
        }
        return PAGE_ERROR_500;
    }

    /**
     * creates and save order, changes ordered car Status
     *
     * @param clientId
     * @param driverId
     * @param originAddress
     * @param destAddress
     * @param orderPrice
     * @param distance
     * @param carId
     * @return true if new order created
     */
    private boolean confirmOrder(Long clientId, Long driverId, String originAddress, String destAddress, BigDecimal orderPrice, String distance, Long carId) {
        try {
            Long orderId = orderService.createOrder(clientId, driverId, originAddress, destAddress, orderPrice, distance, carId);
            Car car = carService.getCarById(carId);
            car.setStatus(CarStatus.BOOKED);
            carService.updateCarStatus(car);
            LOG.info("order confirmed, orderId: " + orderId);
            return true;
        } catch (EntitySaveDaoException e) {
            LOG.error("order not created in 'confirmOrder': " + e);
            return false;
        } catch (EntityNotFoundException e){
            LOG.error("car not found in 'confirmOrder': " + e);
            return false;
        }
    }

}
