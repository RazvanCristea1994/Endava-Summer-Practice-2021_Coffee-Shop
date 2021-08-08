package org.fantasticcoffee.shop.facade.converter.ingredients;

import org.fantasticcoffee.shop.data.ingredient.IngredientChosenResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.JoinClasses.CoffeeIngredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientChosenResponseConverter implements Converter<IngredientChosenResponse, CoffeeIngredient> {

    @Override
    public IngredientChosenResponse convert(CoffeeIngredient ingredient) {
        return new IngredientChosenResponse(
                ingredient.getIngredient().getName(),
                ingredient.getIngredient().getIngredientSellingPrice(),
                ingredient.getNumberOfShots(),
                ingredient.getIngredient().getQuantityPerShot(),
                ingredient.getIngredient().getUnitOfMeasurement()
        );
    }
}