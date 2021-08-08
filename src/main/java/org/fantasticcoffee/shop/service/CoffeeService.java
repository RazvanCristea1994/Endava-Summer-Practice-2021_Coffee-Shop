package org.fantasticcoffee.shop.service;

import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.model.Ingredient;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.StandardRecipe;

import java.util.List;
import java.util.Map;

public interface CoffeeService {

    void save(Order order);

    Double getCoffeePrice(Coffee coffee);

    Double getCoffeeCost(Coffee coffee);

    Map<Ingredient, Integer> getAllCoffeeIngredients(Coffee coffee);

    List<StandardRecipe> getStandardRecipeList();

    StandardRecipe getCoffee(String standardCoffeeName);

    StandardRecipe createStandardCoffee(String standardRecipeName);
}