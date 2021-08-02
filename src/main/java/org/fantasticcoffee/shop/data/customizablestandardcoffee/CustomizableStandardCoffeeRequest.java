package org.fantasticcoffee.shop.data.customizablestandardcoffee;

import lombok.Getter;
import org.fantasticcoffee.shop.data.ingredient.IngredientOnRecipeRequest;
import org.fantasticcoffee.shop.model.coffee.StandardCoffee;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
public class CustomizableStandardCoffeeRequest {

    @NotNull(message = "Customer's name requested")
    @NotBlank(message = "Customer name requested")
    @Pattern(regexp = "^[a-zA-Z '-]+$", message = "Wrong name format")
    private final String customerName;

    @NotNull(message = "Standard Coffee requested")
    private final StandardCoffee standardCoffee;

    private final List<IngredientOnRecipeRequest> extraIngredients;

    public CustomizableStandardCoffeeRequest(
            String customerName,
            StandardCoffee standardCoffee,
            List<IngredientOnRecipeRequest> extraIngredients) {
        this.customerName = customerName;
        this.standardCoffee = standardCoffee;
        this.extraIngredients = extraIngredients;
    }
}