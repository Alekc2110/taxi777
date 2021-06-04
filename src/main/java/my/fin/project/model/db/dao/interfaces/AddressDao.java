package my.fin.project.model.db.dao.interfaces;

import my.fin.project.model.entity.Address;

public interface AddressDao extends Dao<Address>{

    Long getAddressId(String street, String numberHouse);

}
