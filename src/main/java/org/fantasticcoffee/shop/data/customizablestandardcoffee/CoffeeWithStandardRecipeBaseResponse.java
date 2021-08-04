package org.fantasticcoffee.shop.data.customizablestandardcoffee;

import lombok.Getter;
import org.fantasticcoffee.shop.data.ingredient.IngredientOnRecipeResponse;
import org.fantasticcoffee.shop.model.coffee.StandardRecipe;

import java.util.List;

@Getter
public class CoffeeWithStandardRecipeBaseResponse {

    private final String customerName;
    private final StandardRecipe standardRecipe;
    private final List<IngredientOnRecipeResponse> extraIngredients;

    public CoffeeWithStandardRecipeBaseResponse(
            String customerName,
            StandardRecipe standardRecipe,
            List<IngredientOnRecipeResponse> extraIngredients) {
        this.customerName = customerName;
        this.standardRecipe = standardRecipe;
        this.extraIngredients = extraIngredients;
    }
}