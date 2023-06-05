package pl.praktycznajava.module3.valueobjects.challenge2;

import pl.praktycznajava.module3.valueobjects.challenge2.model.Currency;

import java.math.BigDecimal;

public interface Converter {
    BigDecimal convertTo(BigDecimal amount, Currency fromCurrency, Currency toCurrency);
}
