package org.fantasticcoffee.shop.facade.standardrecipe;

import org.fantasticcoffee.shop.data.standardrecipe.StandardRecipeResponse;
import org.fantasticcoffee.shop.facade.converter.Converter;
import org.fantasticcoffee.shop.model.StandardRecipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultStandardRecipe implements StandardRecipeFacade {

    @Autowired
    Converter<StandardRecipeResponse, StandardRecipe> standardRecipeResponseConverter;

    @Override
    public List<StandardRecipeResponse> getStandardRecipeResponse(List<StandardRecipe> standardRecipe) {
        return this.standardRecipeResponseConverter.convertAll(standardRecipe);
    }
}