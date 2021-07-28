package org.fantasticcoffee.shop.facade.populator.coffee;

import org.fantasticcoffee.shop.data.customizablestandardcoffee.CustomizableStandardCoffeeRequest;
import org.fantasticcoffee.shop.facade.populator.Populator;
import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;
import org.springframework.stereotype.Component;

@Component
public class CustomizableStandardCoffeePopulator implements Populator<CustomizableStandardCoffee, CustomizableStandardCoffeeRequest> {

    @Override
    public void populate(CustomizableStandardCoffee coffee, CustomizableStandardCoffeeRequest customizableStandardCoffeeRequest) {

        coffee.setCustomerName(customizableStandardCoffeeRequest.getCustomerName());
        coffee.setExtraIngredients(customizableStandardCoffeeRequest.getExtraIngredients());
        coffee.setStandardCoffee(customizableStandardCoffeeRequest.getStandardCoffee());
    }
}