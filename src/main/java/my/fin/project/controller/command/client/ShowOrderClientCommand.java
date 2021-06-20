package my.fin.project.controller.command.client;

import my.fin.project.controller.command.Command;
import my.fin.project.utils.CookiesUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static my.fin.project.controller.command.Path.ORDER_STATUS;

public class ShowOrderClientCommand extends Command {
    private final Logger LOG = Logger.getLogger(ShowOrderClientCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String driverName = CookiesUtils.readCookies(request, CookiesUtils.DRIVER_NAME);
        String phoneNumber = CookiesUtils.readCookies(request, CookiesUtils.DRIVER_PHONE);
        String carModel = CookiesUtils.readCookies(request, CookiesUtils.CAR_MODEL);
        String carNumber = CookiesUtils.readCookies(request, CookiesUtils.CAR_NUMBER);
        String carType = CookiesUtils.readCookies(request, CookiesUtils.CAR_TYPE);
        String timeWait = CookiesUtils.readCookies(request, CookiesUtils.TIME_WAIT);
        String price = CookiesUtils.readCookies(request, CookiesUtils.PRICE);
        LOG.info("get cookies");
        request.setAttribute(CookiesUtils.DRIVER_NAME, driverName);
        request.setAttribute(CookiesUtils.DRIVER_PHONE, phoneNumber);
        request.setAttribute(CookiesUtils.CAR_MODEL, carModel);
        request.setAttribute(CookiesUtils.CAR_NUMBER, carNumber);
        request.setAttribute(CookiesUtils.CAR_TYPE, carType);
        request.setAttribute(CookiesUtils.PRICE, price);
        request.setAttribute(CookiesUtils.TIME_WAIT, timeWait);
        LOG.info("return order status");
        return ORDER_STATUS;
    }
}
