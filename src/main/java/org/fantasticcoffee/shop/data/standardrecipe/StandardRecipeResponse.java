package org.fantasticcoffee.shop.data.standardrecipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.fantasticcoffee.shop.data.ingredient.IngredientOnStandardRecipeResponse;

import java.util.List;

@Getter
@AllArgsConstructor
public class StandardRecipeResponse {

    private final String standardRecipeName;
    private final List<IngredientOnStandardRecipeResponse> ingredientList;
}