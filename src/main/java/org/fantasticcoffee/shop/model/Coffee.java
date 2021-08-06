package org.fantasticcoffee.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fantasticcoffee.shop.model.ingredient.ChosenIngredientIngredientInStock;
import org.fantasticcoffee.shop.model.recipe.StandardRecipeInStock;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Coffee {

    private String coffeeName;
    private StandardRecipeInStock standardRecipe;
    private List<ChosenIngredientIngredientInStock> chosenIngredients;
    private double price;

    public Coffee(String coffeeName,
                  StandardRecipeInStock standardRecipe,
                  List<ChosenIngredientIngredientInStock> chosenIngredients,
                  double price) {
        this.coffeeName = coffeeName;
        this.standardRecipe = standardRecipe;
        this.chosenIngredients = chosenIngredients;
        this.price = price;
    }

    public Coffee(String coffeeName,
                  StandardRecipeInStock standardRecipe,
                  List<ChosenIngredientIngredientInStock> chosenIngredients) {
        this.coffeeName = coffeeName;
        this.standardRecipe = standardRecipe;
        this.chosenIngredients = chosenIngredients;
    }
}