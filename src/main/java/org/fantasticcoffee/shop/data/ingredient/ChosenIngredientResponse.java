package org.fantasticcoffee.shop.data.ingredient;

import lombok.Getter;
import org.fantasticcoffee.shop.model.ingredient.IngredientInStock;

@Getter
public class ChosenIngredientResponse {

    private IngredientInStock ingredient;
    private int numberOfShots;
    private Double shotsPrice;

    public ChosenIngredientResponse(IngredientInStock ingredient, int numberOfShots, Double shotsPrice) {
        this.ingredient = ingredient;
        this.numberOfShots = numberOfShots;
        this.shotsPrice = shotsPrice;
    }
}