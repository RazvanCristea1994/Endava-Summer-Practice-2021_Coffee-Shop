package org.fantasticcoffee.shop.data.customcoffee;

import lombok.Getter;
import org.fantasticcoffee.shop.data.recipe.RecipeRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class CustomCoffeeRequest {

    @NotNull(message = "Customer's name requested")
    @NotBlank(message = "Blank name is not allowed")
    @Pattern(regexp = "^[a-zA-Z '-]+$", message = "Wrong name format")
    private final String customerName;

    @Valid
    private final RecipeRequest customerMadeRecipe;

    public CustomCoffeeRequest(
            String customerName, RecipeRequest customerMadeRecipe) {
        this.customerName = customerName;
        this.customerMadeRecipe = customerMadeRecipe;
    }
}