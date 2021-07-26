package org.fantasticcoffee.shop.model.ingredientonrecipe;

import lombok.Getter;
import lombok.Setter;
import org.fantasticcoffee.shop.model.ingredientdefinition.ExtraIngredient;

@Getter
@Setter
public class ExtraIngredientOnRecipe {

    ExtraIngredient extraIngredient;
    int quantity;

    public ExtraIngredientOnRecipe(ExtraIngredient extraIngredient, int quantity) {
        this.extraIngredient = extraIngredient;
        this.quantity = quantity;
    }
}