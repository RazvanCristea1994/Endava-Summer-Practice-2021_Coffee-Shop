package org.fantasticcoffee.shop.facade.standardrecipe;

import org.fantasticcoffee.shop.data.standardrecipeinstock.StandardRecipeInStockResponse;
import org.fantasticcoffee.shop.model.recipe.StandardRecipeInStock;

import java.util.List;

public interface StandardRecipeFacade {

    List<StandardRecipeInStockResponse> getStandardRecipeResponse(List<StandardRecipeInStock> standardRecipeInStock);
}