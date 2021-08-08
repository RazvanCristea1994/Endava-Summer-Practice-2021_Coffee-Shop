package org.fantasticcoffee.shop.facade.converter.ingredients;

import org.fantasticcoffee.shop.data.ingredient.IngredientChosenRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.JoinClasses.CoffeeIngredient;
import org.fantasticcoffee.shop.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IngredientChosenConverter implements Converter<CoffeeIngredient, IngredientChosenRequest> {

    @Autowired
    private IngredientService ingredientService;

    @Override
    public CoffeeIngredient convert(IngredientChosenRequest ingredientChosenRequest) {
        return new CoffeeIngredient(
                this.ingredientService.getByName(ingredientChosenRequest.getIngredient()),
                ingredientChosenRequest.getNumberOfShots());
    }
}