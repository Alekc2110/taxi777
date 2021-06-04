package my.fin.project.model.db.mapper;

import my.fin.project.model.entity.Address;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressMapper implements Mapper<Address> {
    @Override
    public Address getEntity(ResultSet resultSet) throws SQLException {
        Address address = new Address.Builder()
                .setStreet(resultSet.getString("street"))
                .setHouseNumber(resultSet.getString("house_number"))
                .build();
        address.setId(resultSet.getLong("address_id"));
        return address;
    }
}
