package my.fin.project.model.db.dao.constants;

public final class Queries {
    //AddressDao
    public static final String GET_ADDRESS_ID = "SELECT address_id FROM address WHERE street = ? AND house_number = ?";
    //OrderDao
    public static final String GET_ALL_CARS = "SELECT * FROM car";
    //UserDao
    public static final String GET_USER_BY_ID = "SELECT * FROM user u JOIN role r WHERE u.id = r.user_id and u.id = ?";
    public static final String GET_BY_PHONE_PASSWORD = "SELECT * FROM user u JOIN role r ON u.id = r.user_id WHERE u.phone_number =? AND u.password = ?";
    public static final String SAVE_USER_CLIENT = "INSERT INTO user (username, password, phone_number, email)  VALUES (?,?,?,?)";
    public static final String SAVE_USER_ROLE = "INSERT INTO role (role_name, user_id)  VALUES (?,?)";

}
