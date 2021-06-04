package my.fin.project.controller.command.client;

import my.fin.project.controller.command.Command;
import my.fin.project.controller.command.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientAccountCommand extends Command {
    private final Logger LOG = Logger.getLogger(ClientAccountCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("return client account page");
        return Path.PAGE_CLIENT_ACCOUNT;
    }
}
