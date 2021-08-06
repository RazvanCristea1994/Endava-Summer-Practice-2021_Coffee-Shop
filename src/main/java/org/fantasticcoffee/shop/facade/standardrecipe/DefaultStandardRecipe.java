package org.fantasticcoffee.shop.facade.standardrecipe;

import org.fantasticcoffee.shop.data.standardrecipeinstock.StandardRecipeInStockResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.recipe.StandardRecipeInStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultStandardRecipe implements StandardRecipeFacade {

    @Autowired
    Converter<StandardRecipeInStockResponse, StandardRecipeInStock> standardRecipeInStockResponseConverter;

    @Override
    public List<StandardRecipeInStockResponse> getStandardRecipeResponse(List<StandardRecipeInStock> standardRecipeInStock) {
        return this.standardRecipeInStockResponseConverter.convertAll(standardRecipeInStock);
    }
}