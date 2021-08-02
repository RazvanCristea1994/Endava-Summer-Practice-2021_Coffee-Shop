package org.fantasticcoffee.shop.facade.converter.ingredients;

import org.fantasticcoffee.shop.data.ingredient.IngredientOnRecipeRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;
import org.springframework.stereotype.Component;

@Component
public class IngredientOnRecipeConverter implements Converter<IngredientOnRecipe, IngredientOnRecipeRequest> {

    @Override
    public IngredientOnRecipe convert(IngredientOnRecipeRequest ingredientOnRecipeRequest) {
        return new IngredientOnRecipe(ingredientOnRecipeRequest.getIngredient(), ingredientOnRecipeRequest.getNumberOfShots());
    }
}