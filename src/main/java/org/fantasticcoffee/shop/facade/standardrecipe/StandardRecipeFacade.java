package org.fantasticcoffee.shop.facade.standardrecipe;

import org.fantasticcoffee.shop.data.standardrecipe.StandardRecipeResponse;
import org.fantasticcoffee.shop.model.StandardRecipe;

import java.util.List;

public interface StandardRecipeFacade {

    List<StandardRecipeResponse> getStandardRecipeResponse(List<StandardRecipe> standardRecipe);
}