package org.fantasticcoffee.shop.facade.converter.coffee;

import org.fantasticcoffee.shop.data.customcoffee.CustomCoffeeResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.facade.populator.Populator;
import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomCoffeeResponseConverter implements Converter<CustomCoffeeResponse, CustomCoffee> {

    @Autowired
    private Populator<CustomCoffeeResponse, CustomCoffee> populator;

    @Override
    public CustomCoffeeResponse convert(CustomCoffee customCoffee) {

        CustomCoffeeResponse customCoffeeResponse = new CustomCoffeeResponse();
        populator.populate(customCoffeeResponse, customCoffee);

        return customCoffeeResponse;
    }
}