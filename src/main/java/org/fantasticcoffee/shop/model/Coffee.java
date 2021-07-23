package org.fantasticcoffee.shop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Coffee {

    private Integer id;
    private String customerName;
    private CoffeeRecipe additionalIngredientsForCustomCoffee;
    protected CoffeeType coffeeType;

    public Coffee(String customerName, CoffeeType coffeeType, CoffeeRecipe additionalIngredientsForCustomCoffee) {
        this.coffeeType = coffeeType;
        this.customerName = customerName;
        this.additionalIngredientsForCustomCoffee = additionalIngredientsForCustomCoffee;
    }
}