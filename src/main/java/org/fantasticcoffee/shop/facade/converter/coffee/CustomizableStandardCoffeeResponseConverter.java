package org.fantasticcoffee.shop.facade.converter.coffee;

import org.fantasticcoffee.shop.data.customizablestandardcoffee.CustomizableStandardCoffeeResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;
import org.springframework.stereotype.Component;

@Component
public class CustomizableStandardCoffeeResponseConverter implements Converter<CustomizableStandardCoffeeResponse, CustomizableStandardCoffee> {

    @Override
    public CustomizableStandardCoffeeResponse convert(CustomizableStandardCoffee customizableStandardCoffee) {

        return new CustomizableStandardCoffeeResponse(
                customizableStandardCoffee.getCustomerName(),
                customizableStandardCoffee.getStandardCoffee(),
                customizableStandardCoffee.getExtraIngredients()
        );
    }
}