package my.fin.project.model.db.mapper;

import my.fin.project.model.entity.Discount;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountMapper implements Mapper<Discount> {
    @Override
    public Discount getEntity(ResultSet resultSet) throws SQLException {
        Discount discount = new Discount.Builder()
                .setClientId(resultSet.getLong("user_id"))
                .setDiscountRate(resultSet.getInt("discount_rate"))
                .setTotalSumRides(resultSet.getInt("total_sum"))
                .build();
        discount.setId(resultSet.getLong("dis_id"));

        return discount;
    }
}
