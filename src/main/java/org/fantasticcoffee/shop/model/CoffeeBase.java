package org.fantasticcoffee.shop.model;

public abstract class CoffeeBase extends AbstractEntity {

    private CoffeeType coffeeType;

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(CoffeeType coffeeType) {
        this.coffeeType = coffeeType;
    }
}
