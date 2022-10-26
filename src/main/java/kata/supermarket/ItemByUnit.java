package kata.supermarket;

import java.math.BigDecimal;
import java.util.Objects;

public class ItemByUnit implements Item, Comparable<ItemByUnit> {

    private final Product product;
    private DiscountType discountType;

    ItemByUnit(final Product product) {
        this.product = product;
        this.discountType = DiscountType.NONE;
    }

    public BigDecimal price() {
        return product.pricePerUnit();
    }

    public DiscountType discountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    @Override
    public int compareTo(ItemByUnit o) {
        return product.pricePerUnit().compareTo(o.price());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemByUnit that = (ItemByUnit) o;
        return product.equals(that.product) && discountType == that.discountType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, discountType);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ItemByUnit{");
        sb.append("product=").append(product);
        sb.append(", discountType=").append(discountType);
        sb.append('}');
        return sb.toString();
    }
}
