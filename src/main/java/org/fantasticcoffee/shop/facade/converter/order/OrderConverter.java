package org.fantasticcoffee.shop.facade.converter.order;

import org.fantasticcoffee.shop.data.customcoffee.CustomCoffeeRequest;
import org.fantasticcoffee.shop.data.customizablestandardcoffee.CustomizableStandardCoffeeRequest;
import org.fantasticcoffee.shop.data.order.OrderRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderConverter implements Converter<Order, OrderRequest> {

    @Autowired
    private Converter<CustomCoffee, CustomCoffeeRequest> customCoffeeRequestConverter;

    @Autowired
    private Converter<CustomizableStandardCoffee, CustomizableStandardCoffeeRequest> customizableStandardCoffeeRequestConverter;

    @Override
    public Order convert(OrderRequest orderRequest) {

        List<CustomCoffee> customCoffeeList = this.customCoffeeRequestConverter.convertAll(orderRequest.getCustomCoffeeList());
        List<CustomizableStandardCoffee> customizableStandardCoffeeList = this.customizableStandardCoffeeRequestConverter.convertAll(orderRequest.getCustomizableStandardCoffee());

        Order.Builder order = new Order.Builder();

        order.setCustomCoffeeList(customCoffeeList);
        order.setCustomizableStandardCoffee(customizableStandardCoffeeList);
        order.setWhereToDrink(orderRequest.getWhereToDrink());
        order.setCard(orderRequest.getCard());

        return order.build();
    }
}