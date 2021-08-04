package org.fantasticcoffee.shop.service.impl.factory;

import org.fantasticcoffee.shop.model.coffee.CoffeeWithStandardRecipeBase;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;

import java.util.List;

public interface StandardCoffeeFactory {

    CoffeeWithStandardRecipeBase create(String customerName, List<IngredientOnRecipe> extraIngredients);
}