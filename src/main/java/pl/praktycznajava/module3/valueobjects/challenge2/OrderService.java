package pl.praktycznajava.module3.valueobjects.challenge2;

import pl.praktycznajava.module3.valueobjects.challenge2.model.Currency;
import pl.praktycznajava.module3.valueobjects.challenge2.model.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderService {

    public static final Currency FREE_SHIPPING_THRESHOLD_CURRENCY = Currency.USD;
    public static final BigDecimal FREE_SHIPPING_THRESHOLD_AMOUNT = BigDecimal.valueOf(100);
    public static final int MAX_PERCENT = 100;
    OrderRepository orderRepository;
    CurrencyConverter currencyConverter;

    // TODO do obiektu domenowego nie można przekazać konkretnego konwertera, ale można przekazać jego interfejs
    // TODO możesz zmienić interfejs CurrencyConverter

    public boolean hasFreeShipping(String orderId) {
        Order order = orderRepository.findBy(orderId);
        BigDecimal totalAmount = order.getTotalAmount();
        Currency currency = order.getCurrency();
        if(currency != FREE_SHIPPING_THRESHOLD_CURRENCY) {
            BigDecimal convertedAmount = order.getConverter().convertTo(totalAmount, currency, FREE_SHIPPING_THRESHOLD_CURRENCY);
            return convertedAmount.compareTo(FREE_SHIPPING_THRESHOLD_AMOUNT) > 0;
        } else {
            return totalAmount.compareTo(FREE_SHIPPING_THRESHOLD_AMOUNT) > 0;
        }
    }

    public void addDiscount(String orderId, BigDecimal discount, Currency discountCurrency) {
        Order order = orderRepository.findBy(orderId);
        BigDecimal totalAmount = order.getTotalAmount();
        Currency currency = order.getCurrency();
        BigDecimal convertedAmount = order.getConverter().convertTo(discount, discountCurrency, currency);
        BigDecimal discountedAmount = totalAmount.subtract(convertedAmount);
        order.changeTotalAmount(discountedAmount, currency);
        orderRepository.save(order);
    }

    public void addPercentageDiscount(String orderId, int percentageDiscount) {
        Order order = orderRepository.findBy(orderId);
        BigDecimal totalAmount = order.getTotalAmount();
        BigDecimal discountAmount = totalAmount.multiply(BigDecimal.valueOf(percentageDiscount))
                .divide(BigDecimal.valueOf(MAX_PERCENT), RoundingMode.HALF_UP);
        BigDecimal discountedAmount = totalAmount.subtract(discountAmount);
        order.changeTotalAmount(discountedAmount, order.getCurrency());
        orderRepository.save(order);
    }

}