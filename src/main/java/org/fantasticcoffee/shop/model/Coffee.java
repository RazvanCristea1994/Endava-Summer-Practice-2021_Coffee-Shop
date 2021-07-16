package org.fantasticcoffee.shop.model;

import java.util.ArrayList;
import java.util.Collection;

public class Coffee extends CoffeeBase {

    private String customerName;
    private Collection<Ingredient> extraIngredientList = new ArrayList<>();

    public Coffee() {
    }

    public Double getPrice() {

        Double baseCoffeePrice = this.getCoffeeType().getPrice();

        Double extraIngredientsPrice = extraIngredientList
                .stream()
                .mapToDouble(Ingredient::getIngredientSellingPrice)
                .sum();

        return baseCoffeePrice + extraIngredientsPrice;
    }

    public Double getCost() {

        Double baseCoffeeCost = this.getCoffeeType().getRecipe()
                .stream()
                .mapToDouble(Ingredient::getIngredientCost)
                .sum();
        Double extraIngredientsCost = extraIngredientList
                .stream()
                .mapToDouble(Ingredient::getIngredientCost)
                .sum();

        return baseCoffeeCost + extraIngredientsCost;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Collection<Ingredient> getExtraIngredientsList() {
        return extraIngredientList;
    }

    public void addExtraIngredient(Ingredient extraIngredient) {
        this.extraIngredientList.add(extraIngredient);
    }
}