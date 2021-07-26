package org.fantasticcoffee.shop.service;

import org.fantasticcoffee.shop.model.ingredientdefinition.ExtraIngredient;
import org.fantasticcoffee.shop.model.stock.ExtraIngredientInStock;

import java.util.List;

public interface ExtraIngredientService {

    void seedStock();

    void decrementExtraIngredient(List<ExtraIngredientInStock> extraIngredientToChange);

    public List<ExtraIngredientInStock> getAllExtraIngredientsInStock();

    List<ExtraIngredient> findAll();

    ExtraIngredient find(Integer id);

    ExtraIngredient update(ExtraIngredient eIngredient);

    ExtraIngredient delete(Integer id);
}