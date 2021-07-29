package org.fantasticcoffee.shop.facade.converter.order;

import org.fantasticcoffee.shop.data.customcoffee.CustomCoffeeResponse;
import org.fantasticcoffee.shop.data.customizablestandardcoffee.CustomizableStandardCoffeeResponse;
import org.fantasticcoffee.shop.data.order.OrderResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderResponseConverter implements Converter<OrderResponse, Order> {

    @Autowired
    private Converter<CustomCoffeeResponse, CustomCoffee> customCoffeeConverter;

    @Autowired
    private Converter<CustomizableStandardCoffeeResponse, CustomizableStandardCoffee> customizableStandardCoffeeConverter;

    @Override
    public OrderResponse convert(Order order) {

        List<CustomCoffeeResponse> customCoffeeList = this.customCoffeeConverter.convertAll(order.getCustomCoffeeList());
        List<CustomizableStandardCoffeeResponse> customizableStandardCoffeeResponseList = this.customizableStandardCoffeeConverter.convertAll(order.getCustomizableStandardCoffee());

        return new OrderResponse(
                order.getId(),
                order.getOrderDateTime(),
                customCoffeeList,
                customizableStandardCoffeeResponseList,
                order.getWhereToDrink()
        );
    }
}