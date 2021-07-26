package org.fantasticcoffee.shop.service.impl.factory;

import org.fantasticcoffee.shop.model.StandardCoffee;
import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;
import org.fantasticcoffee.shop.model.ingredientonrecipe.ExtraIngredientOnRecipe;

import java.util.List;

public class DefaultCappuccino implements StandardCoffeeFactory{

    @Override
    public CustomizableStandardCoffee create(String name,  List<ExtraIngredientOnRecipe> extraIngredients) {
        return new CustomizableStandardCoffee(name, StandardCoffee.CAPPUCCINO, extraIngredients);
    }
}
