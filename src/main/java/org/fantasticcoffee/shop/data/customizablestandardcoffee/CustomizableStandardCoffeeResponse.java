package org.fantasticcoffee.shop.data.customizablestandardcoffee;

import lombok.Getter;
import org.fantasticcoffee.shop.data.ingredient.IngredientOnRecipeResponse;
import org.fantasticcoffee.shop.model.coffee.StandardCoffee;

import java.util.List;

@Getter
public class CustomizableStandardCoffeeResponse {

    private final String customerName;
    private final StandardCoffee standardCoffee;
    private final List<IngredientOnRecipeResponse> extraIngredients;

    public CustomizableStandardCoffeeResponse(
            String customerName,
            StandardCoffee standardCoffee,
            List<IngredientOnRecipeResponse> extraIngredients) {
        this.customerName = customerName;
        this.standardCoffee = standardCoffee;
        this.extraIngredients = extraIngredients;
    }
}