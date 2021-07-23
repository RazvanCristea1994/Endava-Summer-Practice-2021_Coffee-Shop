package org.fantasticcoffee.shop.model.ingredientonrecipe;

import lombok.Getter;
import lombok.Setter;
import org.fantasticcoffee.shop.model.ingredientdefinition.BaseIngredientDefinition;

@Getter
@Setter
public class BaseIngredientOnRecipe {

    BaseIngredientDefinition baseIngredient;
    int quantity;

    public BaseIngredientOnRecipe(BaseIngredientDefinition baseIngredient, int quantity) {
        this.baseIngredient = baseIngredient;
        this.quantity = quantity;
    }
}
