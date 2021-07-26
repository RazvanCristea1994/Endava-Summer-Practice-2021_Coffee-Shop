package org.fantasticcoffee.shop.model.ingredientonrecipe;

import lombok.Getter;
import lombok.Setter;
import org.fantasticcoffee.shop.model.ingredientdefinition.BaseIngredient;

@Getter
@Setter
public class BaseIngredientOnRecipe {

    BaseIngredient baseIngredient;
    int quantity;

    public BaseIngredientOnRecipe(BaseIngredient baseIngredient, int quantity) {
        this.baseIngredient = baseIngredient;
        this.quantity = quantity;
    }
}