package org.fantasticcoffee.shop.data.customizablestandardcoffee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fantasticcoffee.shop.model.StandardCoffee;
import org.fantasticcoffee.shop.model.ingredientonrecipe.ExtraIngredientOnRecipe;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CustomizableStandardCoffeeRequest {

    private String customerName;
    private StandardCoffee standardCoffee;
    private List<ExtraIngredientOnRecipe> extraIngredients;
}