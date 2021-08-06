package org.fantasticcoffee.shop.facade.converter.ingredients;

import org.fantasticcoffee.shop.data.ingredient.ChosenIngredientResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.ingredient.ChosenIngredientIngredientInStock;
import org.fantasticcoffee.shop.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChosenIngredientIngredientInStockResponseConverter implements Converter<ChosenIngredientResponse, ChosenIngredientIngredientInStock> {

    @Autowired
    private IngredientService ingredientService;

    @Override
    public ChosenIngredientResponse convert(ChosenIngredientIngredientInStock ingredient) {
        return new ChosenIngredientResponse(ingredient.getIngredient(),
                ingredient.getNumberOfShots(),
                this.ingredientService.getPriceForShots(ingredient));
    }
}