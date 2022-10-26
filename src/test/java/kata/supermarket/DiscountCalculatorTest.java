package kata.supermarket;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountCalculatorTest {

    @Test
    public void returnsZero_whenNoItems() {
        assertEquals(BigDecimal.ZERO, DiscountCalculator.calculateDiscount(List.of()));
    }

    @Test
    public void returnsZero_whenSingleItem() {
        BigDecimal price = BigDecimal.valueOf(1.49);
        List<ItemByUnit> item = List.of(new ItemByUnit(new Product(price)));
        assertEquals(BigDecimal.ZERO, DiscountCalculator.calculateDiscount(item));
    }

    @Test
    public void returnsLeastExpensiveItemsPrice_whenTwoDifferentItemsBuyOneGetOneFree() {
        List<ItemByUnit> items = List.of(
                itemByUnit_price_3_15_buyOneGetOneFree(),
                itemByUnit_price_1_49_buyOneGetOneFree()
        );
        assertEquals(itemByUnit_price_1_49_buyOneGetOneFree().price(), DiscountCalculator.calculateDiscount(items));
    }

    @Test
    public void returnsHalfPrice_whenTwoSameItemsBuyOneGetOneFree() {
        List<ItemByUnit> items = List.of(
                itemByUnit_price_1_49_buyOneGetOneFree(),
                itemByUnit_price_1_49_buyOneGetOneFree()
        );
        assertEquals(itemByUnit_price_1_49_buyOneGetOneFree().price(), DiscountCalculator.calculateDiscount(items));
    }


    @Test
    public void returnsLeastExpensiveItemsPrice_whenThreeItemsBuyOneGetOneFree() {
        List<ItemByUnit> items = List.of(
                itemByUnit_price_1_49_buyOneGetOneFree(),
                itemByUnit_price_4_30_buyOneGetOneFree(),
                itemByUnit_price_3_15_buyOneGetOneFree()
        );
        assertEquals(itemByUnit_price_1_49_buyOneGetOneFree().price(), DiscountCalculator.calculateDiscount(items));
    }

    @Test
    public void returnsZero_whenOneItemBuyOneGetOneFree_andTwoNonPromoItems() {
        List<ItemByUnit> items = List.of(itemByUnit_price_2_80_No_Promo(),
                itemByUnit_price_3_15_buyOneGetOneFree(),
                itemByUnit_price_2_80_No_Promo(),
                itemByUnit_price_2_80_No_Promo());
        assertEquals(BigDecimal.ZERO, DiscountCalculator.calculateDiscount(items));
    }



    private static ItemByUnit itemByUnit_price_1_49_buyOneGetOneFree() {
        ItemByUnit item = new ItemByUnit(new Product(BigDecimal.valueOf(1.49)));
        item.setDiscountType(DiscountType.BUY_ONE_GET_ONE_FREE);
        return item;
    }

    private static ItemByUnit itemByUnit_price_3_15_buyOneGetOneFree() {
        ItemByUnit item = new ItemByUnit(new Product(BigDecimal.valueOf(3.15)));
        item.setDiscountType(DiscountType.BUY_ONE_GET_ONE_FREE);
        return item;
    }

    private static ItemByUnit itemByUnit_price_4_30_buyOneGetOneFree() {
        ItemByUnit item = new ItemByUnit(new Product(BigDecimal.valueOf(4.30).setScale(2, RoundingMode.HALF_UP)));
        item.setDiscountType(DiscountType.BUY_ONE_GET_ONE_FREE);
        return item;
    }

    private static ItemByUnit itemByUnit_price_2_80_No_Promo() {
        return new ItemByUnit(new Product(BigDecimal.valueOf(2.80)));
    }


}
