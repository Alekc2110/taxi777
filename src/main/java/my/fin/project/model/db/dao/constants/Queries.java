package my.fin.project.model.db.dao.constants;

public final class Queries {
    //CarDao
    public static final String GET_ALL_CARS = "SELECT * FROM `car`";
    public static final String GET_CARS_BY_TYPE = "SELECT * FROM `car` WHERE type = ?";
    public static final String UPDATE_CAR_STATUS = "UPDATE `car` SET status = ? WHERE car_id = ?";
    //UserDao
    public static final String GET_USER_BY_ID = "SELECT * FROM `user` u JOIN `role` r WHERE u.id = r.user_id and u.id = ?";
    public static final String GET_BY_PHONE_PASSWORD = "SELECT * FROM `user` u JOIN `role` r ON u.id = r.user_id WHERE u.phone_number =? AND u.password = ?";
    public static final String SAVE_USER_CLIENT = "INSERT INTO `user` (username, password, phone_number, email)  VALUES (?,?,?,?)";
    public static final String SAVE_USER_ROLE = "INSERT INTO `role` (role_name, user_id)  VALUES (?,?)";
    public static final String GET_USER_BY_EMAIL = "SELECT * FROM `user` WHERE email = ?";
    public static final String GET_USER_BY_PHONE = "SELECT * FROM `user` WHERE phone_number = ?";
    public static final String GET_USER_DISCOUNT = "SELECT * FROM `discount` WHERE user_id = ?";
    public static final String UPDATE_DISCOUNT = "UPDATE `discount` SET discount_rate = ?, total_sum = ? WHERE dis_id = ?";
    public static final String GET_DRIVER_BY_CAR_ID = "SELECT cd.driver_id, u.username, u.phone_number FROM user u " +
                                                      "JOIN car_driver cd ON u.id = cd.driver_id WHERE cd.car_id = ?";
    //OrderDao
    public static final String SAVE_ORDER = "INSERT INTO `order`(order_status, client_id, driver_id, depart_address, arr_address, cost, car_id, creation_date, distance)  VALUES (?,?,?,?,?,?,?,?,?)";


}
