package my.fin.project.controller.command.common;

import my.fin.project.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static my.fin.project.controller.command.Path.*;

public class LogOutCommand extends Command {
    private final Logger LOG = Logger.getLogger(LogOutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        LOG.info("destroy session");
        session.invalidate();
        String contextAndServletPath = request.getContextPath() + request.getServletPath();
        LOG.info("return home page");
        return REDIRECT + contextAndServletPath + PAGE_TAXI_HOME;
    }
}
