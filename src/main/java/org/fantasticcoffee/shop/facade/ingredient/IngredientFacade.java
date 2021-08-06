package org.fantasticcoffee.shop.facade.ingredient;

import org.fantasticcoffee.shop.data.ingredient.IngredientInStockResponse;
import org.fantasticcoffee.shop.model.ingredient.IngredientInStock;

import java.util.List;

public interface IngredientFacade {

    List<IngredientInStockResponse> getIngredientInStockResponse(List<IngredientInStock> ingredientInStock);
}