package my.fin.project.controller.command.common;

import my.fin.project.controller.command.Command;
import my.fin.project.controller.command.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterUserCommand extends Command {
    private final Logger LOG = Logger.getLogger(RegisterUserCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("return register client page");
        return Path.PAGE_REGISTER_CLIENT;
    }
}
