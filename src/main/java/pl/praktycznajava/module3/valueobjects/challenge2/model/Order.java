package pl.praktycznajava.module3.valueobjects.challenge2.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
public class Order {

   List<OrderItem> items;
   BigDecimal totalAmount;
   Currency currency;

   public void changeTotalAmount(BigDecimal totalAmount, Currency currency) {
      this.totalAmount = totalAmount;
      this.currency = currency;
   }

}