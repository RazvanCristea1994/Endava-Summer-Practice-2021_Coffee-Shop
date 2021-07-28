package org.fantasticcoffee.shop.facade.converter.coffee;

import org.fantasticcoffee.shop.data.customcoffee.CustomCoffeeRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.facade.populator.Populator;
import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomCoffeeConverter implements Converter<CustomCoffee, CustomCoffeeRequest> {

    @Autowired
    private Populator<CustomCoffee, CustomCoffeeRequest> populator;

    @Override
    public CustomCoffee convert(CustomCoffeeRequest customCoffeeRequest) {

        CustomCoffee customCoffee = new CustomCoffee();
        populator.populate(customCoffee, customCoffeeRequest);

        return customCoffee;
    }
}