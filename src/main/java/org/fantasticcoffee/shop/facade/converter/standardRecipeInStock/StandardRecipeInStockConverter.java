package org.fantasticcoffee.shop.facade.converter.standardRecipeInStock;

import org.fantasticcoffee.shop.data.standardrecipeinstock.StandardRecipeInStockRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.recipe.StandardRecipeInStock;
import org.fantasticcoffee.shop.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StandardRecipeInStockConverter implements Converter<StandardRecipeInStock, StandardRecipeInStockRequest> {

    @Autowired
    CoffeeService coffeeService;

    @Override
    public StandardRecipeInStock convert(StandardRecipeInStockRequest standardRecipeInStockRequest) {

        return this.coffeeService.getCoffee(standardRecipeInStockRequest.getStandardRecipeName());
    }
}