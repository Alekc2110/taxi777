package my.fin.project.controller.command;

import my.fin.project.controller.command.admin.AdminAccountCommand;
import my.fin.project.controller.command.admin.ShowAllOrdersCommand;
import my.fin.project.controller.command.client.*;
import my.fin.project.controller.command.common.*;
import my.fin.project.controller.command.driver.DriverAccountCommand;
import my.fin.project.controller.command.common.EnterLoginCommand;
import my.fin.project.controller.command.driver.ShowDriverOrdersCommand;
import my.fin.project.model.service.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


import static my.fin.project.controller.command.Path.*;

public class CommandContainer {
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new HashMap<>();

    static {
        LOG.info("put all commands in HashMap");
        commands.put(REGISTER_CLIENT, new RegisterUserCommand());
        commands.put(REGISTER, new RegistrationCommand(new UserService()));
        commands.put(HOME, new TaxiHomeCommand());
        commands.put(MAKE_ORDER, new ClientOrderCommand());
        commands.put(ENTER_LOGIN, new EnterLoginCommand(new UserService()));
        commands.put(LOGIN, new LoginCommand());
        commands.put(LOGOUT, new LogOutCommand());
        commands.put(CLIENT_ACCOUNT, new ClientAccountCommand());
        commands.put(ADMIN_ACCOUNT, new AdminAccountCommand());
        commands.put(DRIVER_ACCOUNT, new DriverAccountCommand());
        commands.put(SHOW_DRIVER_ORDERS, new ShowDriverOrdersCommand(new OrderService()));
        commands.put(SHOW_ALL_ORDERS, new ShowAllOrdersCommand(new OrderService()));
        commands.put(FORBIDDEN, new ErrorForbiddenCommand());
        commands.put(ENTER_ORDER, new EnterOrderCommand(new CarService(),new OrderService(), new UserService()));

        commands.put(CONFIRM_ORDER, new ConfirmOrderCommand(new OrderService(),new CarService(),new UserService()));

    }

    public static Command getCommand(String commandName) {
        return commands.get(commandName);
    }

}
