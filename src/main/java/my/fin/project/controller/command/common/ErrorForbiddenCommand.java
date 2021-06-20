package my.fin.project.controller.command.common;

import my.fin.project.controller.command.Command;
import my.fin.project.controller.command.Path;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorForbiddenCommand extends Command {
    private final Logger LOGGER = Logger.getLogger(ErrorForbiddenCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("return 403 page");
        response.setStatus(403);
        return Path.PAGE_FORBIDDEN_403;
    }
}
