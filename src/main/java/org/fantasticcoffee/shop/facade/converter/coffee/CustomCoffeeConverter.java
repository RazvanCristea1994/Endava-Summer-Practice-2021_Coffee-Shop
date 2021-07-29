package org.fantasticcoffee.shop.facade.converter.coffee;

import org.fantasticcoffee.shop.data.customcoffee.CustomCoffeeRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.springframework.stereotype.Component;

@Component
public class CustomCoffeeConverter implements Converter<CustomCoffee, CustomCoffeeRequest> {

    @Override
    public CustomCoffee convert(CustomCoffeeRequest customCoffeeRequest) {

        return new CustomCoffee(customCoffeeRequest.getCustomerName(), customCoffeeRequest.getCustomerMadeRecipe());
    }
}