package org.fantasticcoffee.shop.model;

import lombok.Getter;

@Getter
public enum Ingredient {

    MILK("Milk", 3.0, 1.5),
    HONEY("Honey", 3.0, 1.5),
    SYRUP("Syrup", 4.0, 2.0),
    STEAMED_MILK("Steamed milk", 5.0, 2.5),
    MILK_FOAM("Milk foam", 3.0, 1.5),
    SWEETENED_CONDENSED_MILK("Sweetened condensed milk", 5.0, 2.5),
    ICE_CREAM("Ice cream", 7.0, 3.5),
    WHIPPED_CREAM("Whipped cream", 5.0, 2.5),
    CINNAMON("Cinnamon", 5.0, 2.5),
    HOT_WATER("Hot water", 2.0, 1.0),
    ICE_CUBES("Ice cubes", 2.0, 1.0),
    ESPRESSO_SHOT("Espresso shot", 7.0, 3.5),
    BLACK_COFFEE("Black coffee", 8.0, 4.0);

    private final Double ingredientSellingPrice;
    private final Double ingredientCost;
    private final String ingredientName;

    Ingredient(String ingredientName, Double ingredientSellingPrice, Double ingredientCost) {
        this.ingredientSellingPrice = ingredientSellingPrice;
        this.ingredientCost = ingredientCost;
        this.ingredientName = ingredientName;
    }
}
