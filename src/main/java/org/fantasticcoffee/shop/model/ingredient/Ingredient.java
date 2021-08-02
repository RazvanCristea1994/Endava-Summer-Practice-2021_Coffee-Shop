package org.fantasticcoffee.shop.model.ingredient;

import lombok.Getter;

@Getter
public enum Ingredient {

    ESPRESSO_SHOT("Espresso shot", 7.0, 3.5, 20, "ml"),
    BLACK_COFFEE("Black coffee", 8.0, 4.0, 20, "ml"),
    MILK("Milk", 3.0, 1.5, 10, "ml"),
    HONEY("Honey", 3.0, 1.5, 10, "g"),
    SYRUP("Syrup", 4.0, 2.0, 10, "ml"),
    STEAMED_MILK("Steamed milk", 5.0, 2.5, 15, "g"),
    MILK_FOAM("Milk foam", 3.0, 1.5, 10, "g"),
    SWEETENED_CONDENSED_MILK("Sweetened condensed milk", 5.0, 2.5, 10, "g"),
    ICE_CREAM("Ice cream", 7.0, 3.5, 70, "g"),
    WHIPPED_CREAM("Whipped cream", 5.0, 2.5, 10, "g"),
    CINNAMON("Cinnamon", 5.0, 2.5, 3, "g"),
    HOT_WATER("Hot water", 2.0, 1.0, 20, "ml"),
    ICE_CUBES("Ice cubes", 2.0, 1.0, 4, "pieces");

    private final Double ingredientSellingPrice;
    private final Double ingredientCost;
    private final String ingredientName;
    private final int quantityPerShot;
    private final String unitOfMeasurement;

    Ingredient(String ingredientName,
               Double ingredientSellingPrice,
               Double ingredientCost,
               int quantityPerShot,
               String unitOfMeasurement) {
        this.ingredientSellingPrice = ingredientSellingPrice;
        this.ingredientCost = ingredientCost;
        this.ingredientName = ingredientName;
        this.quantityPerShot = quantityPerShot;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    @Override
    public String toString() {
        return String.format("%-30s %-4s %-10s %s$ %n", ingredientName, quantityPerShot, unitOfMeasurement, ingredientSellingPrice);
    }
}