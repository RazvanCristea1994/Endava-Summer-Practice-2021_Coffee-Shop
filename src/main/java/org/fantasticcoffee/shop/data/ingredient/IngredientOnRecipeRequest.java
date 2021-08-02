package org.fantasticcoffee.shop.data.ingredient;

import lombok.Getter;
import org.fantasticcoffee.shop.model.ingredient.Ingredient;

import javax.validation.constraints.Min;

@Getter
public class IngredientOnRecipeRequest {

    Ingredient ingredient;

    @Min(value = 1, message = "The quantity must be at least 1")
    int numberOfShots;

    public IngredientOnRecipeRequest(Ingredient ingredient, int numberOfShots) {
        this.ingredient = ingredient;
        this.numberOfShots = numberOfShots;
    }
}