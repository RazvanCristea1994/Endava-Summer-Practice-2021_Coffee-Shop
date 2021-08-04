package org.fantasticcoffee.shop.facade.converter.coffee;

import org.fantasticcoffee.shop.data.customizablestandardcoffee.CoffeeWithStandardRecipeBaseResponse;
import org.fantasticcoffee.shop.data.ingredient.IngredientOnRecipeResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.coffee.CoffeeWithStandardRecipeBase;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomizableStandardCoffeeResponseConverter implements Converter<CoffeeWithStandardRecipeBaseResponse, CoffeeWithStandardRecipeBase> {

    @Autowired
    private Converter<IngredientOnRecipeResponse, IngredientOnRecipe> ingredientOnRecipeRequestConverter;

    @Override
    public CoffeeWithStandardRecipeBaseResponse convert(CoffeeWithStandardRecipeBase coffeeWithStandardRecipeBase) {

        return new CoffeeWithStandardRecipeBaseResponse(
                coffeeWithStandardRecipeBase.getCustomerName(),
                coffeeWithStandardRecipeBase.getStandardRecipe(),
                this.ingredientOnRecipeRequestConverter.convertAll(coffeeWithStandardRecipeBase.getExtraIngredients()));
    }
}