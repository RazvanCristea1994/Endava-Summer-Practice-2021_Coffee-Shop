package org.fantasticcoffee.shop.service;

import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.ingredient.ChosenIngredientIngredientInStock;
import org.fantasticcoffee.shop.model.ingredient.IngredientInStock;

import java.util.List;
import java.util.Map;

public interface IngredientService {

    void decrementIngredient(Map<IngredientInStock, Integer> ingredientToChange);

    List<IngredientInStock> getAllIngredientsInStock();

    Double getPriceForShots(ChosenIngredientIngredientInStock ingredient);

    Map<IngredientInStock, Integer> checkIngredientInStockForOrder(Order order);

    IngredientInStock getByName(String ingredientInStockName);
}