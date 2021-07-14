package org.coffeehouse.model;

import java.util.Collection;

public enum Ingredient {

    MILK(5.0, "Milk"),
    HONEY(6.0, "Honey"),
    SYRUP(4.0, "Syrup"),
    STEAMED_MILK(5.0, "Steamed milk"),
    MILK_FOAM(3.0, "Milk foam"),
    SWEETENED_CONDENSED_MILK(5.0, "Sweetened condensed milk"),
    ICE_CREAM(7.0, "Ice cream"),
    WHIPPED_CREAM(6.0, "Whipped cream"),
    CINNAMON(7.0, "Cinnamon"),
    HOT_WATER(2.0, "Hot water"),
    ICE_CUBES(3.0, "Ice cubes"),
    ESPRESSO_SHOT(8.0, "Espresso shot"),
    BLACK_COFFEE(9.0, "Black coffee");

    private Double ingredientPrice;
    private String ingredientName;

    Ingredient(Double ingredientPrice, String ingredientName) {
        this.ingredientPrice = ingredientPrice;
        this.ingredientName = ingredientName;
    }

    public Double getIngredientPrice() {
        return ingredientPrice;
    }

    public String getIngredientName() {
        return ingredientName;
    }

}
