package org.fantasticcoffee.shop.data.customcoffee;

import lombok.Getter;
import org.fantasticcoffee.shop.data.ingredient.ChosenIngredientResponse;
import org.fantasticcoffee.shop.data.standardrecipeinstock.StandardRecipeInStockResponse;

import java.util.List;

@Getter
public class CoffeeResponse {

    private final String customerName;
    private StandardRecipeInStockResponse standardRecipe;
    private List<ChosenIngredientResponse> chosenIngredients;
    private double price;

    public CoffeeResponse(String customerName,
                          StandardRecipeInStockResponse standardRecipe,
                          List<ChosenIngredientResponse> chosenIngredients,
                          double price) {
        this.customerName = customerName;
        this.standardRecipe = standardRecipe;
        this.chosenIngredients = chosenIngredients;
        this.price = price;
    }
}