package pl.praktycznajava.module3.valueobjects.challenge2.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import pl.praktycznajava.module3.valueobjects.challenge2.Converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class Amount {
    public static final int MAX_PERCENT = 100;
    public static final Currency FREE_SHIPPING_THRESHOLD_CURRENCY = Currency.USD;
    public static final BigDecimal FREE_SHIPPING_THRESHOLD_AMOUNT = BigDecimal.valueOf(100);

    private BigDecimal totalAmount;
    private Currency currency;

    public boolean hasFreeShipping(Converter converter) {
        if(currency != FREE_SHIPPING_THRESHOLD_CURRENCY) {
            BigDecimal convertedAmount = converter.convertTo(this.totalAmount, this.currency, FREE_SHIPPING_THRESHOLD_CURRENCY);
            return convertedAmount.compareTo(FREE_SHIPPING_THRESHOLD_AMOUNT) > 0;
        } else {
            return totalAmount.compareTo(FREE_SHIPPING_THRESHOLD_AMOUNT) > 0;
        }
    }

    public void addDiscount(Converter converter, BigDecimal discount, Currency discountCurrency) {
        this.totalAmount = this.totalAmount.subtract(converter.convertTo(discount, discountCurrency, this.currency));
    }

    public void addPercentageDiscount(int percentageDiscount) {
        this.totalAmount = totalAmount.subtract(totalAmount.multiply(BigDecimal.valueOf(percentageDiscount))
                .divide(BigDecimal.valueOf(MAX_PERCENT), RoundingMode.HALF_UP));
    }
}
