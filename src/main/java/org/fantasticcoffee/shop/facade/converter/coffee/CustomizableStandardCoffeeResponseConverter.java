package org.fantasticcoffee.shop.facade.converter.coffee;

import org.fantasticcoffee.shop.data.customizablestandardcoffee.CustomizableStandardCoffeeResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.facade.populator.Populator;
import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomizableStandardCoffeeResponseConverter implements Converter<CustomizableStandardCoffeeResponse, CustomizableStandardCoffee> {

    @Autowired
    private Populator<CustomizableStandardCoffeeResponse, CustomizableStandardCoffee> populator;

    @Override
    public CustomizableStandardCoffeeResponse convert(CustomizableStandardCoffee customizableStandardCoffee) {

        CustomizableStandardCoffeeResponse customizableStandardCoffeeResponse = new CustomizableStandardCoffeeResponse();
        populator.populate(customizableStandardCoffeeResponse, customizableStandardCoffee);

        return customizableStandardCoffeeResponse;
    }
}