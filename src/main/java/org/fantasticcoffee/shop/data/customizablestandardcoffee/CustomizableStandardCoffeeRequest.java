package org.fantasticcoffee.shop.data.customizablestandardcoffee;

import lombok.Getter;
import org.fantasticcoffee.shop.model.StandardCoffee;
import org.fantasticcoffee.shop.model.ingredientonrecipe.ExtraIngredientOnRecipe;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
public class CustomizableStandardCoffeeRequest {

    @NotNull(message = "Customer's name requested")
    @NotBlank(message = "Customer name requested")
    @Pattern(regexp = "^[a-z '-]+$", message = "Wrong name format")
    private final String customerName;

    @NotNull(message = "Standard Coffee requested")
    private final StandardCoffee standardCoffee;

    private final List<ExtraIngredientOnRecipe> extraIngredients;

    public CustomizableStandardCoffeeRequest(
            String customerName,
            StandardCoffee standardCoffee,
            List<ExtraIngredientOnRecipe> extraIngredients) {
        this.customerName = customerName;
        this.standardCoffee = standardCoffee;
        this.extraIngredients = extraIngredients;
    }
}