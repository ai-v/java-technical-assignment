package kata.supermarket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountCalculatorTest {
    private DiscountCalculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new DiscountCalculator();
    }

    @Test
    public void returnsZero_whenNoItems() {
        assertEquals(BigDecimal.ZERO, calculator.calculateDiscount(List.of()));
    }

    @Test
    public void returnsZero_whenSingleItem() {
        BigDecimal price = BigDecimal.valueOf(1.49);
        List<ItemByUnit> item = List.of(new ItemByUnit(new Product(price)));
        assertEquals(BigDecimal.ZERO, calculator.calculateDiscount(item));
    }

    @Test
    public void returnsLeastExpensiveItemsPrice_whenTwoDifferentItemsBuyOneGetOneFree() {
        List<ItemByUnit> items = List.of(
                itemByUnit_price_3_15_buyOneGetOneFree(),
                itemByUnit_price_1_49_buyOneGetOneFree()
        );
        assertEquals(itemByUnit_price_1_49_buyOneGetOneFree().price(), calculator.calculateDiscount(items));
    }

    @Test
    public void returnsHalfPrice_whenTwoSameItemsBuyOneGetOneFree() {
        List<ItemByUnit> items = List.of(
                itemByUnit_price_1_49_buyOneGetOneFree(),
                itemByUnit_price_1_49_buyOneGetOneFree()
        );
        assertEquals(itemByUnit_price_1_49_buyOneGetOneFree().price(), calculator.calculateDiscount(items));
    }


    @Test
    public void returnsLeastExpensiveItemsPrice_whenThreeItemsBuyOneGetOneFree() {
        List<ItemByUnit> items = List.of(
                itemByUnit_price_1_49_buyOneGetOneFree(),
                itemByUnit_price_4_30_buyOneGetOneFree(),
                itemByUnit_price_3_15_buyOneGetOneFree()
        );
        assertEquals(itemByUnit_price_1_49_buyOneGetOneFree().price(), calculator.calculateDiscount(items));
    }

    @Test
    public void returnsZero_whenOneItemBuyOneGetOneFree_andTwoNonPromoItems() {
        List<ItemByUnit> items = List.of(itemByUnit_price_2_80_No_Promo(),
                itemByUnit_price_3_15_buyOneGetOneFree(),
                itemByUnit_price_2_80_No_Promo(),
                itemByUnit_price_2_80_No_Promo());
        assertEquals(BigDecimal.ZERO, calculator.calculateDiscount(items));
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
