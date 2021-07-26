package org.fantasticcoffee.shop.model.coffee;

import lombok.Getter;
import lombok.Setter;
import org.fantasticcoffee.shop.model.StandardCoffee;
import org.fantasticcoffee.shop.model.ingredientonrecipe.ExtraIngredientOnRecipe;

import java.util.List;

@Getter
@Setter
public class CustomizableStandardCoffee {

    private String customerName;
    private StandardCoffee standardCoffee;
    private List<ExtraIngredientOnRecipe> extraIngredients;

    public CustomizableStandardCoffee(String customerName, StandardCoffee standardCoffee, List<ExtraIngredientOnRecipe> extraIngredients) {
        this.setCustomerName(customerName);
        this.standardCoffee = standardCoffee;
        this.extraIngredients = extraIngredients;
    }
}