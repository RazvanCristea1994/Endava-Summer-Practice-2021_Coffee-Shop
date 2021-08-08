package org.fantasticcoffee.shop.facade.converter.ingredients;

import org.fantasticcoffee.shop.data.ingredient.IngredientDetailedResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientDetailedResponseConverter implements Converter<IngredientDetailedResponse, Ingredient> {

    @Override
    public IngredientDetailedResponse convert(Ingredient ingredient) {
        return new IngredientDetailedResponse(
                ingredient.getName(),
                ingredient.getIngredientSellingPrice(),
                ingredient.getNumberOfShots(),
                ingredient.getQuantityPerShot(),
                ingredient.getUnitOfMeasurement()
        );
    }
}