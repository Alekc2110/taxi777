package my.fin.project.utils;

import my.fin.project.exceptions.MyCookiesUnsupportEncodingException;
import my.fin.project.model.entity.Car;
import my.fin.project.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class CookiesUtils {
    private static final Logger LOG = Logger.getLogger(CookiesUtils.class);
    public static final String DRIVER_NAME = "driverName";
    public static final String DRIVER_PHONE = "phoneNumber";
    public static final String CAR_MODEL = "carModel";
    public static final String CAR_TYPE = "carType";
    public static final String CAR_NUMBER = "carNumber";
    public static final String PRICE = "price";
    public static final String TIME_WAIT = "timeWait";
    public static final String ENCODING = "UTF-8";

    private CookiesUtils() {
    }

    public static String readCookies(HttpServletRequest request, String key)  {
        String empty = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (key.equals(c.getName())) {
                try {
                    return URLDecoder.decode(c.getValue(), ENCODING);
                } catch (UnsupportedEncodingException e) {
                    LOG.error("Exception in readCookies CookiesUtils");
                    throw new MyCookiesUnsupportEncodingException("Encoding exception when reading Cookies: " + e.getMessage());
                }
            }
        }
        return empty;
    }

    public static void addCookies(HttpServletResponse response,
                                  User driver, BigDecimal price, int timeWait, Car car)  {
        String driverName = "";
        try {
            driverName = URLEncoder.encode(driver.getUsername(), ENCODING);
        } catch (UnsupportedEncodingException e) {
            LOG.error("Exception in addCookies CookiesUtils");
            throw new MyCookiesUnsupportEncodingException("Encoding exception when adding Cookies: " + e.getMessage());
        }
        Cookie driverNameCookie = new Cookie(DRIVER_NAME, driverName);
        Cookie phoneNumberCookie = new Cookie(DRIVER_PHONE, driver.getPhoneNumber());
        Cookie priceCookie = new Cookie(PRICE, price.toString());
        Cookie timeWaitCookie = new Cookie(TIME_WAIT, timeWait + "");
        Cookie carModel = new Cookie(CAR_MODEL, car.getModel());
        Cookie carType = new Cookie(CAR_TYPE, String.valueOf(car.getCarType()));
        Cookie carNumber = new Cookie(CAR_NUMBER, car.getCarNumber());

        driverNameCookie.setMaxAge(-1);
        phoneNumberCookie.setMaxAge(-1);
        priceCookie.setMaxAge(-1);
        timeWaitCookie.setMaxAge(-1);
        carModel.setMaxAge(-1);
        carType.setMaxAge(-1);
        carNumber.setMaxAge(-1);

        response.addCookie(driverNameCookie);
        response.addCookie(phoneNumberCookie);
        response.addCookie(priceCookie);
        response.addCookie(timeWaitCookie);
        response.addCookie(carModel);
        response.addCookie(carType);
        response.addCookie(carNumber);
    }
}
