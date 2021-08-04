package org.fantasticcoffee.shop.data.order;

import lombok.Getter;
import org.fantasticcoffee.shop.data.customcoffee.CustomCoffeeResponse;
import org.fantasticcoffee.shop.data.customizablestandardcoffee.CoffeeWithStandardRecipeBaseResponse;
import org.fantasticcoffee.shop.model.WhereToDrink;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderResponse {

    private final Integer id;
    private final LocalDateTime orderDateTime;
    private final List<CustomCoffeeResponse> customCoffeeList;
    private final List<CoffeeWithStandardRecipeBaseResponse> customizableStandardCoffee;
    private final WhereToDrink whereToDrink;
    private final String cardHolderName;
    private final Double totalPrice;

    public OrderResponse(
            Integer id,
            LocalDateTime orderDateTime,
            List<CustomCoffeeResponse> customCoffeeList,
            List<CoffeeWithStandardRecipeBaseResponse> customizableStandardCoffee,
            WhereToDrink whereToDrink,
            String cardHolderName,
            Double totalPrice) {
        this.id = id;
        this.orderDateTime = orderDateTime;
        this.customCoffeeList = customCoffeeList;
        this.customizableStandardCoffee = customizableStandardCoffee;
        this.whereToDrink = whereToDrink;
        this.cardHolderName = cardHolderName;
        this.totalPrice = totalPrice;
    }
}