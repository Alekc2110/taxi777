package my.fin.project.controller.command.client;

import my.fin.project.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EnterOrderCommand extends Command {
//    public EnterOrderCommand(OrderService orderService, DriverService driverService, AddressService addressService, CouponService couponService) {
//        super();
//    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return null;
    }
}
