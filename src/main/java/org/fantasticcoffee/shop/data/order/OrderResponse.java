package org.fantasticcoffee.shop.data.order;

import lombok.Getter;
import org.fantasticcoffee.shop.data.card.CardResponse;
import org.fantasticcoffee.shop.data.customcoffee.CoffeeResponse;
import org.fantasticcoffee.shop.model.WhereToDrink;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderResponse {

    private final Integer id;
    private final String customerName;
    private final LocalDateTime orderDateTime;
    private final List<CoffeeResponse> coffeeList;
    private final WhereToDrink whereToDrink;
    private final CardResponse cardResponse;
    private final Double totalOrderPrice;
    private final Double todayProfit;

    public OrderResponse(
            Integer id,
            String customerName,
            LocalDateTime orderDateTime,
            List<CoffeeResponse> coffeeList,
            WhereToDrink whereToDrink,
            CardResponse cardResponse,
            Double totalOrderPrice,
            Double todayProfit) {
        this.id = id;
        this.orderDateTime = orderDateTime;
        this.coffeeList = coffeeList;
        this.whereToDrink = whereToDrink;
        this.customerName = customerName;
        this.cardResponse = cardResponse;
        this.totalOrderPrice = totalOrderPrice;
        this.todayProfit = todayProfit;
    }
}