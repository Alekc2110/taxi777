package my.fin.project.model.db.dao.impl;

import my.fin.project.model.db.dao.constants.Queries;
import my.fin.project.model.db.dao.interfaces.UserDao;
import my.fin.project.model.db.mapper.DiscountMapper;
import my.fin.project.model.db.mapper.DriverMapper;
import my.fin.project.model.db.mapper.Mapper;
import my.fin.project.model.db.mapper.UserMapper;
import my.fin.project.model.entity.Discount;
import my.fin.project.model.entity.User;
import my.fin.project.model.entity.enums.Role;
import my.fin.project.utils.PriceUtils;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static my.fin.project.model.db.dao.constants.Queries.*;


public class JDBCUserDao implements UserDao {
    private static final Logger LOG = Logger.getLogger(JDBCUserDao.class);
    private Connection connection;
    private Mapper<User> userMapper;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> getById(Long id) {
        userMapper = new UserMapper();
        User entity = null;
        try (PreparedStatement ps = connection.prepareStatement(GET_USER_BY_ID)) {
            ps.setLong(1, id);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query" + GET_USER_BY_ID);
            if (rs.next()) {
                LOG.debug("check if rs has next");
                entity = userMapper.getEntity(rs);
            }
        } catch (SQLException e) {
            LOG.error("SQLException occurred in JdbcDriverDao", e);
        }
        return Optional.ofNullable(entity);
    }

    @Override
    public boolean checkUser(String phoneNumber, String password) {
        try (PreparedStatement ps = connection.prepareStatement(GET_BY_PHONE_PASSWORD)) {
            ps.setString(1, phoneNumber);
            ps.setString(2, password);
            final ResultSet rs = ps.executeQuery();

            LOG.debug("Executed query" + GET_BY_PHONE_PASSWORD);
            if (rs.next()) {
                LOG.debug("check if rs has next");
                return true;
            }
        } catch (SQLException e) {
            LOG.error("SQLException occurred in JdbcClientDao", e);
        }
        return false;
    }

    @Override
    public Optional<User> getUser(String phoneNumber, String password) {
        userMapper = new UserMapper();
        User result = null;
        try (PreparedStatement ps = connection.prepareStatement(Queries.GET_BY_PHONE_PASSWORD)) {
            ps.setString(1, phoneNumber);
            ps.setString(2, password);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query" + GET_BY_PHONE_PASSWORD);
            if (rs.next()) {
                LOG.debug("check if rs has next");
                result = userMapper.getEntity(rs);
            }
        } catch (SQLException e) {
            LOG.error("SQLException occurred in JdbcDriverDao", e);
        }
        return Optional.ofNullable(result);
    }

