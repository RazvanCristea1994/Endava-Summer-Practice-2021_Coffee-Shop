package org.fantasticcoffee.shop.facade.converter.coffee;

import org.fantasticcoffee.shop.data.customizablestandardcoffee.CustomizableStandardCoffeeRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.facade.populator.Populator;
import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomizableStandardCoffeeConverter implements Converter<CustomizableStandardCoffee, CustomizableStandardCoffeeRequest> {

    @Autowired
    private Populator<CustomizableStandardCoffee, CustomizableStandardCoffeeRequest> populator;

    @Override
    public CustomizableStandardCoffee convert(CustomizableStandardCoffeeRequest customizableStandardCoffeeRequest) {

        CustomizableStandardCoffee customizableStandardCoffee = new CustomizableStandardCoffee();
        populator.populate(customizableStandardCoffee, customizableStandardCoffeeRequest);

        return customizableStandardCoffee;
    }
}