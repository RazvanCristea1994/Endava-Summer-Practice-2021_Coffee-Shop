package org.fantasticcoffee.shop.model;

public abstract class CoffeeBase extends AbstractEntity {

    protected CoffeeType coffeeType;

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }
}
