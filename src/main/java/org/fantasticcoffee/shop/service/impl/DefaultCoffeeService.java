package org.fantasticcoffee.shop.service.impl;

import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.fantasticcoffee.shop.model.StandardCoffee;
import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;
import org.fantasticcoffee.shop.model.ingredientonrecipe.BaseIngredientOnRecipe;
import org.fantasticcoffee.shop.model.ingredientonrecipe.ExtraIngredientOnRecipe;
import org.fantasticcoffee.shop.repository.DefaultBaseIngredientRepository;
import org.fantasticcoffee.shop.repository.DefaultExtraIngredientRepository;
import org.fantasticcoffee.shop.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("coffeeService")
public class DefaultCoffeeService implements CoffeeService {

    @Autowired
    DefaultBaseIngredientRepository baseIngredientRepository;

    @Autowired
    DefaultExtraIngredientRepository extraIngredientRepository;

    public Double getCoffeePrice(CustomCoffee coffee) {

        double additionalBaseIngredientsPrice = 0.0;
        if (!coffee.getCustomerMadeRecipe().getBaseIngredients().isEmpty()) {
            for (BaseIngredientOnRecipe baseIngredientOnRecipe : coffee.getCustomerMadeRecipe().getBaseIngredients()) {
                additionalBaseIngredientsPrice +=
                        baseIngredientOnRecipe.getQuantity() * baseIngredientOnRecipe.getBaseIngredient().getIngredientSellingPrice();
            }
        }

        double additionalExtraIngredientsPrice = 0.0;
        if (!coffee.getCustomerMadeRecipe().getExtraIngredients().isEmpty()) {
            for (ExtraIngredientOnRecipe extraIngredientOnRecipe : coffee.getCustomerMadeRecipe().getExtraIngredients()) {
                additionalExtraIngredientsPrice +=
                        extraIngredientOnRecipe.getQuantity() * extraIngredientOnRecipe.getExtraIngredient().getIngredientSellingPrice();
            }
        }

        return additionalBaseIngredientsPrice + additionalExtraIngredientsPrice;
    }

    public Double getCoffeePrice(CustomizableStandardCoffee coffee) {

        double baseIngredientsInStandardCoffeePrice = 0.0;
        if (!coffee.getStandardCoffee().getRecipe().getBaseIngredients().isEmpty()) {
            for (BaseIngredientOnRecipe baseIngredientOnRecipe : coffee.getStandardCoffee().getRecipe().getBaseIngredients()) {
                baseIngredientsInStandardCoffeePrice +=
                        baseIngredientOnRecipe.getQuantity() * baseIngredientOnRecipe.getBaseIngredient().getIngredientSellingPrice();
            }
        }

        double extraIngredientsInStandardCoffeePrice = 0.0;
        if (!coffee.getStandardCoffee().getRecipe().getExtraIngredients().isEmpty()) {
            for (ExtraIngredientOnRecipe extraIngredientOnRecipe : coffee.getStandardCoffee().getRecipe().getExtraIngredients()) {
                extraIngredientsInStandardCoffeePrice +=
                        extraIngredientOnRecipe.getQuantity() * extraIngredientOnRecipe.getExtraIngredient().getIngredientSellingPrice();
            }
        }

        double additionalExtraIngredientsPrice = 0.0;
        if (!coffee.getExtraIngredients().isEmpty()) {
            for (ExtraIngredientOnRecipe extraIngredientOnRecipe : coffee.getExtraIngredients()) {
                additionalExtraIngredientsPrice +=
                        extraIngredientOnRecipe.getQuantity() * extraIngredientOnRecipe.getExtraIngredient().getIngredientSellingPrice();
            }
        }

        return baseIngredientsInStandardCoffeePrice + extraIngredientsInStandardCoffeePrice + additionalExtraIngredientsPrice;
    }

