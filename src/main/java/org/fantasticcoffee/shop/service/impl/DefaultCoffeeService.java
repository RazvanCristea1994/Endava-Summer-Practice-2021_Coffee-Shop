package org.fantasticcoffee.shop.service.impl;

import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.model.CoffeeType;
import org.fantasticcoffee.shop.model.ingredientonrecipe.BaseIngredientOnRecipe;
import org.fantasticcoffee.shop.model.ingredientonrecipe.ExtraIngredientOnRecipe;
import org.fantasticcoffee.shop.service.CoffeeService;
import org.springframework.stereotype.Service;

@Service("coffeeService")
public class DefaultCoffeeService implements CoffeeService {

    public Double getCoffeePrice(Coffee coffee) {

        double baseIngredientsInCoffeeTypePrice = 0.0;
        if (!coffee.getCoffeeType().getRecipe().getBaseIngredients().isEmpty()) {
            for (BaseIngredientOnRecipe baseIngredientOnRecipe : coffee.getCoffeeType().getRecipe().getBaseIngredients()) {
                baseIngredientsInCoffeeTypePrice +=
                        baseIngredientOnRecipe.getQuantity() * baseIngredientOnRecipe.getBaseIngredient().getIngredientSellingPrice();
            }
        }

        double extraIngredientsInCoffeeTypePrice = 0.0;
        if (!coffee.getCoffeeType().getRecipe().getExtraIngredients().isEmpty()) {
            for (ExtraIngredientOnRecipe extraIngredientOnRecipe : coffee.getCoffeeType().getRecipe().getExtraIngredients()) {
                extraIngredientsInCoffeeTypePrice +=
                        extraIngredientOnRecipe.getQuantity() * extraIngredientOnRecipe.getExtraIngredient().getIngredientSellingPrice();
            }
        }

        double additionalBaseIngredientsPrice = 0.0;
        if (!coffee.getAdditionalIngredientsForCustomCoffee().getBaseIngredients().isEmpty()) {
            for (BaseIngredientOnRecipe baseIngredientOnRecipe : coffee.getAdditionalIngredientsForCustomCoffee().getBaseIngredients()) {
                additionalBaseIngredientsPrice +=
                        baseIngredientOnRecipe.getQuantity() * baseIngredientOnRecipe.getBaseIngredient().getIngredientSellingPrice();
            }
        }

        double additionalExtraIngredientsPrice = 0.0;
        if (!coffee.getAdditionalIngredientsForCustomCoffee().getExtraIngredients().isEmpty()) {
            for (ExtraIngredientOnRecipe extraIngredientOnRecipe : coffee.getAdditionalIngredientsForCustomCoffee().getExtraIngredients()) {
                additionalExtraIngredientsPrice +=
                        extraIngredientOnRecipe.getQuantity() * extraIngredientOnRecipe.getExtraIngredient().getIngredientSellingPrice();
            }
        }

        return baseIngredientsInCoffeeTypePrice + extraIngredientsInCoffeeTypePrice + additionalBaseIngredientsPrice + additionalExtraIngredientsPrice;
    }

    public Double getCoffeeCost(Coffee coffee) {

        double baseIngredientsInCoffeeTypeCost = 0.0;
        if (!coffee.getCoffeeType().getRecipe().getBaseIngredients().isEmpty()) {
            for (BaseIngredientOnRecipe baseIngredientOnRecipe : coffee.getCoffeeType().getRecipe().getBaseIngredients()) {
                baseIngredientsInCoffeeTypeCost +=
                        baseIngredientOnRecipe.getQuantity() * baseIngredientOnRecipe.getBaseIngredient().getIngredientCost();
            }
        }
        double extraIngredientsInCoffeeTypeCost = 0.0;
        if (!coffee.getCoffeeType().getRecipe().getExtraIngredients().isEmpty()) {
            for (ExtraIngredientOnRecipe extraIngredientOnRecipe : coffee.getCoffeeType().getRecipe().getExtraIngredients()) {
                extraIngredientsInCoffeeTypeCost +=
                        extraIngredientOnRecipe.getQuantity() * extraIngredientOnRecipe.getExtraIngredient().getIngredientCost();
            }
        }

        double additionalBaseIngredientsCost = 0.0;
        if (!coffee.getAdditionalIngredientsForCustomCoffee().getBaseIngredients().isEmpty()) {
            for (BaseIngredientOnRecipe baseIngredientOnRecipe : coffee.getAdditionalIngredientsForCustomCoffee().getBaseIngredients()) {
                additionalBaseIngredientsCost +=
                        baseIngredientOnRecipe.getQuantity() * baseIngredientOnRecipe.getBaseIngredient().getIngredientCost();
            }
        }

        double additionalExtraIngredientsCost = 0.0;
        if (!coffee.getAdditionalIngredientsForCustomCoffee().getExtraIngredients().isEmpty()) {
            for (ExtraIngredientOnRecipe extraIngredientOnRecipe : coffee.getAdditionalIngredientsForCustomCoffee().getExtraIngredients()) {
                additionalExtraIngredientsCost +=
                        extraIngredientOnRecipe.getQuantity() * extraIngredientOnRecipe.getExtraIngredient().getIngredientCost();
            }
        }

        return baseIngredientsInCoffeeTypeCost + extraIngredientsInCoffeeTypeCost + additionalBaseIngredientsCost + additionalExtraIngredientsCost;
    }

    public Double getCoffeeTypePrice(CoffeeType coffeeType) {

        Double baseIngredientsInCoffeeTypePrice = 0.0;
        if (!coffeeType.getRecipe().getBaseIngredients().isEmpty()) {
            for (BaseIngredientOnRecipe baseIngredientOnRecipe : coffeeType.getRecipe().getBaseIngredients()) {
                baseIngredientsInCoffeeTypePrice +=
                        baseIngredientOnRecipe.getQuantity() * baseIngredientOnRecipe.getBaseIngredient().getIngredientSellingPrice();
            }
        }

        double extraIngredientsInCoffeeTypeCost = 0.0;
        if (!coffeeType.getRecipe().getExtraIngredients().isEmpty()) {
            for (ExtraIngredientOnRecipe extraIngredientOnRecipe : coffeeType.getRecipe().getExtraIngredients()) {
                extraIngredientsInCoffeeTypeCost +=
                        extraIngredientOnRecipe.getQuantity() * extraIngredientOnRecipe.getExtraIngredient().getIngredientSellingPrice();
            }
        }

        return baseIngredientsInCoffeeTypePrice + extraIngredientsInCoffeeTypeCost;
    }
}