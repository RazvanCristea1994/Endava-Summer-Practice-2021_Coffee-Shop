package org.fantasticcoffee.shop.facade.converter.recipe;

import org.fantasticcoffee.shop.data.ingredient.IngredientOnRecipeRequest;
import org.fantasticcoffee.shop.data.recipe.RecipeRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Recipe;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecipeConverter implements Converter<Recipe, RecipeRequest> {

    @Autowired
    Converter<IngredientOnRecipe, IngredientOnRecipeRequest> ingredientOnRecipeConverter;

    @Override
    public Recipe convert(RecipeRequest recipeRequest) {

        Recipe.Builder recipe = new Recipe.Builder();

        if (!recipeRequest.getIngredients().isEmpty()) {
            recipe.setIngredientsConfig(this.ingredientOnRecipeConverter.convertAll(recipeRequest.getIngredients()));
        }

        return recipe.build();
    }
}