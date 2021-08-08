package org.fantasticcoffee.shop.facade.converter.coffee;

import org.fantasticcoffee.shop.data.customcoffee.CoffeeRequest;
import org.fantasticcoffee.shop.data.ingredient.IngredientChosenRequest;
import org.fantasticcoffee.shop.data.standardrecipe.StandardRecipeRequest;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.model.JoinClasses.CoffeeIngredient;
import org.fantasticcoffee.shop.model.StandardRecipe;
import org.fantasticcoffee.shop.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoffeeConverter implements Converter<Coffee, CoffeeRequest> {

    @Autowired
    private Converter<CoffeeIngredient, IngredientChosenRequest> ingredientConverter;
    @Autowired
    private Converter<StandardRecipe, StandardRecipeRequest> standardRecipeConverter;
    @Autowired
    private CoffeeService coffeeService;

    @Override
    public Coffee convert(CoffeeRequest coffeeRequest) {

        Coffee coffee = new Coffee(
                coffeeRequest.getCoffeeName(),
                this.standardRecipeConverter.convert(coffeeRequest.getStandardRecipe()),
                this.ingredientConverter.convertAll(coffeeRequest.getChosenIngredients()));
        coffee.setPrice(this.coffeeService.getCoffeePrice(coffee));

        return coffee;
    }
}