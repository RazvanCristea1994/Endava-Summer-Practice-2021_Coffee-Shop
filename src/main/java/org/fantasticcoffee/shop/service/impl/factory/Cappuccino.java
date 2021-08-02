package org.fantasticcoffee.shop.service.impl.factory;

import org.fantasticcoffee.shop.model.coffee.StandardCoffee;
import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;

import java.util.List;

public class Cappuccino implements StandardCoffeeFactory {

    @Override
    public CustomizableStandardCoffee create(String customerName, List<IngredientOnRecipe> extraIngredients) {
        return new CustomizableStandardCoffee(customerName, StandardCoffee.CAPPUCCINO, extraIngredients);
    }
}