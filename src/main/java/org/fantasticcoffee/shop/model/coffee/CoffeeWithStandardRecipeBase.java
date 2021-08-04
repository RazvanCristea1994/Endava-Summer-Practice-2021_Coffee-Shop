package org.fantasticcoffee.shop.model.coffee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CoffeeWithStandardRecipeBase {

    private String customerName;
    private StandardRecipe standardRecipe;
    private List<IngredientOnRecipe> extraIngredients;

    public CoffeeWithStandardRecipeBase(String customerName,
                                        StandardRecipe standardRecipe,
                                        List<IngredientOnRecipe> extraIngredients) {
        this.setCustomerName(customerName);
        this.standardRecipe = standardRecipe;
        this.extraIngredients = extraIngredients;
    }
}