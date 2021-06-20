package my.fin.project.utils;


import my.fin.project.model.entity.Discount;
import my.fin.project.model.entity.enums.CarType;

import java.math.BigDecimal;

public class PriceUtils {
    private static final int START_PRICE_STD = 50;
    private static final int START_PRICE_COMF = 70;
    private static final int START_PRICE_VAN = 100;
    private static final int PRICE_RATE_PER_KM = 7;

    public static BigDecimal getOrderPriceDisc(Integer distance, CarType carType, int discountRate) {
        BigDecimal common = new BigDecimal(distance).divide(new BigDecimal(1000), 0, BigDecimal.ROUND_CEILING)
                .multiply(new BigDecimal(PRICE_RATE_PER_KM));

        if (carType.equals(CarType.STANDARD)) {
            BigDecimal res = common.add(new BigDecimal(START_PRICE_STD));
            return res.subtract(res.multiply(new BigDecimal(discountRate)).divide(new BigDecimal(100), 0, BigDecimal.ROUND_CEILING));
        }
        if (carType.equals(CarType.COMFORT)) {
            BigDecimal res = common.add(new BigDecimal(START_PRICE_COMF));
            return res.subtract(res.multiply(new BigDecimal(discountRate)).divide(new BigDecimal(100), 0, BigDecimal.ROUND_CEILING));
        }
        BigDecimal res = common.add(new BigDecimal(START_PRICE_VAN));
        return res.subtract(res.multiply(new BigDecimal(discountRate)).divide(new BigDecimal(100), 0, BigDecimal.ROUND_CEILING));
    }

    public static BigDecimal getOrderPrice(Integer distance, CarType carType) {
        BigDecimal common = new BigDecimal(distance).divide(new BigDecimal(1000), 0, BigDecimal.ROUND_CEILING)
                .multiply(new BigDecimal(PRICE_RATE_PER_KM));

        if (carType.equals(CarType.STANDARD)) {
            return common.add(new BigDecimal(START_PRICE_STD));
        }
        if (carType.equals(CarType.COMFORT)) {
            return common.add(new BigDecimal(START_PRICE_COMF));
        }
        return common.add(new BigDecimal(START_PRICE_VAN));
    }

    public static void checkUserDiscount(Discount userDiscount, BigDecimal orderPrice) {
        userDiscount.setTotalSumRides(userDiscount.getTotalSumRides() + orderPrice.intValue());
        if (userDiscount.getTotalSumRides() >= 5000) {
            userDiscount.setDiscountRate(5);
        }
        if (userDiscount.getTotalSumRides() >= 10000) {
            userDiscount.setDiscountRate(10);
        }
    }
}
