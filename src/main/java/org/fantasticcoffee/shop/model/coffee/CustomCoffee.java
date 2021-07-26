package org.fantasticcoffee.shop.model.coffee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fantasticcoffee.shop.model.Recipe;

@Getter
@Setter
@NoArgsConstructor
public class CustomCoffee extends BaseCoffee {

    private Recipe customerMadeRecipe;

    public CustomCoffee(String customerName, Recipe customerMadeRecipe) {
        this.setCustomerName(customerName);
        this.customerMadeRecipe = customerMadeRecipe;
    }
}