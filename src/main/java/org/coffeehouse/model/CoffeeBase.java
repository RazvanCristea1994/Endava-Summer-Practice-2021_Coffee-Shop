package org.coffeehouse.model;

public abstract class CoffeeBase<ID> extends AbstractEntity<ID> {

    private CoffeeType coffeeType;

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(CoffeeType coffeeType) {
        this.coffeeType = coffeeType;
    }
}
