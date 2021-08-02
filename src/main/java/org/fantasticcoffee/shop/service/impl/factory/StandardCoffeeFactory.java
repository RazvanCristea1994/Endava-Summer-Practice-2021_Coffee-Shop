package org.fantasticcoffee.shop.service.impl.factory;

import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;

import java.util.List;

public interface StandardCoffeeFactory {

    CustomizableStandardCoffee create(String customerName, List<IngredientOnRecipe> extraIngredients);
}