    public Double getCoffeeCost(CustomCoffee coffee) {

        double additionalBaseIngredientsCost = 0.0;
        if (!coffee.getCustomerMadeRecipe().getBaseIngredients().isEmpty()) {
            for (BaseIngredientOnRecipe baseIngredientOnRecipe : coffee.getCustomerMadeRecipe().getBaseIngredients()) {
                additionalBaseIngredientsCost +=
                        baseIngredientOnRecipe.getQuantity() * baseIngredientOnRecipe.getBaseIngredient().getIngredientCost();
            }
        }

        double additionalExtraIngredientsCost = 0.0;
        if (!coffee.getCustomerMadeRecipe().getExtraIngredients().isEmpty()) {
            for (ExtraIngredientOnRecipe extraIngredientOnRecipe : coffee.getCustomerMadeRecipe().getExtraIngredients()) {
                additionalExtraIngredientsCost +=
                        extraIngredientOnRecipe.getQuantity() * extraIngredientOnRecipe.getExtraIngredient().getIngredientCost();
            }
        }

        return additionalBaseIngredientsCost + additionalExtraIngredientsCost;
    }

    public Double getCoffeeCost(CustomizableStandardCoffee coffee) {

        double baseIngredientsInStandardCoffeeCost = 0.0;
        if (!coffee.getStandardCoffee().getRecipe().getBaseIngredients().isEmpty()) {
            for (BaseIngredientOnRecipe baseIngredientOnRecipe : coffee.getStandardCoffee().getRecipe().getBaseIngredients()) {
                baseIngredientsInStandardCoffeeCost +=
                        baseIngredientOnRecipe.getQuantity() * baseIngredientOnRecipe.getBaseIngredient().getIngredientCost();
            }
        }

        double extraIngredientsInStandardCoffeeCost = 0.0;
        if (!coffee.getStandardCoffee().getRecipe().getExtraIngredients().isEmpty()) {
            for (ExtraIngredientOnRecipe extraIngredientOnRecipe : coffee.getStandardCoffee().getRecipe().getExtraIngredients()) {
                extraIngredientsInStandardCoffeeCost +=
                        extraIngredientOnRecipe.getQuantity() * extraIngredientOnRecipe.getExtraIngredient().getIngredientCost();
            }
        }

        double additionalExtraIngredientsCost = 0.0;
        if (!coffee.getExtraIngredients().isEmpty()) {
            for (ExtraIngredientOnRecipe extraIngredientOnRecipe : coffee.getStandardCoffee().getRecipe().getExtraIngredients()) {
                additionalExtraIngredientsCost +=
                        extraIngredientOnRecipe.getQuantity() * extraIngredientOnRecipe.getExtraIngredient().getIngredientCost();
            }
        }

        return baseIngredientsInStandardCoffeeCost + extraIngredientsInStandardCoffeeCost + additionalExtraIngredientsCost;
    }

    public Double getStandardCoffeePrice(StandardCoffee standardCoffee) {

        double baseIngredientsInCoffeeTypePrice = 0.0;
        if (!standardCoffee.getRecipe().getBaseIngredients().isEmpty()) {
            for (BaseIngredientOnRecipe baseIngredientOnRecipe : standardCoffee.getRecipe().getBaseIngredients()) {
                baseIngredientsInCoffeeTypePrice +=
                        baseIngredientOnRecipe.getQuantity() * baseIngredientOnRecipe.getBaseIngredient().getIngredientSellingPrice();
            }
        }

        double extraIngredientsInCoffeeTypeCost = 0.0;
        if (!standardCoffee.getRecipe().getExtraIngredients().isEmpty()) {
            for (ExtraIngredientOnRecipe extraIngredientOnRecipe : standardCoffee.getRecipe().getExtraIngredients()) {
                extraIngredientsInCoffeeTypeCost +=
                        extraIngredientOnRecipe.getQuantity() * extraIngredientOnRecipe.getExtraIngredient().getIngredientSellingPrice();
            }
        }

        return baseIngredientsInCoffeeTypePrice + extraIngredientsInCoffeeTypeCost;
    }
}