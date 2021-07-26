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
            case ESPRESSO -> new DefaultEspresso();
            case MACHIATTO -> new DefaultMachiatto();
            case CAFFEE_LATTE -> new DefaultCaffeeLatte();
            case CAPPUCCINO -> new DefaultCappuccino();
            case CAFFEE_MIEL -> new DefaultCaffeeMiel();
        };
    }
}