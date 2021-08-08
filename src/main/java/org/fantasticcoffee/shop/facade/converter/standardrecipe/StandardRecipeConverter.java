package org.fantasticcoffee.shop.facade.converter.standardrecipe;

import org.fantasticcoffee.shop.data.standardrecipe.StandardRecipeRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.StandardRecipe;
import org.fantasticcoffee.shop.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StandardRecipeConverter implements Converter<StandardRecipe, StandardRecipeRequest> {

    @Autowired
    private CoffeeService coffeeService;

    @Override
    public StandardRecipe convert(StandardRecipeRequest standardRecipeRequest) {

        return this.coffeeService.getCoffee(standardRecipeRequest.getStandardRecipeName());
    }
}