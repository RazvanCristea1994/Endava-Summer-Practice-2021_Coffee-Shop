package org.fantasticcoffee.shop.facade.converter.ingredients;

import org.fantasticcoffee.shop.data.ingredient.IngredientInStockResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.ingredient.IngredientInStock;
import org.springframework.stereotype.Component;

@Component
public class IngredientInStockResponseConverter implements Converter<IngredientInStockResponse, IngredientInStock> {

    @Override
    public IngredientInStockResponse convert(IngredientInStock ingredientInStock) {
        return new IngredientInStockResponse(
                ingredientInStock.getName(),
                ingredientInStock.getIngredientSellingPrice(),
                ingredientInStock.getNumberOfShots(),
                ingredientInStock.getQuantityPerShot(),
                ingredientInStock.getUnitOfMeasurement()
        );
    }
}