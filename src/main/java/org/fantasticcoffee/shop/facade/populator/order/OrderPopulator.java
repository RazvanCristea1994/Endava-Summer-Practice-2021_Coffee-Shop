package org.fantasticcoffee.shop.facade.populator.order;

import org.fantasticcoffee.shop.data.customcoffee.CustomCoffeeRequest;
import org.fantasticcoffee.shop.data.customizablestandardcoffee.CustomizableStandardCoffeeRequest;
import org.fantasticcoffee.shop.data.order.OrderRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.facade.populator.Populator;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderPopulator implements Populator<Order.Builder, OrderRequest> {

    @Autowired
    private Converter<CustomCoffee, CustomCoffeeRequest> customCoffeeRequestConverter;

    @Autowired
    private Converter<CustomizableStandardCoffee, CustomizableStandardCoffeeRequest> customizableStandardCoffeeRequestConverter;

    @Override
    public void populate(Order.Builder order, OrderRequest orderRequest) {

        List<CustomCoffee> customCoffeeList = this.customCoffeeRequestConverter.convertAll(orderRequest.getCustomCoffeeList());
        List<CustomizableStandardCoffee> customizableStandardCoffeeList = this.customizableStandardCoffeeRequestConverter.convertAll(orderRequest.getCustomizableStandardCoffee());

        order.setCustomCoffeeList(customCoffeeList);
        order.setCustomizableStandardCoffee(customizableStandardCoffeeList);
        order.setWhereToDrink(orderRequest.getWhereToDrink());
        order.setCard(orderRequest.getCard());
    }
}