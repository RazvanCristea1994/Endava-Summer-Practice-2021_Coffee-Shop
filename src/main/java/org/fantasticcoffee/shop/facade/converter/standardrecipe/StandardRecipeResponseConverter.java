package org.fantasticcoffee.shop.facade.converter.standardrecipe;

import org.fantasticcoffee.shop.data.standardrecipe.StandardRecipeResponse;
import org.fantasticcoffee.shop.data.ingredient.IngredientOnStandardRecipeResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.JoinClasses.StandardRecipeIngredient;
import org.fantasticcoffee.shop.model.StandardRecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StandardRecipeResponseConverter implements Converter<StandardRecipeResponse, StandardRecipe> {

    @Autowired
    private Converter<IngredientOnStandardRecipeResponse, StandardRecipeIngredient> standardRecipeIngredientResponseConverter;

    @Override
    public StandardRecipeResponse convert(StandardRecipe standardRecipeResponse) {
        return new StandardRecipeResponse(
                standardRecipeResponse.getName(),
                this.standardRecipeIngredientResponseConverter.convertAll(standardRecipeResponse.getIngredientList()));
    }
}