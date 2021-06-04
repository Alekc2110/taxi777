package my.fin.project.model.db;

import my.fin.project.model.db.dao.impl.*;
import my.fin.project.model.db.dao.interfaces.*;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class JDBCDaoFactory extends DaoFactory {
    private static final Logger LOG = Logger.getLogger(JDBCDaoFactory.class);
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public AddressDao createAddressDao() {
        return new JDBCAddressDao(getConnection());
    }

    @Override
    public CarDao createCarDao() {
        return new JDBCCarDao(getConnection());
    }

    @Override
    public UserDao createUserDao() { return new JDBCUserDao(getConnection()); }

    @Override
    public OrderDao createOrderDao() {return new JDBCOrderDao(getConnection()); }

    private Connection getConnection() {
        try {
            LOG.debug("getConnection: " + dataSource);
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOG.debug("SQLException occurred", e);
            throw new RuntimeException(e);
        }
    }
}
