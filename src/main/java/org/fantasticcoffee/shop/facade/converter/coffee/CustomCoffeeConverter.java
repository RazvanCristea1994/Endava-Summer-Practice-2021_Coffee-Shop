package org.fantasticcoffee.shop.facade.converter.coffee;

import org.fantasticcoffee.shop.data.recipe.RecipeRequest;
import org.fantasticcoffee.shop.data.customcoffee.CustomCoffeeRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Recipe;
import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomCoffeeConverter implements Converter<CustomCoffee, CustomCoffeeRequest> {

    @Autowired
    Converter<Recipe, RecipeRequest> recipeConverter;

    @Override
    public CustomCoffee convert(CustomCoffeeRequest customCoffeeRequest) {

        return new CustomCoffee(customCoffeeRequest.getCustomerName(),
                this.recipeConverter.convert(customCoffeeRequest.getCustomerMadeRecipe()));
    }
}