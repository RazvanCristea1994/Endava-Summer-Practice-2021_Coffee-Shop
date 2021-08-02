package org.fantasticcoffee.shop.facade.converter.coffee;

import org.fantasticcoffee.shop.data.customizablestandardcoffee.CustomizableStandardCoffeeRequest;
import org.fantasticcoffee.shop.data.ingredient.IngredientOnRecipeRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomizableStandardCoffeeConverter implements Converter<CustomizableStandardCoffee, CustomizableStandardCoffeeRequest> {

    @Autowired
    private Converter<IngredientOnRecipe, IngredientOnRecipeRequest> ingredientOnRecipeConverter;

    @Override
    public CustomizableStandardCoffee convert(CustomizableStandardCoffeeRequest customizableStandardCoffeeRequest) {

        return new CustomizableStandardCoffee(
                customizableStandardCoffeeRequest.getCustomerName(),
                customizableStandardCoffeeRequest.getStandardCoffee(),
                this.ingredientOnRecipeConverter.convertAll(customizableStandardCoffeeRequest.getExtraIngredients()));
    }
}