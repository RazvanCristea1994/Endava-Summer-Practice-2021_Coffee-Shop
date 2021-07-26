package org.fantasticcoffee.shop.service;

import org.fantasticcoffee.shop.model.ingredientdefinition.BaseIngredient;
import org.fantasticcoffee.shop.model.ingredientonrecipe.BaseIngredientOnRecipe;
import org.fantasticcoffee.shop.model.stock.BaseIngredientInStock;

import java.util.List;

public interface BaseIngredientService {

    void seedStock();

    void decrementBaseIngredient(List<BaseIngredientOnRecipe> baseIngredientToChange);

    public List<BaseIngredientInStock> getAllBaseIngredientsInStock();

    List<BaseIngredient> findAll();

    BaseIngredient find(Integer id);

    BaseIngredient update(BaseIngredient bIngredient);

    BaseIngredient delete(Integer id);
}