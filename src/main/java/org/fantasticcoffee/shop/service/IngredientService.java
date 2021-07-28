package org.fantasticcoffee.shop.service;

import org.fantasticcoffee.shop.model.ingredientonrecipe.BaseIngredientOnRecipe;
import org.fantasticcoffee.shop.model.ingredientonrecipe.ExtraIngredientOnRecipe;
import org.fantasticcoffee.shop.model.stock.BaseIngredientInStock;
import org.fantasticcoffee.shop.model.stock.ExtraIngredientInStock;

import java.util.List;

public interface IngredientService {

    void decrementExtraIngredient(List<ExtraIngredientOnRecipe> extraIngredientToChange);

    void decrementBaseIngredient(List<BaseIngredientOnRecipe> baseIngredientToChange);

    public List<ExtraIngredientInStock> getAllExtraIngredientsInStock();

    public List<BaseIngredientInStock> getAllBaseIngredientsInStock();
}