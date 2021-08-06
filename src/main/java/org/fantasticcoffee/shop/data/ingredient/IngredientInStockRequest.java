package org.fantasticcoffee.shop.data.ingredient;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class IngredientInStockRequest {

    @NotNull(message = "You have to choose an ingredient")
    @NotBlank(message = "Ingredient name cannot be blank")
    private String name;
}