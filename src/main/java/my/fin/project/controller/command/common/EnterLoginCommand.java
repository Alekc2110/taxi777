package my.fin.project.controller.command.common;

import my.fin.project.controller.command.Command;
import my.fin.project.exceptions.EntityNotFoundException;
import my.fin.project.model.entity.User;
import my.fin.project.model.entity.enums.Role;
import my.fin.project.model.service.UserService;
import my.fin.project.utils.LoginUserUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static my.fin.project.controller.command.Path.*;

public class EnterLoginCommand extends Command {
    private final Logger LOG = Logger.getLogger(EnterLoginCommand.class);
    private static final String PHONE_PARAMETER = "phoneNumber";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String ERROR_MESSAGE_STRING = "Invalid Phone Number or Password";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String WRONG_DATA = "?wrongData=true";
    private UserService userService;

    public EnterLoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String phoneNumber = request.getParameter(PHONE_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        User user;
        String contextAndServletPath = request.getContextPath() + request.getServletPath();
        LOG.info("contextPath:" + request.getContextPath());
        LOG.info("servletPath:" + request.getServletPath());
        LOG.info("requestURI:" + request.getRequestURI());
        LOG.info("check data phone number and password" + phoneNumber + " " + password);
        if (inputWrongData(phoneNumber, password)) {
            LOG.info("wrong data");
            return PAGE_LOGIN + WRONG_DATA;
        } else {
            user = LoginUserUtils.getLoginedUser(request.getSession());
            if (user != null) {
                LOG.info("logined person: " + user);
                if (Role.CLIENT.getName().equals(user.getRole().getName())) {
                    LOG.info("return client account");
                    return REDIRECT + contextAndServletPath + CLIENT_ACCOUNT;
                }
                if (Role.ADMIN.getName().equals(user.getRole().getName())) {
                    LOG.info("return admin account");
                    return REDIRECT + contextAndServletPath + ADMIN_ACCOUNT;
                } else {
                    LOG.info("return driver account");
                    return REDIRECT + contextAndServletPath + DRIVER_ACCOUNT;
                }
            }
            try {
                user = userService.getUser(phoneNumber, password);
            } catch (EntityNotFoundException e) {
                LOG.error("could not found user in 'EnterLoginCommand' " + e);
                return PAGE_ERROR_500;
            }

            LoginUserUtils.saveLoginedUser(request.getSession(), user);
            if (user.getRole().equals(Role.CLIENT)) {
                LOG.info("return client account");
                return REDIRECT + contextAndServletPath + CLIENT_ACCOUNT;
            }
            if (user.getRole().equals(Role.ADMIN)) {
                LOG.info("return admin account");
                return REDIRECT + contextAndServletPath + ADMIN_ACCOUNT;
            }
            LOG.info("return driver account");
            return REDIRECT + contextAndServletPath + DRIVER_ACCOUNT;
        }

    }

    private boolean inputWrongData(String phoneNumber, String password) {
        return !userService.checkUserExists(phoneNumber, password);
    }

}
