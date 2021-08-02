package org.fantasticcoffee.shop.facade.converter.ingredients;

import org.fantasticcoffee.shop.data.ingredient.IngredientOnRecipeResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;
import org.fantasticcoffee.shop.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IngredientOnRecipeResponseConverter implements Converter<IngredientOnRecipeResponse, IngredientOnRecipe> {

    @Autowired
    private IngredientService ingredientService;

    @Override
    public IngredientOnRecipeResponse convert(IngredientOnRecipe ingredient) {
        return new IngredientOnRecipeResponse(ingredient.getIngredient(),
                ingredient.getQuantity(),
                this.ingredientService.getPriceForShots(ingredient));
    }
}