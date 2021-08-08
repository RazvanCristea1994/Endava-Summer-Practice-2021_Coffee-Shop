package org.fantasticcoffee.shop.data.ingredient;

import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class IngredientChosenRequest {

    @NotNull(message = "You have to choose an ingredient")
    @NotBlank(message = "Ingredient name cannot be blank")
    private final String ingredient;

    @Min(value = 1, message = "The quantity must be at least 1")
    private final int numberOfShots;

    public IngredientChosenRequest(String ingredient, int numberOfShots) {
        this.ingredient = ingredient;
        this.numberOfShots = numberOfShots;
    }
}