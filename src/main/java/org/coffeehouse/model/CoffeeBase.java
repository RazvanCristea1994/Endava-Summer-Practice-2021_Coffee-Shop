package org.coffeehouse.model;

public abstract class CoffeeBase<ID> extends AbstractEntity<ID> {

    private CoffeeType coffeeType;
    private WhereToDrink whereToDrink;

    public enum WhereToDrink {
        PICK_UP, TO_GO
    }

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(CoffeeType coffeeType) {
        this.coffeeType = coffeeType;
    }

    public WhereToDrink getWhereToDrink() {
        return whereToDrink;
    }

    public void setWhereToDrink(WhereToDrink whereToDrink) {
        this.whereToDrink = whereToDrink;
    }
}
