package org.fantasticcoffee.shop.data.recipe;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.fantasticcoffee.shop.data.ingredient.IngredientOnRecipeRequest;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
public class RecipeRequest {

    @JsonProperty("ingredients")
    @NotEmpty(message = "Ingredients are required")
    private List<IngredientOnRecipeRequest> ingredients;

    @JsonCreator
    public RecipeRequest(List<IngredientOnRecipeRequest> ingredients) {
        this.ingredients = ingredients;
    }
}