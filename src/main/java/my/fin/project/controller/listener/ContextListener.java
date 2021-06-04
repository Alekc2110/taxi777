package my.fin.project.controller.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ContextListener implements ServletContextListener {
    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("Servlet context initialization starts");
        initCommandContainer();
        LOG.info("Servlet context initialization finished");
    }

    /**
     * Initializes list of Commands in CommandContainer
     */
    private void initCommandContainer() {
        LOG.info("Command container initialization started");
        try {
            Class.forName("my.fin.project.controller.command.CommandContainer");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        LOG.debug("Command container initialization finished");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info("Servlet context destroyed");
    }
}
