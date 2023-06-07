package pl.praktycznajava.module3.valueobjects.challenge2.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import pl.praktycznajava.module3.valueobjects.challenge2.Converter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class Amount {
    public static final int MAX_PERCENT = 100;
    public static final Currency FREE_SHIPPING_THRESHOLD_CURRENCY = Currency.USD;
    public static final BigDecimal FREE_SHIPPING_THRESHOLD_AMOUNT = BigDecimal.valueOf(100);

    private final BigDecimal totalAmount;
    private final Currency currency;

    public boolean hasFreeShipping(Converter converter) {
        if (currency != FREE_SHIPPING_THRESHOLD_CURRENCY) {
            BigDecimal convertedAmount = converter.convertTo(this.totalAmount, this.currency, FREE_SHIPPING_THRESHOLD_CURRENCY);
            return convertedAmount.compareTo(FREE_SHIPPING_THRESHOLD_AMOUNT) > 0;
        } else {
            return totalAmount.compareTo(FREE_SHIPPING_THRESHOLD_AMOUNT) > 0;
        }
    }

    public Amount addDiscount(Converter converter, BigDecimal discount, Currency discountCurrency) {
        return Amount.of(this.totalAmount.subtract(converter.convertTo(discount, discountCurrency, this.currency)), this.currency);
    }

    public Amount addPercentageDiscount(int percentageDiscount) {
        return Amount.of(this.totalAmount.subtract(this.totalAmount.multiply(BigDecimal.valueOf(percentageDiscount)).divide(BigDecimal.valueOf(MAX_PERCENT), RoundingMode.HALF_UP)), this.currency);
    }
}

//Value Objecty nie mają setterów, więc tam @Setter do usunięcia 🙂 No i jeszcze ustawiamy pola jako final w nich. Aby wykonać działanie na value objectcie, po prostu wykonujemy dane działanie i wynik zapisujemy już w nowym obiekcie i jego zwracamy na zewnątrz 🙂