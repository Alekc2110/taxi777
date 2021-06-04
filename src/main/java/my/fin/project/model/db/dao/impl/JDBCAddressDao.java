package my.fin.project.model.db.dao.impl;

import my.fin.project.model.db.dao.constants.Queries;
import my.fin.project.model.db.dao.interfaces.AddressDao;
import my.fin.project.model.entity.Address;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBCAddressDao implements AddressDao {
    private static final Logger LOG = Logger.getLogger(JDBCAddressDao.class);
    private Connection connection;

    public JDBCAddressDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long getAddressId(String street, String numberHouse) {
        try (PreparedStatement ps = connection.prepareStatement(Queries.GET_ADDRESS_ID)) {
            ps.setString(1, street);
            ps.setString(2, numberHouse);
            final ResultSet rs = ps.executeQuery();
            LOG.debug("Executed query" + Queries.GET_ADDRESS_ID);
            if (rs.next()) {
               return rs.getLong("address_id");
            }
        } catch (SQLException e) {
            LOG.error("SQLException occurred", e);
        }
        return -1L;
    }

    @Override
    public Long save(Address entity) {
        return null;
    }

    @Override
    public Address getById(Long id) {
        return null;
    }

    @Override
    public List<Address> findAll() {
        return null;
    }

    @Override
    public boolean update(Address address) {
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
