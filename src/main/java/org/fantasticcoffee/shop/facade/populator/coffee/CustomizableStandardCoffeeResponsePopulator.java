package org.fantasticcoffee.shop.facade.populator.coffee;

import org.fantasticcoffee.shop.data.customizablestandardcoffee.CustomizableStandardCoffeeResponse;
import org.fantasticcoffee.shop.facade.populator.Populator;
import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;
import org.springframework.stereotype.Component;

@Component
public class CustomizableStandardCoffeeResponsePopulator implements Populator<CustomizableStandardCoffeeResponse, CustomizableStandardCoffee> {

    @Override
    public void populate(CustomizableStandardCoffeeResponse customizableStandardCoffeeResponse, CustomizableStandardCoffee coffee) {

        customizableStandardCoffeeResponse.setCustomerName(coffee.getCustomerName());
        customizableStandardCoffeeResponse.setExtraIngredients(coffee.getExtraIngredients());
        customizableStandardCoffeeResponse.setStandardCoffee(coffee.getStandardCoffee());
    }
}