package org.fantasticcoffee.shop.facade.ingredient;

import org.fantasticcoffee.shop.data.ingredient.IngredientDetailedResponse;
import org.fantasticcoffee.shop.model.Ingredient;

import java.util.List;

public interface IngredientFacade {

    List<IngredientDetailedResponse> getIngredientResponse(List<Ingredient> ingredient);
}