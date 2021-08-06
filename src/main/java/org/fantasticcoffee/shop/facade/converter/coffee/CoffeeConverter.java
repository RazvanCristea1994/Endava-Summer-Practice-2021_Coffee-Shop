package org.fantasticcoffee.shop.facade.converter.coffee;

import org.fantasticcoffee.shop.data.customcoffee.CoffeeRequest;
import org.fantasticcoffee.shop.data.ingredient.ChosenIngredientRequest;
import org.fantasticcoffee.shop.data.standardrecipeinstock.StandardRecipeInStockRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.model.ingredient.ChosenIngredientIngredientInStock;
import org.fantasticcoffee.shop.model.recipe.StandardRecipeInStock;
import org.fantasticcoffee.shop.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoffeeConverter implements Converter<Coffee, CoffeeRequest> {

    @Autowired
    Converter<ChosenIngredientIngredientInStock, ChosenIngredientRequest> ingredientOnRecipeConverter;
    @Autowired
    Converter<StandardRecipeInStock, StandardRecipeInStockRequest> standardRecipeInStockConverter;
    @Autowired
    CoffeeService coffeeService;

    @Override
    public Coffee convert(CoffeeRequest coffeeRequest) {

        Coffee coffee = new Coffee(
                coffeeRequest.getCoffeeName(),
                this.standardRecipeInStockConverter.convert(coffeeRequest.getStandardRecipe()),
                this.ingredientOnRecipeConverter.convertAll(coffeeRequest.getChosenIngredients()));
        coffee.setPrice(this.coffeeService.getCoffeePrice(coffee));

        return coffee;
    }
}