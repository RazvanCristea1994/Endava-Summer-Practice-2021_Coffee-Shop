package org.fantasticcoffee.shop.data.customizablestandardcoffee;

import lombok.Getter;
import org.fantasticcoffee.shop.model.StandardCoffee;
import org.fantasticcoffee.shop.model.ingredientonrecipe.ExtraIngredientOnRecipe;

import java.util.List;

@Getter
public class CustomizableStandardCoffeeResponse {

    private final String customerName;
    private final StandardCoffee standardCoffee;
    private final List<ExtraIngredientOnRecipe> extraIngredients;

    public CustomizableStandardCoffeeResponse(
            String customerName,
            StandardCoffee standardCoffee,
            List<ExtraIngredientOnRecipe> extraIngredients) {
        this.customerName = customerName;
        this.standardCoffee = standardCoffee;
        this.extraIngredients = extraIngredients;
    }
}