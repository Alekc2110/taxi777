package my.fin.project.model.db.dao.impl;

import my.fin.project.model.db.dao.constants.Queries;
import my.fin.project.model.db.dao.interfaces.UserDao;
import my.fin.project.model.db.mapper.Mapper;
import my.fin.project.model.db.mapper.UserMapper;
import my.fin.project.model.entity.User;
import my.fin.project.model.entity.enums.Role;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JDBCUserDao implements UserDao {
    private static final Logger LOG = Logger.getLogger(JDBCUserDao.class);
    private Connection connection;
    private Mapper<User> userMapper;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getById(Long id) {
        userMapper = new UserMapper();
        User entity = null;
        try (PreparedStatement ps = connection.prepareStatement(Queries.GET_USER_BY_ID)) {
            ps.setLong(1, id);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query" + Queries.GET_USER_BY_ID);
            if (rs.next()) {
                entity = userMapper.getEntity(rs);
            }
        } catch (SQLException e) {
            LOG.error("SQLException occurred in JdbcDriverDao", e);
        }
        return entity;
    }

    @Override
    public boolean checkUser(String phoneNumber, String password) {
        try (PreparedStatement ps = connection.prepareStatement(Queries.GET_BY_PHONE_PASSWORD)) {
            ps.setString(1, phoneNumber);
            ps.setString(2, password);
            final ResultSet rs = ps.executeQuery();

            LOG.debug("Executed query" + Queries.GET_BY_PHONE_PASSWORD);
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
    public User getUser(String phoneNumber, String password) {
        userMapper = new UserMapper();
        User result = null;
        try (PreparedStatement ps = connection.prepareStatement(Queries.GET_BY_PHONE_PASSWORD)) {
            ps.setString(1, phoneNumber);
            ps.setString(2, password);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query" + Queries.GET_BY_PHONE_PASSWORD);
            if (rs.next()) {
                result = userMapper.getEntity(rs);
            }
        } catch (SQLException e) {
            LOG.error("SQLException occurred in JdbcDriverDao", e);
        }
        return result;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public Role checkUserRole(User user) {
        return null;
    }

    @Override
    public boolean saveUserRole(Long id, Role userRole) {
        try (PreparedStatement ps = connection.prepareStatement(Queries.SAVE_USER_ROLE)) {
            ps.setString(1, String.valueOf(userRole));
            ps.setLong(2, id);
            if (ps.executeUpdate() != 1) {
                return false;
            }
        } catch (SQLException e) {
            LOG.info("SQLException in 'saveUserRole' UserDao: " + e);
            return false;
        }
        return true;
    }

    @Override
    public boolean isEmailExists(String email) {
        return false;
    }

    @Override
    public boolean isPhoneNumberExists(String phoneNumber) {
        return false;
    }

    @Override
    public Long save(User user) {
        ResultSet generatedKey = null;
        Long id = null;
        try (PreparedStatement ps = connection.prepareStatement(Queries.SAVE_USER_CLIENT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getPhoneNumber());
            ps.setString(4, user.getEmail());
            if (ps.executeUpdate() != 1) {
                return -1L;
            }
            generatedKey = ps.getGeneratedKeys();
            if (generatedKey.next()) {
                id = generatedKey.getLong(1);
                return id;
            }
        } catch (SQLException e) {
            LOG.info("SQLException in 'saveUser' UserDao: " + e);
            return -1L;
        } finally {
            try {
                if (generatedKey != null) generatedKey.close();
            } catch (SQLException e) {
                LOG.info("SQLException when closing ResultSet in 'saveUser': " + e);
            }
        }
        return id;
    }


    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
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
