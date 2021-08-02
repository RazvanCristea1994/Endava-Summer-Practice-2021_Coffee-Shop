package org.fantasticcoffee.shop.model.coffee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomizableStandardCoffee {

    private String customerName;
    private StandardCoffee standardCoffee;
    private List<IngredientOnRecipe> extraIngredients;

    public CustomizableStandardCoffee(String customerName,
                                      StandardCoffee standardCoffee,
                                      List<IngredientOnRecipe> extraIngredients) {
        this.setCustomerName(customerName);
        this.standardCoffee = standardCoffee;
        this.extraIngredients = extraIngredients;
    }
}