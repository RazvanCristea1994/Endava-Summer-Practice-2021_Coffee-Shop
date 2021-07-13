package org.coffeehouse.model;

import java.util.Collection;
import java.util.List;

public enum Ingredients {

    MILK(5, "Milk"),
    HONEY(6, "Honey"),
    SYRUP(4, "Syrup"),
    STEAMED_MILK(5, "Steamed milk"),
    MILK_FOAM(3, "Milk foam"),
    SWEETENED_CONDENSED_MILK(5, "Sweetened condensed milk"),
    ICE_CREAM(7, "Ice cream"),
    WHIPPED_CREAM(6, "Whipped cream"),
    CINNAMON(7, "Cinnamon"),
    HOT_WATER(2, "Hot water"),
    ICE_CUBES(3, "Ice cubes"),
    ESPRESSO_SHOT(8, "Espresso shot"),
    BLACK_COFFEE(9, "Black coffee");

    private Double ingredientPrice;
    private String ingredientName;
    private Collection<Coffee> coffeeIngredientsList;
    private Collection<CoffeeType> coffeeTypeIngredientsList;

    Ingredients(double ingredientPrice, String ingredientName) {
        this.ingredientPrice = ingredientPrice;
        this.ingredientName = ingredientName;
    }

    public Double getIngredientPrice() {
        return ingredientPrice;
    }

    public void setIngredientPrice(Double ingredientPrice) {
        this.ingredientPrice = ingredientPrice;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Collection<Coffee> getCoffeeIngredientsList() {
        return coffeeIngredientsList;
    }

    public void setCoffeeIngredientsList(Collection<Coffee> coffeeIngredientsList) {
        this.coffeeIngredientsList = coffeeIngredientsList;
    }

    public Collection<CoffeeType> getCoffeeTypeIngredientsList() {
        return coffeeTypeIngredientsList;
    }

    public void setCoffeeTypeIngredientsList(Collection<CoffeeType> coffeeTypeIngredientsList) {
        this.coffeeTypeIngredientsList = coffeeTypeIngredientsList;
    }
}
