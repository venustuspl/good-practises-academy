package pl.praktycznajava.module3.valueobjects.challenge2;

import pl.praktycznajava.module3.valueobjects.challenge2.model.Currency;

import java.math.BigDecimal;

import static pl.praktycznajava.module3.valueobjects.challenge2.OrderService.FREE_SHIPPING_THRESHOLD_AMOUNT;
import static pl.praktycznajava.module3.valueobjects.challenge2.OrderService.FREE_SHIPPING_THRESHOLD_CURRENCY;

public class CurrencyConverter implements Converter{
    @Override
    public BigDecimal convertTo(BigDecimal amount, Currency fromCurrency, Currency toCurrency) {
        return null;
    }

}
