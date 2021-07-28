package org.fantasticcoffee.shop.facade.populator.order;

import org.fantasticcoffee.shop.data.customcoffee.CustomCoffeeResponse;
import org.fantasticcoffee.shop.data.customizablestandardcoffee.CustomizableStandardCoffeeResponse;
import org.fantasticcoffee.shop.data.order.OrderResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.facade.populator.Populator;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderResponsePopulator implements Populator<OrderResponse, Order> {

    @Autowired
    private Converter<CustomCoffeeResponse, CustomCoffee> customCoffeeConverter;

    @Autowired
    private Converter<CustomizableStandardCoffeeResponse, CustomizableStandardCoffee> customizableStandardCoffeeConverter;

    @Override
    public void populate(OrderResponse orderResponse, Order order) {

        List<CustomCoffeeResponse> customCoffeeList = this.customCoffeeConverter.convertAll(order.getCustomCoffeeList());
        List<CustomizableStandardCoffeeResponse> customizableStandardCoffeeResponseList = this.customizableStandardCoffeeConverter.convertAll(order.getCustomizableStandardCoffee());

        orderResponse.setId(order.getId());
        orderResponse.setOrderDateTime(order.getOrderDateTime());
        orderResponse.setCustomCoffeeList(customCoffeeList);
        orderResponse.setCustomizableStandardCoffee(customizableStandardCoffeeResponseList);
        orderResponse.setWhereToDrink(order.getWhereToDrink());
    }
}