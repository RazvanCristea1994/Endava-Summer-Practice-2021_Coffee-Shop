package org.fantasticcoffee.shop.model;

import java.util.Collection;

public class Coffee extends AbstractEntity {

    private String customerName;
    protected CoffeeType coffeeType;
    private Collection<Ingredient> extraIngredientList;

    public Coffee(String customerName, CoffeeType coffeeType, Collection<Ingredient> extraIngredientList) {
        this.coffeeType = coffeeType;
        this.customerName = customerName;
        this.extraIngredientList = extraIngredientList;
    }

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Collection<Ingredient> getExtraIngredientsList() {
        return extraIngredientList;
    }
}
