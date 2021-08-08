package org.fantasticcoffee.shop.data.ingredient;

import lombok.Getter;

@Getter
public class IngredientDetailedResponse {

    private final String name;
    private final Double ingredientSellingPrice;
    private final int availableNumberOfShots;
    private final Double quantityPerShot;
    private final String unitOfMeasurement;
    private final String alertMessage;

    public IngredientDetailedResponse(String name,
                                      Double ingredientSellingPrice,
                                      int availableNumberOfShots, Double quantityPerShot,
                                      String unitOfMeasurement) {
        this.name = name;
        this.ingredientSellingPrice = ingredientSellingPrice;
        this.availableNumberOfShots = availableNumberOfShots;
        this.quantityPerShot = quantityPerShot;
        this.unitOfMeasurement = unitOfMeasurement;

        if (availableNumberOfShots <= 3) {
            this.alertMessage = "Warning! Stock almost empty!";
        } else {
            this.alertMessage = null;
        }
    }
}