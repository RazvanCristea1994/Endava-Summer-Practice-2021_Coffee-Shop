package org.fantasticcoffee.shop.facade.ingredient;

import org.fantasticcoffee.shop.data.ingredient.IngredientDetailedResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultIngredientFacade implements IngredientFacade {

    @Autowired
    Converter<IngredientDetailedResponse, Ingredient> ingredientResponseConverter;

    @Override
    public List<IngredientDetailedResponse> getIngredientResponse(List<Ingredient> ingredient) {
        return this.ingredientResponseConverter.convertAll(ingredient);
    }
}