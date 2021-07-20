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
    private Collection<Ingredient> ingredientsList;
    protected CoffeeType coffeeType;

    public Coffee(String customerName, CoffeeType coffeeType, Collection<Ingredient> ingredientsList) {
        this.coffeeType = coffeeType;
        this.customerName = customerName;
        this.ingredientsList = ingredientsList;
    }

    @Override
    public String toString() {
        return coffeeType.getName() +
                "\t" + ingredientsList +
                '}';
    }
}
