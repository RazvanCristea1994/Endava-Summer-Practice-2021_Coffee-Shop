package org.fantasticcoffee.shop.converter;

import org.fantasticcoffee.shop.model.ingredientonrecipe.BaseIngredientOnRecipe;
import org.fantasticcoffee.shop.model.stock.BaseIngredientInStock;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("baseIngredientOnRecipeConverter")
public class BaseIngredientOnRecipeConverter {

    public List<BaseIngredientInStock> recipeIngredientsToStockIngredients(List<BaseIngredientOnRecipe> baseIngredientsOnRecipe) {

        List<BaseIngredientInStock> baseIngredientInStock = new ArrayList<>();
        baseIngredientsOnRecipe.forEach(ingredient -> baseIngredientInStock.add(new BaseIngredientInStock.Builder(ingredient.getBaseIngredient(), ingredient.getQuantity()).build()));

        return baseIngredientInStock;
    }
}