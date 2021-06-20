package my.fin.project.model.db.mapper;

import my.fin.project.model.entity.Discount;
import my.fin.project.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountMapper implements Mapper<Discount> {
    @Override
    public Discount getEntity(ResultSet resultSet) throws SQLException {
        Discount discount = new Discount();
                discount.setId(Long.parseLong(resultSet.getString("dis_id")));
                discount.setClientId(Long.parseLong(resultSet.getString("user_id")));
                discount.setDiscountRate(Integer.parseInt(resultSet.getString("discount_rate")));
                discount.setTotalSumRides(Integer.parseInt(resultSet.getString("total_sum")));

        return discount;
    }
}
