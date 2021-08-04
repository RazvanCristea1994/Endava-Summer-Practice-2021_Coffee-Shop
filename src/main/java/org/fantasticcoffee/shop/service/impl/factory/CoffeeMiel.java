package org.fantasticcoffee.shop.service.impl.factory;

import org.fantasticcoffee.shop.model.coffee.StandardRecipe;
import org.fantasticcoffee.shop.model.coffee.CoffeeWithStandardRecipeBase;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;

import java.util.List;

public class CoffeeMiel implements StandardCoffeeFactory {

    @Override
    public CoffeeWithStandardRecipeBase create(String customerName, List<IngredientOnRecipe> extraIngredients) {
        return new CoffeeWithStandardRecipeBase(customerName, StandardRecipe.CAFFEE_MIEL, extraIngredients);
    }
}