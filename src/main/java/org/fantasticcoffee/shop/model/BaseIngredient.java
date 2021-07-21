package org.fantasticcoffee.shop.model;

import lombok.Getter;

@Getter
public enum BaseIngredient {

    ESPRESSO_SHOT("Espresso shot", 7.0, 3.5, 20, "ml"),
    BLACK_COFFEE("Black coffee", 8.0, 4.0, 20, "ml");

    private final Double ingredientSellingPrice;
    private final Double ingredientCost;
    private final String ingredientName;
    private final int quantity;
    private final String unitOfMeasurement;

    BaseIngredient(String ingredientName, Double ingredientSellingPrice, Double ingredientCost, int quantity, String unitOfMeasurement) {
        this.ingredientSellingPrice = ingredientSellingPrice;
        this.ingredientCost = ingredientCost;
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    @Override
    public String toString() {
        return String.format("%-30s %-4s %-10s %s$ %n", ingredientName, quantity, unitOfMeasurement, ingredientSellingPrice);
    }
}
