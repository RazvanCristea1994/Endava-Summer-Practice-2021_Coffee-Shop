package org.fantasticcoffee.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class Coffee extends AbstractEntity {

    private String customerName;
    private Collection<Ingredient> extraIngredientsList;
    protected CoffeeType coffeeType;

    public Coffee(String customerName, CoffeeType coffeeType, Collection<Ingredient> extraIngredientsList) {
        this.coffeeType = coffeeType;
        this.customerName = customerName;
        this.extraIngredientsList = extraIngredientsList;
    }
}
