package org.fantasticcoffee.shop.service;

import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.JoinClasses.CoffeeIngredient;
import org.fantasticcoffee.shop.model.Ingredient;

import java.util.List;
import java.util.Map;

public interface IngredientService {

    void decrementIngredient(Map<Ingredient, Integer> ingredientToChange);

    List<Ingredient> getAllIngredients();

    Double getPriceForShots(CoffeeIngredient ingredient);

    Map<Ingredient, Integer> checkIngredientsForOrder(Order order);

    Ingredient getByName(String ingredient);
}