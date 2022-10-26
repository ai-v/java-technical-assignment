package kata.supermarket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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




}
