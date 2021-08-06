package org.fantasticcoffee.shop.facade.converter.ingredients;

import org.fantasticcoffee.shop.data.ingredient.ChosenIngredientRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.ingredient.ChosenIngredientIngredientInStock;
import org.fantasticcoffee.shop.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChosenIngredientIngredientInStockConverter implements Converter<ChosenIngredientIngredientInStock, ChosenIngredientRequest> {

    @Autowired
    IngredientService ingredientService;

    @Override
    public ChosenIngredientIngredientInStock convert(ChosenIngredientRequest chosenIngredientRequest) {
        return new ChosenIngredientIngredientInStock(
                this.ingredientService.getByName(chosenIngredientRequest.getIngredient()),
                chosenIngredientRequest.getNumberOfShots());
    }
}