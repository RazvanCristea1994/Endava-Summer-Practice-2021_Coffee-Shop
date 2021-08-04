package org.fantasticcoffee.shop.facade.converter.recipe;

import org.fantasticcoffee.shop.data.ingredient.IngredientOnRecipeResponse;
import org.fantasticcoffee.shop.data.recipe.RecipeResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Recipe;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecipeResponseConverter implements Converter<RecipeResponse, Recipe> {

    @Autowired
    Converter<IngredientOnRecipeResponse, IngredientOnRecipe> ingredientOnRecipeConverter;

    @Override
    public RecipeResponse convert(Recipe recipe) {

        return new RecipeResponse(this.ingredientOnRecipeConverter.convertAll(recipe.getIngredientOnRecipe()));
    }
}