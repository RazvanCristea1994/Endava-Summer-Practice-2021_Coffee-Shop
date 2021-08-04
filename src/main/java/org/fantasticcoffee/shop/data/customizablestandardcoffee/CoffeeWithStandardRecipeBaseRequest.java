package org.fantasticcoffee.shop.data.customizablestandardcoffee;

import lombok.Getter;
import org.fantasticcoffee.shop.data.ingredient.IngredientOnRecipeRequest;
import org.fantasticcoffee.shop.model.coffee.StandardRecipe;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
public class CoffeeWithStandardRecipeBaseRequest {

    @NotNull(message = "Customer's name requested")
    @NotBlank(message = "Customer name requested")
    @Pattern(regexp = "^[a-zA-Z '-]+$", message = "Wrong name format")
    private final String customerName;

    @NotNull(message = "Standard Coffee requested")
    private final StandardRecipe standardRecipe;

    private final List<IngredientOnRecipeRequest> extraIngredients;

    public CoffeeWithStandardRecipeBaseRequest(
            String customerName,
            StandardRecipe standardRecipe,
            List<IngredientOnRecipeRequest> extraIngredients) {
        this.customerName = customerName;
        this.standardRecipe = standardRecipe;
        this.extraIngredients = extraIngredients;
    }
}