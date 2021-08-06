package org.fantasticcoffee.shop.facade.ingredient;

import org.fantasticcoffee.shop.data.ingredient.IngredientInStockResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.ingredient.IngredientInStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultIngredientFacade implements IngredientFacade {

    @Autowired
    Converter<IngredientInStockResponse, IngredientInStock> ingredientInStockResponseConverter;

    @Override
    public List<IngredientInStockResponse> getIngredientInStockResponse(List<IngredientInStock> ingredientInStock) {
        return this.ingredientInStockResponseConverter.convertAll(ingredientInStock);
    }
}