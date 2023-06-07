package pl.praktycznajava.module3.valueobjects.challenge2;

import pl.praktycznajava.module3.valueobjects.challenge2.model.Currency;
import pl.praktycznajava.module3.valueobjects.challenge2.model.Order;

import java.math.BigDecimal;

public class OrderService {


    OrderRepository orderRepository;
    CurrencyConverter currencyConverter;

    // TODO do obiektu domenowego nie można przekazać konkretnego konwertera, ale można przekazać jego interfejs
    // TODO możesz zmienić interfejs CurrencyConverter

    public boolean hasFreeShipping(String orderId) {
        Order order = orderRepository.findBy(orderId);
        return order.getAmount().hasFreeShipping(currencyConverter);
    }


    public void addDiscount(String orderId, BigDecimal discount, Currency discountCurrency) {
        Order order = orderRepository.findBy(orderId);
        order.setAmount(order.getAmount().addDiscount(currencyConverter, discount, discountCurrency));
        orderRepository.save(order);
    }

    public void addPercentageDiscount(String orderId, int percentageDiscount) {
        Order order = orderRepository.findBy(orderId);
        order.setAmount(order.getAmount().addPercentageDiscount(percentageDiscount));
        orderRepository.save(order);
    }


}