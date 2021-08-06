package org.fantasticcoffee.shop.service;

import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.model.ingredient.IngredientInStock;
import org.fantasticcoffee.shop.model.recipe.StandardRecipeInStock;

import java.util.List;
import java.util.Map;

public interface CoffeeService {

    Double getCoffeePrice(Coffee coffee);

    Double getCoffeeCost(Coffee coffee);

    Map<IngredientInStock, Integer> getAllCoffeeIngredients(Coffee coffee);

    List<StandardRecipeInStock> getStandardRecipeList();

    StandardRecipeInStock getCoffee(String standardCoffeeName);

    StandardRecipeInStock createStandardCoffee(String standardRecipeName);
}