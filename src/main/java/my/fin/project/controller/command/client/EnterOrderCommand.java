package my.fin.project.controller.command.client;

import my.fin.project.controller.command.Command;
import my.fin.project.model.entity.Car;
import my.fin.project.model.entity.User;
import my.fin.project.model.entity.enums.CarStatus;
import my.fin.project.model.entity.pojo.DistancePojo;
import my.fin.project.model.entity.pojo.Element;
import my.fin.project.model.entity.pojo.Row;
import my.fin.project.model.service.CarService;
import my.fin.project.model.service.OrderService;
import my.fin.project.model.service.UserService;
import my.fin.project.utils.CookiesUtils;
import my.fin.project.utils.LoginUserUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import static my.fin.project.controller.command.Path.*;

public class EnterOrderCommand extends Command {
    private final Logger LOG = Logger.getLogger(EnterOrderCommand.class);
    private static final String ADDRESS_DEPARTURE_PARAMETER = "addressDeparture";
    private static final String ADDRESS_ARRIVE_PARAMETER = "addressArrive";
    private static final String CAR_TYPE_PARAMETER = "carType";
    private static final String NO_AVAIL_CAR = "?noAvailableCar=true";
    private static final String NO_SUCH_ADDRESS = "?notFoundAddress=true";
    private static final String SAME_ADDRESS = "?sameAddress=true";
    private CarService carService;
    private OrderService orderService;
    private UserService userService;


    public EnterOrderCommand(CarService carService, OrderService orderService, UserService userService) {
        this.carService = carService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String addressDeparture = request.getParameter(ADDRESS_DEPARTURE_PARAMETER);
        String addressArrive = request.getParameter(ADDRESS_ARRIVE_PARAMETER);
        final String carType = request.getParameter(CAR_TYPE_PARAMETER);

        LOG.info(addressDeparture + "!!!!!");
        LOG.info(addressArrive + "!!!!!");
        LOG.info(carType + "********");


        if (isNotSameAddress(addressDeparture, addressArrive)) {
            User loginedClient = LoginUserUtils.getLoginedUser(request.getSession());
            DistancePojo distancePojo = orderService.getDistanceFromGoogleService(addressDeparture, addressArrive);

            if (distancePojo.getDestination_addresses().length > 0 && distancePojo.getOrigin_addresses().length > 0) {
                Map<String, String> distanceValues = getDistanceValues(distancePojo);
                LOG.info("distanceValues map: " + distanceValues);

                List<Car> carList = carService.getCars(carType);
                LOG.info(String.format("returned list cars: %s of CarType %s: ", carList, carType));
                if (!carList.isEmpty()) {
                    String contextAndServletPath = request.getContextPath() + request.getServletPath();

                    Optional<Car> availCar = carList.stream().filter(car -> car.getStatus().equals(CarStatus.FREE)).findFirst();
                    if (availCar.isPresent()) {
                        boolean confirmed = false;
                        Car orderCar = availCar.get();
                        LOG.info(String.format("make order with free car: %s: ", orderCar));
                        User driver = userService.getDriver(orderCar.getId());
                        BigDecimal orderPrice = orderService.calculateOrderPrice(Integer.parseInt(distanceValues.get("distanceValue")), orderCar.getCarType(), loginedClient);
                        LOG.info("orderPrice calculated: " + orderPrice.toString());
                        if (userService.updateTotalSumRides(loginedClient, orderPrice)) {
                            confirmed = confirmOrder(loginedClient, driver, distanceValues, orderPrice, orderCar);
                            int timeWaiting = orderService.getTimeWaiting();
                            CookiesUtils.addCookies(response, driver, orderPrice, timeWaiting, orderCar);
                        }
                        if (confirmed)
                            return REDIRECT + contextAndServletPath + SHOW_CLIENT_ORDER;
                    } else {
                        Optional<Car> bookedCar = carList.stream().filter(car -> car.getStatus().equals(CarStatus.BOOKED)).findFirst();
                        if (bookedCar.isPresent()) {
                            boolean confirmed = false;
                            int reqSeats = bookedCar.get().getSeats();
                            Optional<Car> altCar = carService.getFreeCarsByNumSeats(reqSeats).stream().findAny();
                            if(altCar.isPresent()){
                                Car otherCar = altCar.get();
                                LOG.info("make order with alternate car CarType : " + otherCar.getCarType().toString());
                                User driver = userService.getDriver(otherCar.getId());
                                BigDecimal orderPrice = orderService.calculateOrderPrice(Integer.parseInt(distanceValues.get("distanceValue")), otherCar.getCarType(), loginedClient);
                                LOG.info("orderPrice calculated: " + orderPrice.toString());
                                if (userService.updateTotalSumRides(loginedClient, orderPrice)) {
                                    confirmed = confirmOrder(loginedClient, driver, distanceValues, orderPrice, otherCar);
                                    int timeWaiting = orderService.getTimeWaiting();
                                    CookiesUtils.addCookies(response, driver, orderPrice, timeWaiting, otherCar);
                                }
                                if (confirmed)
                                    return REDIRECT + contextAndServletPath + SHOW_CLIENT_ORDER;
                            }
                        }
                    }
                }
            } else {
                LOG.info("not found such address");
                return PAGE_TAXI_ORDER + NO_SUCH_ADDRESS;
            }
        } else {
            LOG.info("Not correct input: chosen the same address!");
            return PAGE_TAXI_ORDER + SAME_ADDRESS;
        }
        LOG.info("no available car");
        return PAGE_TAXI_ORDER + NO_AVAIL_CAR;
    }

    /**
     *  checks if the same address input     *
     * @param addressDeparture
     * @param addressArrive
     */
    private boolean isNotSameAddress(String addressDeparture, String addressArrive) {
        return !addressArrive.equals(addressDeparture);
    }

    /**
     * converts DistancePojo object to Map
     * @param distance
     *
     */
    private Map<String, String> getDistanceValues(DistancePojo distance) {
        Map<String, String> values = new HashMap<>();
        StringBuilder distanceValue = new StringBuilder();
        String destinationAddress = distance.getDestination_addresses()[0];
        String originAddress = distance.getOrigin_addresses()[0];
        LOG.info("distancePojo object: " + distance.toString());
        Stream.of(distance.getRows()).map(Row::getElements).forEach(elements -> {
            for (Element e : elements) {
                distanceValue.append(e.getDistance().getValue());
            }
        });
        values.put("distanceValue", distanceValue.toString());
        values.put("originAddress", originAddress);
        values.put("destinationAddress", destinationAddress);

        return values;
    }

    /**
     * creates and save order, changes ordered car Status
     * @param loginedClient
     * @param driver
     * @param distanceValues
     * @param orderPrice
     * @param car
     *
     */
    private boolean confirmOrder(User loginedClient, User driver, Map<String, String> distanceValues, BigDecimal orderPrice, Car car) {
        Long orderId = orderService.createOrder(loginedClient, driver, distanceValues.get("originAddress"),
                distanceValues.get("destinationAddress"), orderPrice, distanceValues.get("distanceValue"), car);
        if (orderId != -1) {
            car.setStatus(CarStatus.BOOKED);
            carService.updateCarStatus(car);
            LOG.info("order confirmed, orderId: " + orderId);
            return true;
        }
        return false;
    }

}
