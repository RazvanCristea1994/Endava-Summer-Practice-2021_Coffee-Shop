package org.fantasticcoffee.shop.data.ingredient;

import lombok.Getter;
import org.fantasticcoffee.shop.model.ingredient.Ingredient;

@Getter
public class IngredientOnRecipeResponse {

    private Ingredient ingredient;
    private int numberOfShots;
    private Double shotsPrice;

    public IngredientOnRecipeResponse(Ingredient ingredient, int numberOfShots, Double shotsPrice) {
        this.ingredient = ingredient;
        this.numberOfShots = numberOfShots;
        this.shotsPrice = shotsPrice;
    }
}