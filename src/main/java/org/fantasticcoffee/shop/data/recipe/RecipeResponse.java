package org.fantasticcoffee.shop.data.recipe;

import lombok.Getter;
import org.fantasticcoffee.shop.data.ingredient.IngredientOnRecipeResponse;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RecipeResponse {

    private List<IngredientOnRecipeResponse> ingredients = new ArrayList<>();

    public RecipeResponse(List<IngredientOnRecipeResponse> ingredients) {
        this.ingredients = ingredients;
    }
}