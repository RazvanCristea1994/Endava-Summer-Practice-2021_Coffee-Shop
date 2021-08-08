package org.fantasticcoffee.shop.facade.converter.coffee;

import org.fantasticcoffee.shop.data.customcoffee.CoffeeResponse;
import org.fantasticcoffee.shop.data.ingredient.IngredientChosenResponse;
import org.fantasticcoffee.shop.data.standardrecipe.StandardRecipeResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.model.JoinClasses.CoffeeIngredient;
import org.fantasticcoffee.shop.model.StandardRecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoffeeResponseConverter implements Converter<CoffeeResponse, Coffee> {

    @Autowired
    private Converter<IngredientChosenResponse, CoffeeIngredient> ingredientResponseConverter;
    @Autowired
    private Converter<StandardRecipeResponse, StandardRecipe> standardRecipeResponseConverter;

    @Override
    public CoffeeResponse convert(Coffee coffee) {

        return new CoffeeResponse(
                coffee.getCoffeeName(),
                this.standardRecipeResponseConverter.convert(coffee.getStandardRecipe()),
                this.ingredientResponseConverter.convertAll(coffee.getChosenIngredients()),
                coffee.getPrice());
    }
}