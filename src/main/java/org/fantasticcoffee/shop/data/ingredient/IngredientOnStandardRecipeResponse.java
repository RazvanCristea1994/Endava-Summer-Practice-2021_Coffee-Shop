package org.fantasticcoffee.shop.data.ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IngredientOnStandardRecipeResponse {

    private final String name;
    private final int numberOfShots;
    private final Double quantityTotal;
    private final String unitOfMeasurement;
    private final Double priceShotsTotal;

    public IngredientOnStandardRecipeResponse(String name,
                                              Double ingredientSellingPrice,
                                              int numberOfShots,
                                              Double quantityPerShot,
                                              String unitOfMeasurement) {
        this.name = name;
        this.numberOfShots = numberOfShots;
        this.quantityTotal = numberOfShots * quantityPerShot;
        this.unitOfMeasurement = unitOfMeasurement;
        this.priceShotsTotal = numberOfShots * ingredientSellingPrice;
    }
}