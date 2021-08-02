package org.fantasticcoffee.shop.facade.converter.coffee;

import org.fantasticcoffee.shop.data.customcoffee.CustomCoffeeResponse;
import org.fantasticcoffee.shop.data.recipe.RecipeResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Recipe;
import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomCoffeeResponseConverter implements Converter<CustomCoffeeResponse, CustomCoffee> {

    @Autowired
    Converter<RecipeResponse, Recipe> recipeResponseConverter;

    @Override
    public CustomCoffeeResponse convert(CustomCoffee customCoffee) {

        return new CustomCoffeeResponse(
                customCoffee.getCustomerName(),
                this.recipeResponseConverter.convert(customCoffee.getCustomerMadeRecipe()));
    }
}