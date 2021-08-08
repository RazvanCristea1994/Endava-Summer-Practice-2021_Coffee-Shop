package org.fantasticcoffee.shop.facade.converter.ingredients;

import org.fantasticcoffee.shop.data.ingredient.IngredientOnStandardRecipeResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.JoinClasses.StandardRecipeIngredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientOnStandardRecipeConverter implements Converter<IngredientOnStandardRecipeResponse, StandardRecipeIngredient> {

    @Override
    public IngredientOnStandardRecipeResponse convert(StandardRecipeIngredient ingredient) {
        return new IngredientOnStandardRecipeResponse(
                ingredient.getIngredient().getName(),
                ingredient.getIngredient().getIngredientSellingPrice(),
                ingredient.getNumberOfShots(),
                ingredient.getIngredient().getQuantityPerShot(),
                ingredient.getIngredient().getUnitOfMeasurement()
        );
    }
}