package org.fantasticcoffee.shop.service;

import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;
import org.fantasticcoffee.shop.model.ingredient.IngredientInStock;

import java.util.List;

public interface IngredientService {

    void decrementIngredient(List<IngredientOnRecipe> ingredientToChange);

    List<IngredientInStock> getAllIngredientsInStock();

    Double getPriceForShots(IngredientOnRecipe ingredient);
}