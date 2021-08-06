package org.fantasticcoffee.shop.data.standardrecipeinstock;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class StandardRecipeInStockResponse {

    private String standardRecipeName;
    private List<StandardRecipeIngredientInStockResponse> ingredientOnRecipeList;
}