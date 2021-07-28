package org.fantasticcoffee.shop.data.customcoffee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fantasticcoffee.shop.model.Recipe;

@Getter
@Setter
@NoArgsConstructor
public class CustomCoffeeRequest {

    private String customerName;
    private Recipe customerMadeRecipe;
}