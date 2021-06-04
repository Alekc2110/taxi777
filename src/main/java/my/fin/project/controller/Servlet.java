package my.fin.project.controller;

import my.fin.project.controller.command.Command;
import my.fin.project.controller.command.CommandContainer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static my.fin.project.controller.command.Path.*;

public class Servlet extends HttpServlet {
    private final Logger LOG = Logger.getLogger(Servlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("doGet process");
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("doPost process");
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = getRequestPath(req);
        LOG.info("get command");
        LOG.info("command name: " + commandName + "!!!!!!!");
        Command command = CommandContainer.getCommand(commandName);
        String contextAndServletPath = req.getContextPath() + req.getServletPath();
        if (command == null) {
            LOG.info("command is null redirect home page");
            resp.sendRedirect(contextAndServletPath + HOME);
        } else {
            String nextPage = command.execute(req, resp);
            if (isRedirect(nextPage)) {
                LOG.info("redirect page " + nextPage);
                resp.sendRedirect(nextPage.replaceAll(REDIRECT, EMPTY_STR));
            } else {
                LOG.info("forward page " + nextPage);
                req.getRequestDispatcher(nextPage).forward(req, resp);
            }
        }
    }

    private String getRequestPath(HttpServletRequest request) {
        String pathURI = request.getRequestURI();
        String servletPath = request.getServletPath();
        String regex = ".*" + servletPath;
        return pathURI.replaceAll(regex, EMPTY_STR);
    }

    private boolean isRedirect(String string) {
        return string.contains(REDIRECT);
    }
}
