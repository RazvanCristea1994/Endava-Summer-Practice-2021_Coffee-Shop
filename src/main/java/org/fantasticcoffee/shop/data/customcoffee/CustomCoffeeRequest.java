package org.fantasticcoffee.shop.data.customcoffee;

import lombok.Getter;
import org.fantasticcoffee.shop.model.Recipe;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class CustomCoffeeRequest {

    @NotNull(message = "Customer's name requested")
    @NotBlank(message = "Blank name is not allowed")
    @Pattern(regexp = "^[a-z '-]+$")
    private final String customerName;

    @Valid
    private final Recipe customerMadeRecipe;

    public CustomCoffeeRequest(String customerName, Recipe customerMadeRecipe) {
        this.customerName = customerName;
        this.customerMadeRecipe = customerMadeRecipe;
    }
}