package org.fantasticcoffee.shop.data.customcoffee;

import lombok.Getter;
import org.fantasticcoffee.shop.model.Recipe;

@Getter
public class CustomCoffeeResponse {

    private final String customerName;
    private final Recipe customerMadeRecipe;

    public CustomCoffeeResponse(String customerName, Recipe customerMadeRecipe) {
        this.customerName = customerName;
        this.customerMadeRecipe = customerMadeRecipe;
    }
}