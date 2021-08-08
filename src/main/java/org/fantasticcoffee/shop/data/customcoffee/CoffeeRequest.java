package org.fantasticcoffee.shop.data.customcoffee;

import lombok.Getter;
import org.fantasticcoffee.shop.data.ingredient.IngredientChosenRequest;
import org.fantasticcoffee.shop.data.standardrecipe.StandardRecipeRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CoffeeRequest {

    @NotNull(message = "Customer's name requested ")
    @Pattern(regexp = "^[a-zA-Z0-9 .'-]+$", message = "Wrong name format")
    private final String coffeeName;

    @Valid
    private final StandardRecipeRequest standardRecipe;

    @Valid
    private final List<IngredientChosenRequest> chosenIngredients;

    public CoffeeRequest(
            String coffeeName,
            StandardRecipeRequest standardRecipe,
            List<IngredientChosenRequest> chosenIngredients
    ) {
        this.coffeeName = coffeeName;
        this.standardRecipe = (standardRecipe == null ? new StandardRecipeRequest("CUSTOM") : standardRecipe);
        this.chosenIngredients = (chosenIngredients == null ? new ArrayList<>() : chosenIngredients);

        /*if ("CUSTOM".equals(this.standardRecipe.getStandardRecipeName()) && this.chosenIngredients.isEmpty()) {
            throw new IllegalArgumentException("You cannot order an empty coffee");
        }*/
    }
}