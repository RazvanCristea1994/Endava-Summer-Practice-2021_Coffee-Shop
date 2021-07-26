package org.fantasticcoffee.shop.converter;

import org.fantasticcoffee.shop.model.ingredientonrecipe.ExtraIngredientOnRecipe;
import org.fantasticcoffee.shop.model.stock.ExtraIngredientInStock;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("extraIngredientOnRecipeConverter")
public class ExtraIngredientOnRecipeConverter {

    public List<ExtraIngredientInStock> recipeIngredientsToStockIngredients(List<ExtraIngredientOnRecipe> baseIngredientsOnRecipe) {

        List<ExtraIngredientInStock> baseIngredientInStock = new ArrayList<>();
        baseIngredientsOnRecipe.forEach(ingredient -> baseIngredientInStock.add(new ExtraIngredientInStock.Builder(ingredient.getExtraIngredient(), ingredient.getQuantity()).build()));

        return baseIngredientInStock;
    }
}
