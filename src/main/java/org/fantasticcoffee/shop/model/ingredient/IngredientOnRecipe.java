package org.fantasticcoffee.shop.model.ingredient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientOnRecipe {

    Ingredient ingredient;
    int quantity;

    public IngredientOnRecipe(Ingredient ingredient, int quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }
}