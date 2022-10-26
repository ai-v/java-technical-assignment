package kata.supermarket;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountCalculator {
    public static BigDecimal calculateDiscount(List<ItemByUnit> items) {
        if (items.size() <=1) {
            return BigDecimal.ZERO;
        }

        List<ItemByUnit> numBuyOneGetOneFree = items.stream()
                .filter(item -> item.discountType().equals(DiscountType.BUY_ONE_GET_ONE_FREE))
                .collect(Collectors.toList());

        long numApplicableForDiscount = numBuyOneGetOneFree.size()/2;
        int remainder = numBuyOneGetOneFree.size() % 2;

        return numBuyOneGetOneFree.stream()
                .sorted(Comparator.reverseOrder())
                .skip(numApplicableForDiscount + remainder)
                .map(ItemByUnit::price)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

    }
}
