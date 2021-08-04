package org.fantasticcoffee.shop.service;

import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.fantasticcoffee.shop.model.coffee.StandardRecipe;
import org.fantasticcoffee.shop.model.coffee.CoffeeWithStandardRecipeBase;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;

import java.util.List;

public interface CoffeeService {

    Double getCoffeePrice(CustomCoffee customCoffee);


    Double getCoffeePrice(CoffeeWithStandardRecipeBase customCoffee);


    Double getCoffeeCost(CustomCoffee customCoffee);


    Double getCoffeeCost(CoffeeWithStandardRecipeBase customCoffee);


    Double getStandardCoffeePrice(StandardRecipe standardRecipe);


    List<IngredientOnRecipe> getAllIngredientsForCustomCoffee(CustomCoffee coffee);


    List<IngredientOnRecipe> getAllIngredientsForCoffeeWithStandardRecipeBase(CoffeeWithStandardRecipeBase coffee);
}