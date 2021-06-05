package my.fin.project.controller.command.client;

import my.fin.project.controller.command.Command;
import my.fin.project.controller.command.Path;
import my.fin.project.model.entity.Address;
import my.fin.project.model.service.AddressService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClientOrderCommand extends Command {
    private final Logger LOG = Logger.getLogger(ClientOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("return order page");
        return Path.PAGE_TAXI_ORDER;
    }
}
