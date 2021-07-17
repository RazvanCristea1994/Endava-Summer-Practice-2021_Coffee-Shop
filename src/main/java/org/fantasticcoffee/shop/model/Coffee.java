package org.fantasticcoffee.shop.model;

import java.util.ArrayList;
import java.util.Collection;

public class Coffee extends CoffeeBase {

    private String customerName;
    private Collection<Ingredient> extraIngredientList;

    public Coffee(String customerName, CoffeeType coffeeType, Collection<Ingredient> extraIngredientList) {
        this.coffeeType = coffeeType;
        this.customerName = customerName;
        this.extraIngredientList = extraIngredientList;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Collection<Ingredient> getExtraIngredientsList() {
        return extraIngredientList;
    }
}
