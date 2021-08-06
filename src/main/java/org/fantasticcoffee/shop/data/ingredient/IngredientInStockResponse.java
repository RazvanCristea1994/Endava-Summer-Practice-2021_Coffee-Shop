package org.fantasticcoffee.shop.data.ingredient;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IngredientInStockResponse {

    private String name;
    private Double ingredientSellingPrice;
    private int numberOfShots;
    private Double quantityPerShot;
    private String unitOfMeasurement;
}