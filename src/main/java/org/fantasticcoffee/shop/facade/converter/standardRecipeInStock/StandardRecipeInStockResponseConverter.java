package org.fantasticcoffee.shop.facade.converter.standardRecipeInStock;

import org.fantasticcoffee.shop.data.standardrecipeinstock.StandardRecipeInStockResponse;
import org.fantasticcoffee.shop.data.standardrecipeinstock.StandardRecipeIngredientInStockResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.StandardRecipeIngredientInStock;
import org.fantasticcoffee.shop.model.recipe.StandardRecipeInStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StandardRecipeInStockResponseConverter implements Converter<StandardRecipeInStockResponse, StandardRecipeInStock> {

    @Autowired
    Converter<StandardRecipeIngredientInStockResponse, StandardRecipeIngredientInStock> standardRecipeIngredientInStockResponseConverter;

    @Override
    public StandardRecipeInStockResponse convert(StandardRecipeInStock standardRecipeInStockResponse) {
        return new StandardRecipeInStockResponse(
                standardRecipeInStockResponse.getName(),
                this.standardRecipeIngredientInStockResponseConverter.convertAll(standardRecipeInStockResponse.getIngredientOnRecipeList()));
    }
}