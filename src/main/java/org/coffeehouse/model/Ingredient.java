package org.coffeehouse.model;

public enum Ingredient {

    MILK(5.0, 2.2, "Milk"),
    HONEY(6.0, 3.2, "Honey"),
    SYRUP(4.0, 1.7, "Syrup"),
    STEAMED_MILK(5.0, 2.8, "Steamed milk"),
    MILK_FOAM(4.0, 1.8, "Milk foam"),
    SWEETENED_CONDENSED_MILK(5.0, 2.0, "Sweetened condensed milk"),
    ICE_CREAM(7.0, 3.5, "Ice cream"),
    WHIPPED_CREAM(6.0, 3.0, "Whipped cream"),
    CINNAMON(7.0, 3.5, "Cinnamon"),
    HOT_WATER(2.0, 0.7, "Hot water"),
    ICE_CUBES(3.0, 1.0, "Ice cubes"),
    ESPRESSO_SHOT(8.0, 3.0, "Espresso shot"),
    BLACK_COFFEE(9.0, 4.0, "Black coffee");

    private Double ingredientSellingPrice;
    private Double ingredientCost;
    private String ingredientName;

    Ingredient(Double ingredientSellingPrice, Double ingredientCost, String ingredientName) {
        this.ingredientSellingPrice = ingredientSellingPrice;
        this.ingredientCost = ingredientCost;
        this.ingredientName = ingredientName;
    }

    public Double getIngredientSellingPrice() {
        return ingredientSellingPrice;
    }

    public Double getIngredientCost() {
        return ingredientCost;
    }

    public String getIngredientName() {
        return ingredientName;
    }
}
