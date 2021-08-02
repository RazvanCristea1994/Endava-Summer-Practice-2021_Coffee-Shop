package org.fantasticcoffee.shop.facade.converter.coffee;

import org.fantasticcoffee.shop.data.customizablestandardcoffee.CustomizableStandardCoffeeResponse;
import org.fantasticcoffee.shop.data.ingredient.IngredientOnRecipeResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomizableStandardCoffeeResponseConverter implements Converter<CustomizableStandardCoffeeResponse, CustomizableStandardCoffee> {

    @Autowired
    private Converter<IngredientOnRecipeResponse, IngredientOnRecipe> ingredientOnRecipeRequestConverter;

    @Override
    public CustomizableStandardCoffeeResponse convert(CustomizableStandardCoffee customizableStandardCoffee) {

        return new CustomizableStandardCoffeeResponse(
                customizableStandardCoffee.getCustomerName(),
                customizableStandardCoffee.getStandardCoffee(),
                this.ingredientOnRecipeRequestConverter.convertAll(customizableStandardCoffee.getExtraIngredients()));
    }
}