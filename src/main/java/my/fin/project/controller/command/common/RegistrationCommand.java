package my.fin.project.controller.command.common;

import my.fin.project.controller.command.Command;
import my.fin.project.exceptions.EmailExistException;
import my.fin.project.exceptions.EntitySaveDaoException;
import my.fin.project.exceptions.PhoneNumExistException;
import my.fin.project.model.entity.Discount;
import my.fin.project.model.entity.User;
import my.fin.project.model.entity.enums.Role;
import my.fin.project.model.service.UserService;
import my.fin.project.controller.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static my.fin.project.controller.command.Path.*;

public class RegistrationCommand extends Command {
    private static final String NAME_PARAMETER = "name";
    private static final String PHONE_NUMBER_PARAMETER = "phone_number";
    private static final String EMAIL_PARAMETER = "email";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String PASSWORD_REPEAT = "password_repeat";
    private static final String BAD_INPUT = "?badInput=true";
    private static final String BAD_EMAIL = "?badEmail=true";
    private static final String BAD_PHONE = "?badPhoneNumber=true";
    private static final String REG_AGAIN = "?successReg=true";
    private static final String REG_EXC = "?successReg=false";
    private final Logger LOG = Logger.getLogger(RegistrationCommand.class);
    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        final String name = request.getParameter(NAME_PARAMETER);
        final String phoneNumber = request.getParameter(PHONE_NUMBER_PARAMETER);
        final String email = request.getParameter(EMAIL_PARAMETER);
        final String password = request.getParameter(PASSWORD_PARAMETER);
        final String repeatPassword = request.getParameter(PASSWORD_REPEAT);

        if (name == null) {
            LOG.info("Name == null, return register client");
            return PAGE_REGISTER_CLIENT;
        }
        if (Validator.notValidData(name, phoneNumber,
                email, password, repeatPassword)) {
            LOG.info("wrong data input");
            return PAGE_REGISTER_CLIENT + BAD_INPUT;
        }

        User client = new User.Builder().
        setUserName(name).
        setPhoneNumber(phoneNumber).
        setEmail(email).
        setRole(Role.CLIENT).
        setPassword(password).build();
        String contextAndServletPath = request.getContextPath() + request.getServletPath();
        try {
            LOG.info("try write to database client");
            userService.saveUser(client);

        } catch (EmailExistException e) {
            LOG.error("EmailIsAlreadyTaken: ", e);
            return PAGE_REGISTER_CLIENT + BAD_EMAIL;
        } catch (PhoneNumExistException e) {
            LOG.error("PhoneNumberIsAlreadyTaken ", e);
            return PAGE_REGISTER_CLIENT + BAD_PHONE;
        } catch (EntitySaveDaoException e) {
            LOG.error("Exception when save new user: " +  client);
            return REDIRECT + contextAndServletPath + LOGIN + REG_EXC;
        }

        LOG.info("return login page");
        return REDIRECT + contextAndServletPath + LOGIN + REG_AGAIN;

    }
}
