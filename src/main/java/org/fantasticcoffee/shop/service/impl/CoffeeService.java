package org.fantasticcoffee.shop.service.impl;

import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.model.CoffeeType;
import org.fantasticcoffee.shop.model.Ingredient;
import org.fantasticcoffee.shop.service.ICoffee;

public class CoffeeService implements ICoffee {

    public Double getCoffeePrice(Coffee coffee) {

        Double baseCoffeePrice = coffee.getCoffeeType().getRecipe().stream().mapToDouble(Ingredient::getIngredientSellingPrice).sum();
        Double extraIngredientsPrice = coffee.getExtraIngredientsList().stream().mapToDouble(Ingredient::getIngredientSellingPrice).sum();

        return baseCoffeePrice + extraIngredientsPrice;
    }

    public Double getCoffeeCost(Coffee coffee) {

        Double baseCoffeeCost = coffee.getCoffeeType().getRecipe().stream().mapToDouble(Ingredient::getIngredientCost).sum();
        Double extraIngredientsCost = coffee.getExtraIngredientsList().stream().mapToDouble(Ingredient::getIngredientCost).sum();

        return baseCoffeeCost + extraIngredientsCost;
    }

    public Double getCoffeePrice(CoffeeType coffeeType) {

        return coffeeType.getRecipe().stream().mapToDouble(Ingredient::getIngredientSellingPrice).sum();
    }
}
