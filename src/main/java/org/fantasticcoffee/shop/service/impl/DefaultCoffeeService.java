package org.fantasticcoffee.shop.service.impl;

import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.fantasticcoffee.shop.model.coffee.StandardCoffee;
import org.fantasticcoffee.shop.model.coffee.CustomizableStandardCoffee;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;
import org.fantasticcoffee.shop.repository.DefaultIngredientRepository;
import org.fantasticcoffee.shop.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("coffeeService")
public class DefaultCoffeeService implements CoffeeService {

    @Autowired
    DefaultIngredientRepository ingredientRepository;

    public Double getCoffeePrice(CustomCoffee coffee) {

        double ingredientsPrice = 0.0;
        if (!coffee.getCustomerMadeRecipe().getIngredients().isEmpty()) {
            for (IngredientOnRecipe ingredientOnRecipe : coffee.getCustomerMadeRecipe().getIngredients()) {
                ingredientsPrice +=
                        ingredientOnRecipe.getQuantity() * ingredientOnRecipe.getIngredient().getIngredientSellingPrice();
            }
        }

        return ingredientsPrice;
    }

    public Double getCoffeePrice(CustomizableStandardCoffee coffee) {

        double ingredientsInStandardCoffeePrice = getStandardCoffeePrice(coffee.getStandardCoffee());

        double additionalIngredientsPrice = 0.0;
        if (!coffee.getExtraIngredients().isEmpty()) {
            for (IngredientOnRecipe ingredientOnRecipe : coffee.getExtraIngredients()) {
                additionalIngredientsPrice +=
                        ingredientOnRecipe.getQuantity() * ingredientOnRecipe.getIngredient().getIngredientSellingPrice();
            }
        }

        return ingredientsInStandardCoffeePrice + additionalIngredientsPrice;
    }

    public Double getCoffeeCost(CustomCoffee coffee) {

        double additionalIngredientsCost = 0.0;
        if (!coffee.getCustomerMadeRecipe().getIngredients().isEmpty()) {
            for (IngredientOnRecipe ingredientOnRecipe : coffee.getCustomerMadeRecipe().getIngredients()) {
                additionalIngredientsCost +=
                        ingredientOnRecipe.getQuantity() * ingredientOnRecipe.getIngredient().getIngredientCost();
            }
        }

        return additionalIngredientsCost;
    }

    public Double getCoffeeCost(CustomizableStandardCoffee coffee) {

        double ingredientsInStandardCoffeeCost = 0.0;
        if (!coffee.getStandardCoffee().getRecipe().getIngredients().isEmpty()) {
            for (IngredientOnRecipe ingredientOnRecipe : coffee.getStandardCoffee().getRecipe().getIngredients()) {
                ingredientsInStandardCoffeeCost +=
                        ingredientOnRecipe.getQuantity() * ingredientOnRecipe.getIngredient().getIngredientCost();
            }
        }

        double extraIngredientsCost = 0.0;
        if (!coffee.getExtraIngredients().isEmpty()) {
            for (IngredientOnRecipe extraIngredientOnRecipe : coffee.getStandardCoffee().getRecipe().getIngredients()) {
                extraIngredientsCost +=
                        extraIngredientOnRecipe.getQuantity() * extraIngredientOnRecipe.getIngredient().getIngredientCost();
            }
        }

        return ingredientsInStandardCoffeeCost + extraIngredientsCost;
    }

    public Double getStandardCoffeePrice(StandardCoffee standardCoffee) {

        double ingredientsInCoffeeTypePrice = 0.0;
        if (!standardCoffee.getRecipe().getIngredients().isEmpty()) {
            for (IngredientOnRecipe ingredientOnRecipe : standardCoffee.getRecipe().getIngredients()) {
                ingredientsInCoffeeTypePrice +=
                        ingredientOnRecipe.getQuantity() * ingredientOnRecipe.getIngredient().getIngredientSellingPrice();
            }
        }

        return ingredientsInCoffeeTypePrice;
    }
}