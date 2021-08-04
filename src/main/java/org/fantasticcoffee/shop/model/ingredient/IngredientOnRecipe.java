package org.fantasticcoffee.shop.model.ingredient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientOnRecipe {

    Ingredient ingredient;
    int numberOfShots;

    public IngredientOnRecipe(Ingredient ingredient, int numberOfShots) {
        this.ingredient = ingredient;
        this.numberOfShots = numberOfShots;
    }
}