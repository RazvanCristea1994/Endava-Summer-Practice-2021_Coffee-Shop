package org.fantasticcoffee.shop.data.customcoffee;

import lombok.Getter;
import org.fantasticcoffee.shop.data.ingredient.ChosenIngredientRequest;
import org.fantasticcoffee.shop.data.standardrecipeinstock.StandardRecipeInStockRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CoffeeRequest {

    @NotNull(message = "Customer's name requested")
    @NotBlank(message = "Blank name is not allowed")
    @Pattern(regexp = "^[a-zA-Z '-]+$", message = "Wrong name format")
    private final String coffeeName;

    @Valid
    private StandardRecipeInStockRequest standardRecipe;

    @Valid
    private final List<ChosenIngredientRequest> chosenIngredients;

    public CoffeeRequest(
            String coffeeName,
            StandardRecipeInStockRequest standardRecipe,
            List<ChosenIngredientRequest> chosenIngredients
    ) {
        this.coffeeName = coffeeName;
        this.standardRecipe = (standardRecipe == null ? new StandardRecipeInStockRequest("CUSTOM") : standardRecipe);
        this.chosenIngredients = (chosenIngredients == null ? new ArrayList<>() : chosenIngredients);
    }
}