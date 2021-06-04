package my.fin.project.controller.command.common;

import my.fin.project.controller.command.Command;
import my.fin.project.controller.command.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand extends Command {
    private final Logger LOG = Logger.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("return login page");
        return Path.PAGE_LOGIN;
    }
}