    @Override
    public boolean saveUserRole(Long id, Role userRole) {
        try (PreparedStatement ps = connection.prepareStatement(SAVE_USER_ROLE)) {
            ps.setString(1, String.valueOf(userRole));
            ps.setLong(2, id);
            if (ps.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            LOG.info("SQLException in saveUserRole UserDao: " + e);
            return false;
        }
        return true;
    }

    @Override
    public boolean isEmailExists(String email) {
        try (PreparedStatement ps = connection.prepareStatement(GET_USER_BY_EMAIL)) {
            ps.setString(1, email);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query" + GET_USER_BY_EMAIL);
            if (rs.next()) {
                LOG.debug("check if rs has next");
                return true;
            }
        } catch (SQLException e) {
            LOG.error("SQLException when isEmailExists in UserDao", e);
        }
        return false;
    }

    @Override
    public boolean isPhoneNumberExists(String phoneNumber) {
        try (PreparedStatement ps = connection.prepareStatement(GET_USER_BY_PHONE)) {
            ps.setString(1, phoneNumber);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query" + GET_USER_BY_PHONE);
            if (rs.next()) {
                LOG.debug("check if rs has next");
                return true;
            }
        } catch (SQLException e) {
            LOG.error("SQLException when isPhoneNumberExists in UserDao", e);
        }
        return false;
    }

    @Override
    public Optional<User> findDriverByCarId(Long carId) {
        Mapper<User> driverMapper = new DriverMapper();
        User driver = null;
        try (PreparedStatement ps = connection.prepareStatement(GET_DRIVER_BY_CAR_ID)) {
            ps.setLong(1, carId);
            LOG.debug("Executed query" + GET_DRIVER_BY_CAR_ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                driver = driverMapper.getEntity(rs);
            }
        } catch (SQLException e) {
            LOG.error("SQLException when findDriverByCarId in UserDao", e);
        }
        LOG.debug("found driver: " + driver);
        return Optional.ofNullable(driver);
    }

    @Override
    public Optional<Discount> getUserDiscount(Long clientId) {
        Mapper<Discount> discountMapper = new DiscountMapper();
        Discount discount = null;
        try (PreparedStatement ps = connection.prepareStatement(GET_USER_DISCOUNT)) {
            ps.setLong(1, clientId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                discount = discountMapper.getEntity(rs);
            }
        } catch (SQLException e) {
            LOG.error("SQLException when getUserDiscount in UserDao", e);
        }
        return Optional.ofNullable(discount);

    }

    @Override
    public Optional<Long> save(User user) {
        ResultSet generatedKey = null;
        try (PreparedStatement ps = connection.prepareStatement(SAVE_USER_CLIENT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getPhoneNumber());
            ps.setString(4, user.getEmail());
            if (ps.executeUpdate() != 1) {
                return Optional.empty();
            }
            generatedKey = ps.getGeneratedKeys();
            if (generatedKey.next()) {
                Long id = generatedKey.getLong(1);
                return Optional.of(id);
            }
        } catch (SQLException e) {
            LOG.info("SQLException in 'saveUser' UserDao: " + e);
            return Optional.empty();
        } finally {
            try {
                if (generatedKey != null) generatedKey.close();
            } catch (SQLException e) {
                LOG.info("SQLException when closing ResultSet in 'saveUser': " + e);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean updateUserDiscount(User client, BigDecimal orderPrice) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_DISCOUNT)) {
            connection.setAutoCommit(false);
            Optional<Discount> userDiscountOpt = getUserDiscount(client.getId());
            if (userDiscountOpt.isPresent()) {
                Discount userDiscount = userDiscountOpt.get();
                PriceUtils.checkUserDiscount(userDiscount, orderPrice);
                ps.setInt(1, userDiscount.getDiscountRate());
                ps.setInt(2, userDiscount.getTotalSumRides());
                ps.setLong(3, userDiscount.getId());
            }
            if (ps.executeUpdate() != 1) {
                return false;
            }
            connection.commit();
        } catch (SQLException e) {
            LOG.error("SQLException when updateUserDiscount in UserDao ", e);
            try {
                LOG.error("Transaction is being rolled back");
                connection.rollback();
            } catch (SQLException ex) {
                LOG.error("SQLException when rollBack connection in updateUserDiscount ", ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LOG.error("SQLException when setAutoCommit in updateUserDiscount ", e);
            }
        }
        return true;
    }

    @Override
    public boolean saveUserDiscount(Discount discount) {
        ResultSet generatedKey = null;
        try (PreparedStatement ps = connection.prepareStatement(SAVE_USER_DISCOUNT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, discount.getClientId());
            ps.setInt(2, discount.getDiscountRate());
            ps.setInt(3, discount.getTotalSumRides());
            if (ps.executeUpdate() != 1) {
                return false;
            }
            generatedKey = ps.getGeneratedKeys();
            if (generatedKey.next()) {
                generatedKey.getLong(1);
                return true;
            }
        } catch (SQLException e) {
            LOG.info("SQLException in 'saveUser' UserDao: " + e);
            return false;
        } finally {
            try {
                if (generatedKey != null) generatedKey.close();
            } catch (SQLException e) {
                LOG.info("SQLException when closing ResultSet in 'saveUser': " + e);
            }
        }
        return false;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
