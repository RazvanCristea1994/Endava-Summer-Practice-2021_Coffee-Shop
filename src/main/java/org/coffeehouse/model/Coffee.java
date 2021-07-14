package org.coffeehouse.model;

import java.util.ArrayList;
import java.util.Collection;

public class Coffee extends CoffeeBase<Long> {

    private Double price;
    private String customerName;
    private Collection<Ingredient> extraIngredientList = new ArrayList<>();

    public Coffee() {
    }

    public Double getPrice() {

        Double baseCoffeePrice = super.getCoffeeType().getRecipe()
                .stream()
                .mapToDouble(Ingredient::getIngredientPrice)
                .sum();
        Double extraIngredientsPrice = extraIngredientList
                .stream()
                .mapToDouble(Ingredient::getIngredientPrice)
                .sum();

        return baseCoffeePrice + extraIngredientsPrice;
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
