package org.fantasticcoffee.shop.data.standardrecipeinstock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.fantasticcoffee.shop.data.ingredient.IngredientInStockResponse;

@Getter
@AllArgsConstructor
public class StandardRecipeIngredientInStockResponse {

    IngredientInStockResponse ingredientInStock;
    int numberOfShots;
}