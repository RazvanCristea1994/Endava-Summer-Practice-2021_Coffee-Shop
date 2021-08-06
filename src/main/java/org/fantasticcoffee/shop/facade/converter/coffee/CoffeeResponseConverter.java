package org.fantasticcoffee.shop.facade.converter.coffee;

import org.fantasticcoffee.shop.data.customcoffee.CoffeeResponse;
import org.fantasticcoffee.shop.data.ingredient.ChosenIngredientResponse;
import org.fantasticcoffee.shop.data.standardrecipeinstock.StandardRecipeInStockResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.model.ingredient.ChosenIngredientIngredientInStock;
import org.fantasticcoffee.shop.model.recipe.StandardRecipeInStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoffeeResponseConverter implements Converter<CoffeeResponse, Coffee> {

    @Autowired
    Converter<ChosenIngredientResponse, ChosenIngredientIngredientInStock> ingredientOnRecipeResponseConverter;
    @Autowired
    Converter<StandardRecipeInStockResponse, StandardRecipeInStock> standardRecipeInStockResponseConverter;

    @Override
    public CoffeeResponse convert(Coffee customCoffee) {

        return new CoffeeResponse(
                customCoffee.getCoffeeName(),
                this.standardRecipeInStockResponseConverter.convert(customCoffee.getStandardRecipe()),
                this.ingredientOnRecipeResponseConverter.convertAll(customCoffee.getChosenIngredients()),
                customCoffee.getPrice());
    }
}