package my.fin.project.controller.command.client;

import my.fin.project.controller.command.Command;
import my.fin.project.controller.command.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientOrderCommand extends Command {
    private final Logger LOG = Logger.getLogger(ClientOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("return order page");
        return Path.PAGE_TAXI_ORDER;
    }
}
