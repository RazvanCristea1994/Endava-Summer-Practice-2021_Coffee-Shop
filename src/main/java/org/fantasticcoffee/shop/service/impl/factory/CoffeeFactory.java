package org.fantasticcoffee.shop.service.impl.factory;

import org.fantasticcoffee.shop.model.coffee.StandardRecipe;

public class CoffeeFactory {

    private CoffeeFactory() {
    }

    public static StandardCoffeeFactory createStandardCoffee(StandardRecipe standardRecipe) {

        if (standardRecipe == null) {
            return null;
        }

        return switch (standardRecipe) {
            case ESPRESSO -> new Espresso();
            case MACHIATTO -> new Machiatto();
            case CAFFEE_LATTE -> new CoffeeLatte();
            case CAPPUCCINO -> new Cappuccino();
            case CAFFEE_MIEL -> new CoffeeMiel();
        };
    }
}