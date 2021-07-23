package org.fantasticcoffee.shop.model.ingredientonrecipe;

import lombok.Getter;
import lombok.Setter;
import org.fantasticcoffee.shop.model.ingredientdefinition.ExtraIngredientDefinition;

@Getter
@Setter
public class ExtraIngredientOnRecipe {

    ExtraIngredientDefinition extraIngredient;
    int quantity;

    public ExtraIngredientOnRecipe(ExtraIngredientDefinition extraIngredient, int quantity) {
        this.extraIngredient = extraIngredient;
        this.quantity = quantity;
    }
}
