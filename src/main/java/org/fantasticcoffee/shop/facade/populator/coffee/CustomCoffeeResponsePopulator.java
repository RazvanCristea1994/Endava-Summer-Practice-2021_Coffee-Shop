package org.fantasticcoffee.shop.facade.populator.coffee;

import org.fantasticcoffee.shop.data.customcoffee.CustomCoffeeResponse;
import org.fantasticcoffee.shop.facade.populator.Populator;
import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.springframework.stereotype.Component;

@Component
public class CustomCoffeeResponsePopulator implements Populator<CustomCoffeeResponse, CustomCoffee> {

    @Override
    public void populate(CustomCoffeeResponse customCoffeeResponse, CustomCoffee customCoffee) {

        customCoffeeResponse.setCustomerName(customCoffee.getCustomerName());
        customCoffeeResponse.setCustomerMadeRecipe(customCoffee.getCustomerMadeRecipe());
    }
}