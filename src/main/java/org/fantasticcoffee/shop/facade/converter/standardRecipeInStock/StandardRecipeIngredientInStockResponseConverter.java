package org.fantasticcoffee.shop.facade.converter.standardRecipeInStock;

import org.fantasticcoffee.shop.data.ingredient.IngredientInStockResponse;
import org.fantasticcoffee.shop.data.standardrecipeinstock.StandardRecipeIngredientInStockResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.StandardRecipeIngredientInStock;
import org.fantasticcoffee.shop.model.ingredient.IngredientInStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StandardRecipeIngredientInStockResponseConverter implements Converter<StandardRecipeIngredientInStockResponse, StandardRecipeIngredientInStock> {

    @Autowired
    Converter<IngredientInStockResponse, IngredientInStock> ingredientInStockResponseConverter;

    @Override
    public StandardRecipeIngredientInStockResponse convert(StandardRecipeIngredientInStock ingredient) {
        return new StandardRecipeIngredientInStockResponse(
                this.ingredientInStockResponseConverter.convert(ingredient.getIngredientInStock()),
                ingredient.getNumberOfShots()
        );
    }
}