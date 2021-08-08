package org.fantasticcoffee.shop.data.customcoffee;

import lombok.Getter;
import org.fantasticcoffee.shop.data.ingredient.IngredientChosenResponse;
import org.fantasticcoffee.shop.data.standardrecipe.StandardRecipeResponse;

import java.util.List;

@Getter
public class CoffeeResponse {

    private final String customerName;
    private final StandardRecipeResponse standardRecipe;
    private final List<IngredientChosenResponse> chosenIngredients;
    private final double price;

    public CoffeeResponse(String customerName,
                          StandardRecipeResponse standardRecipe,
                          List<IngredientChosenResponse> chosenIngredients,
                          double price) {
        this.customerName = customerName;
        this.standardRecipe = standardRecipe;
        this.chosenIngredients = chosenIngredients;
        this.price = price;
    }
}