package my.fin.project.controller.command.common;

import my.fin.project.controller.command.Command;
import my.fin.project.controller.command.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class NoCommand extends Command {

    private static final long serialVersionUID = 27859766166865669L;

    private static final Logger LOG = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        LOG.debug("Command starts");
//
//        String errorMessage = "No such command";
//        request.setAttribute("errorMessage", errorMessage);
//        LOG.error("Set the request attribute: errorMessage --> " + errorMessage);
//
//        LOG.debug("Command finished");
        return Path.PAGE__ERROR_PAGE;
    }
}
