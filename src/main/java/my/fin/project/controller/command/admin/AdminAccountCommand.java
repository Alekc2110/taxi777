package my.fin.project.controller.command.admin;

import my.fin.project.controller.command.Command;
import my.fin.project.controller.command.Path;
import my.fin.project.controller.command.client.ClientAccountCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminAccountCommand extends Command {
    private final Logger LOG = Logger.getLogger(ClientAccountCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("return admin account page");
        return Path.PAGE_ADMIN_ACCOUNT;
    }
}
