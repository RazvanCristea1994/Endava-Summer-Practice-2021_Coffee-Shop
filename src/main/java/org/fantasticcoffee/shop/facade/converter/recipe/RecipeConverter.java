package org.fantasticcoffee.shop.facade.converter.recipe;

import org.fantasticcoffee.shop.data.RecipeRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Recipe;
import org.springframework.stereotype.Component;

@Component
public class RecipeConverter implements Converter<Recipe, RecipeRequest> {

    @Override
    public Recipe convert(RecipeRequest recipeRequest) {

        Recipe.Builder recipe = new Recipe.Builder();

        if (!recipeRequest.getBaseIngredients().isEmpty()) {
            recipe.setBaseIngredientsConfig(recipeRequest.getBaseIngredients());
        }

        if (!recipeRequest.getExtraIngredients().isEmpty()) {
            recipe.setExtraIngredientConfig(recipeRequest.getExtraIngredients());
        }

        return recipe.build();
    }
}