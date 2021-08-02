package org.fantasticcoffee.shop.data.customcoffee;

import lombok.Getter;
import org.fantasticcoffee.shop.data.recipe.RecipeResponse;

@Getter
public class CustomCoffeeResponse {

    private final String customerName;
    private final RecipeResponse customerMadeRecipe;

    public CustomCoffeeResponse(String customerName, RecipeResponse customerMadeRecipe) {
        this.customerName = customerName;
        this.customerMadeRecipe = customerMadeRecipe;
    }
}