package my.fin.project.controller.command;

import my.fin.project.controller.command.admin.AdminAccountCommand;
import my.fin.project.controller.command.client.*;
import my.fin.project.controller.command.common.*;
import my.fin.project.controller.command.driver.DriverAccountCommand;
import my.fin.project.controller.command.common.EnterLoginCommand;
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
//        commands.put(SHOW_ALL_ORDERS_PAG, new ShowAllDriverOrdersCommand(new OrderService()));
        commands.put(FORBIDDEN, new ErrorForbiddenCommand());
//        commands.put(ENTER_ORDER, new EnterOrderCommand(new OrderService(), new DriverService(),
//                new AddressService()));
        commands.put(SHOW_CLIENT_ORDER, new ShowOrderClientCommand());
//        commands.put(ENTER_NUMBER_OF_ORDER, new DriverEnterNumberOrderCommand(new OrderService()));
//        commands.put(NO_COMMAND, new NoCommand());
    }

    public static Command getCommand(String commandName) {
//        if (commandName == null || !commands.containsKey(commandName)) {
//            LOG.trace("Command not found, name --> " + commandName);
//            return commands.get("noCommand");
//        }

        return commands.get(commandName);
    }

}
