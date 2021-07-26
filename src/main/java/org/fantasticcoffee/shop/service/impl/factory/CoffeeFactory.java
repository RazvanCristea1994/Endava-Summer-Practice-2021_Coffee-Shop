package org.fantasticcoffee.shop.service.impl.factory;

import org.fantasticcoffee.shop.model.StandardCoffee;

public class CoffeeFactory {

    private CoffeeFactory() {
    }

    public static StandardCoffeeFactory createStandardCoffee(StandardCoffee standardCoffee) {

        if (standardCoffee == null) {
            return null;
        }

        return switch (standardCoffee) {
            case ESPRESSO -> new Espresso();
            case MACHIATTO -> new Machiatto();
            case CAFFEE_LATTE -> new CoffeeLatte();
            case CAPPUCCINO -> new Cappuccino();
            case CAFFEE_MIEL -> new CoffeeMiel();
        };
    }
}