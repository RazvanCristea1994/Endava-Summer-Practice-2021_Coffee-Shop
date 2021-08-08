package org.fantasticcoffee.shop.data.order;

import lombok.Getter;
import org.fantasticcoffee.shop.data.card.CardResponse;
import org.fantasticcoffee.shop.data.customcoffee.CoffeeResponse;
import org.fantasticcoffee.shop.model.OrderType;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderResponse {

    private final Integer id;
    private final String customerName;
    private final LocalDateTime orderDateTime;
    private final List<CoffeeResponse> coffeeList;
    private final OrderType orderType;
    private final CardResponse cardResponse;
    private final Double totalOrderPrice;
    private final Double todayProfit;

    public OrderResponse(
            Integer id,
            String customerName,
            LocalDateTime orderDateTime,
            List<CoffeeResponse> coffeeList,
            OrderType orderType,
            CardResponse cardResponse,
            Double totalOrderPrice,
            Double todayProfit) {
        this.id = id;
        this.orderDateTime = orderDateTime;
        this.coffeeList = coffeeList;
        this.orderType = orderType;
        this.customerName = customerName;
        this.cardResponse = cardResponse;
        this.totalOrderPrice = totalOrderPrice;
        this.todayProfit = todayProfit;
    }
}