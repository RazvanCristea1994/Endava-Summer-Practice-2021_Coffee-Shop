package org.fantasticcoffee.shop.service.impl;

import org.fantasticcoffee.shop.model.BaseIngredient;
import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.model.CoffeeType;
import org.fantasticcoffee.shop.model.ExtraIngredient;
import org.fantasticcoffee.shop.service.ICoffee;
import org.springframework.stereotype.Service;

@Service("coffeeService")
public class CoffeeService implements ICoffee {

    public Double getCoffeePrice(Coffee coffee) {

        double baseCoffeeTypePrice = 0.0;
        if (coffee.getCoffeeType().getRecipe().getBaseIngredients() != null) {
            baseCoffeeTypePrice = coffee.getCoffeeType().getRecipe().getBaseIngredients().stream()
                    .mapToDouble(BaseIngredient::getIngredientSellingPrice)
                    .sum();
        }

        double extraIngredientsInCoffeeTypePrice = 0.0;
        if (coffee.getCoffeeType().getRecipe().getExtraIngredients() != null) {
            extraIngredientsInCoffeeTypePrice = coffee.getCoffeeType().getRecipe().getExtraIngredients().stream()
                    .mapToDouble(ExtraIngredient::getIngredientSellingPrice)
                    .sum();
        }

        double additionalBaseIngredients = 0.0;
        if (coffee.getAdditionalIngredientsForCustomCoffee().getBaseIngredients() != null) {
            additionalBaseIngredients = coffee.getAdditionalIngredientsForCustomCoffee().getBaseIngredients().stream()
                    .mapToDouble(BaseIngredient::getIngredientSellingPrice)
                    .sum();
        }

        double additionalExtraIngredients = 0.0;
        if (coffee.getAdditionalIngredientsForCustomCoffee().getExtraIngredients() != null) {
            additionalExtraIngredients = coffee.getAdditionalIngredientsForCustomCoffee().getExtraIngredients().stream()
                    .mapToDouble(ExtraIngredient::getIngredientSellingPrice)
                    .sum();
        }

        return baseCoffeeTypePrice + extraIngredientsInCoffeeTypePrice + additionalBaseIngredients + additionalExtraIngredients;
    }

    public Double getCoffeeCost(Coffee coffee) {

        double baseCoffeeTypeCost = 0.0;
        if (coffee.getCoffeeType().getRecipe().getBaseIngredients() != null) {
            baseCoffeeTypeCost = coffee.getCoffeeType().getRecipe().getBaseIngredients().stream()
                    .mapToDouble(BaseIngredient::getIngredientCost)
                    .sum();
        }
        double extraIngredientsInCoffeeTypeCost = 0.0;
        if (coffee.getCoffeeType().getRecipe().getExtraIngredients() != null) {
            extraIngredientsInCoffeeTypeCost = coffee.getCoffeeType().getRecipe().getExtraIngredients().stream()
                    .mapToDouble(ExtraIngredient::getIngredientCost)
                    .sum();
        }

        double additionalBaseCoffeeCost = 0.0;
        if (coffee.getAdditionalIngredientsForCustomCoffee().getBaseIngredients() != null) {
            additionalBaseCoffeeCost = coffee.getAdditionalIngredientsForCustomCoffee().getBaseIngredients().stream()
                    .mapToDouble(BaseIngredient::getIngredientCost)
                    .sum();
        }

        double additionalExtraIngredientsCost = 0.0;
        if (coffee.getAdditionalIngredientsForCustomCoffee().getExtraIngredients() != null) {
            additionalExtraIngredientsCost = coffee.getAdditionalIngredientsForCustomCoffee().getExtraIngredients().stream()
                    .mapToDouble(ExtraIngredient::getIngredientCost)
                    .sum();
        }

        return baseCoffeeTypeCost + extraIngredientsInCoffeeTypeCost + additionalBaseCoffeeCost + additionalExtraIngredientsCost;
    }

    public Double getCoffeeTypePrice(CoffeeType coffeeType) {

        Double baseCoffeeTypePrice = coffeeType.getRecipe().getBaseIngredients().stream()
                .mapToDouble(BaseIngredient::getIngredientSellingPrice)
                .sum();

        double extraIngredientsInCoffeeTypeCost = 0.0;
        if (coffeeType.getRecipe().getExtraIngredients() != null) {
            extraIngredientsInCoffeeTypeCost = coffeeType.getRecipe().getExtraIngredients().stream()
                    .mapToDouble(ExtraIngredient::getIngredientSellingPrice)
                    .sum();
        }

        return baseCoffeeTypePrice + extraIngredientsInCoffeeTypeCost;
    }
}
