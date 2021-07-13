package org.coffeehouse.model;

public abstract class CoffeeBase<ID> {

    protected ID id;
    private CoffeeType coffeeType;
    private WhereToDrink whereToDrink;

    public enum WhereToDrink {
        PICK_UP, TO_GO
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
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
