package org.fantasticcoffee.shop.facade.converter.coffee;

import org.fantasticcoffee.shop.data.customizablestandardcoffee.CoffeeWithStandardRecipeBaseRequest;
import org.fantasticcoffee.shop.data.ingredient.IngredientOnRecipeRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.coffee.CoffeeWithStandardRecipeBase;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomizableStandardCoffeeConverter implements Converter<CoffeeWithStandardRecipeBase, CoffeeWithStandardRecipeBaseRequest> {

    @Autowired
    private Converter<IngredientOnRecipe, IngredientOnRecipeRequest> ingredientOnRecipeConverter;

    @Override
    public CoffeeWithStandardRecipeBase convert(CoffeeWithStandardRecipeBaseRequest coffeeWithStandardRecipeBaseRequest) {

        return new CoffeeWithStandardRecipeBase(
                coffeeWithStandardRecipeBaseRequest.getCustomerName(),
                coffeeWithStandardRecipeBaseRequest.getStandardRecipe(),
                this.ingredientOnRecipeConverter.convertAll(coffeeWithStandardRecipeBaseRequest.getExtraIngredients()));
    }
}