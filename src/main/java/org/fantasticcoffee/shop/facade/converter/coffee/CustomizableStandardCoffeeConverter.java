package org.fantasticcoffee.shop.facade.converter.coffee;

import org.fantasticcoffee.shop.data.customizablestandardcoffee.CustomizableStandardCoffeeRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;
import org.springframework.stereotype.Component;

@Component
public class CustomizableStandardCoffeeConverter implements Converter<CustomizableStandardCoffee, CustomizableStandardCoffeeRequest> {

    @Override
    public CustomizableStandardCoffee convert(CustomizableStandardCoffeeRequest customizableStandardCoffeeRequest) {

        return new CustomizableStandardCoffee(
                customizableStandardCoffeeRequest.getCustomerName(),
                customizableStandardCoffeeRequest.getStandardCoffee(),
                customizableStandardCoffeeRequest.getExtraIngredients());
    }
}