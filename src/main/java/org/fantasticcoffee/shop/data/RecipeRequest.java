package org.fantasticcoffee.shop.data;

import lombok.Getter;
import org.fantasticcoffee.shop.model.ingredientonrecipe.BaseIngredientOnRecipe;
import org.fantasticcoffee.shop.model.ingredientonrecipe.ExtraIngredientOnRecipe;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
public class RecipeRequest {

    @NotEmpty(message = "Base Ingredients are required")
    private List<BaseIngredientOnRecipe> baseIngredients = new ArrayList<>();
    private List<ExtraIngredientOnRecipe> extraIngredients = new ArrayList<>();
}