package org.coffeehouse.model;

public enum Ingredient {

    MILK(3.0, 1.5, "Milk"),
    HONEY(3.0, 1.5, "Honey"),
    SYRUP(4.0, 2.0, "Syrup"),
    STEAMED_MILK(5.0, 2.5, "Steamed milk"),
    MILK_FOAM(3.0, 1.5, "Milk foam"),
    SWEETENED_CONDENSED_MILK(5.0, 2.5, "Sweetened condensed milk"),
    ICE_CREAM(7.0, 3.5, "Ice cream"),
    WHIPPED_CREAM(5.0, 2.5, "Whipped cream"),
    CINNAMON(5.0, 2.5, "Cinnamon"),
    HOT_WATER(2.0, 1.0, "Hot water"),
    ICE_CUBES(2.0, 1.0, "Ice cubes"),
    ESPRESSO_SHOT(7.0, 3.5, "Espresso shot"),
    BLACK_COFFEE(8.0, 4.0, "Black coffee");

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
