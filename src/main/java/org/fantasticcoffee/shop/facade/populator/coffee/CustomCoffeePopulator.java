package org.fantasticcoffee.shop.facade.populator.coffee;

import org.fantasticcoffee.shop.data.customcoffee.CustomCoffeeRequest;
import org.fantasticcoffee.shop.facade.populator.Populator;
import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.springframework.stereotype.Component;

@Component
public class CustomCoffeePopulator implements Populator<CustomCoffee, CustomCoffeeRequest> {

    @Override
    public void populate(CustomCoffee customCoffee, CustomCoffeeRequest customCoffeeRequest) {

        customCoffee.setCustomerName(customCoffeeRequest.getCustomerName());
        customCoffee.setCustomerMadeRecipe(customCoffeeRequest.getCustomerMadeRecipe());
    }
}