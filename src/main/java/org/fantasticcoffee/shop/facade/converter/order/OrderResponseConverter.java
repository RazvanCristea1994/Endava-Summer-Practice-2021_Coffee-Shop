package org.fantasticcoffee.shop.facade.converter.order;

import org.fantasticcoffee.shop.data.customcoffee.CustomCoffeeResponse;
import org.fantasticcoffee.shop.data.customizablestandardcoffee.CoffeeWithStandardRecipeBaseResponse;
import org.fantasticcoffee.shop.data.order.OrderResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.fantasticcoffee.shop.model.coffee.CoffeeWithStandardRecipeBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderResponseConverter implements Converter<OrderResponse, Order> {

    @Autowired
    private Converter<CustomCoffeeResponse, CustomCoffee> customCoffeeConverter;

    @Autowired
    private Converter<CoffeeWithStandardRecipeBaseResponse, CoffeeWithStandardRecipeBase> customizableStandardCoffeeConverter;

    @Override
    public OrderResponse convert(Order order) {

        List<CustomCoffeeResponse> customCoffeeList = this.customCoffeeConverter.convertAll(order.getCustomCoffeeList());
        List<CoffeeWithStandardRecipeBaseResponse> coffeeWithStandardRecipeBaseResponseList = this.customizableStandardCoffeeConverter.convertAll(order.getCoffeeWithStandardRecipeBase());

        return new OrderResponse(
                order.getId(),
                order.getOrderDateTime(),
                customCoffeeList,
                coffeeWithStandardRecipeBaseResponseList,
                order.getWhereToDrink(),
                order.getCard().getCardHolderName(),
                order.getPrice()
        );
    }
}