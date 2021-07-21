package org.fantasticcoffee.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Coffee extends AbstractEntity {

    private String customerName;
    private CoffeeRecipe additionalIngredientsForCustomCoffee;
    protected CoffeeType coffeeType;

    public Coffee(String customerName, CoffeeType coffeeType, CoffeeRecipe additionalIngredientsForCustomCoffee) {
        this.coffeeType = coffeeType;
        this.customerName = customerName;
        this.additionalIngredientsForCustomCoffee = additionalIngredientsForCustomCoffee;
    }

    @Override
    public String toString() {
        return coffeeType.getName() +
                "\t" + additionalIngredientsForCustomCoffee +
                '}';
    }
}
