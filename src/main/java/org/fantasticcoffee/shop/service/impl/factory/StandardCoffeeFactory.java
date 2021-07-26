package org.fantasticcoffee.shop.service.impl.factory;

import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;
import org.fantasticcoffee.shop.model.ingredientonrecipe.ExtraIngredientOnRecipe;

import java.util.List;

public interface StandardCoffeeFactory {

    CustomizableStandardCoffee create(String customerName, List<ExtraIngredientOnRecipe> extraIngredients);
}