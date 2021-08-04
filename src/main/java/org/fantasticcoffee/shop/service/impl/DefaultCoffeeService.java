package org.fantasticcoffee.shop.service.impl;

import org.fantasticcoffee.shop.model.coffee.CustomCoffee;
import org.fantasticcoffee.shop.model.coffee.StandardRecipe;
import org.fantasticcoffee.shop.model.coffee.CoffeeWithStandardRecipeBase;
import org.fantasticcoffee.shop.model.ingredient.Ingredient;
import org.fantasticcoffee.shop.model.ingredient.IngredientOnRecipe;
import org.fantasticcoffee.shop.repository.database.IngredientInStockRepository;
import org.fantasticcoffee.shop.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("coffeeService")
public class DefaultCoffeeService implements CoffeeService {

    @Autowired
    IngredientInStockRepository ingredientRepository;

    public Double getCoffeePrice(CustomCoffee coffee) {

        double ingredientsPrice = 0.0;
        if (!coffee.getCustomerMadeRecipe().getIngredientOnRecipe().isEmpty()) {
            for (IngredientOnRecipe ingredientOnRecipe : coffee.getCustomerMadeRecipe().getIngredientOnRecipe()) {
                ingredientsPrice +=
                        ingredientOnRecipe.getNumberOfShots() * ingredientOnRecipe.getIngredient().getIngredientSellingPrice();
            }
        }

        return ingredientsPrice;
    }

    public Double getCoffeePrice(CoffeeWithStandardRecipeBase coffee) {

        double ingredientsInStandardCoffeePrice = getStandardCoffeePrice(coffee.getStandardRecipe());

        double additionalIngredientsPrice = 0.0;
        if (!coffee.getExtraIngredients().isEmpty()) {
            for (IngredientOnRecipe ingredientOnRecipe : coffee.getExtraIngredients()) {
                additionalIngredientsPrice +=
                        ingredientOnRecipe.getNumberOfShots() * ingredientOnRecipe.getIngredient().getIngredientSellingPrice();
            }
        }

        return ingredientsInStandardCoffeePrice + additionalIngredientsPrice;
    }

    public Double getCoffeeCost(CustomCoffee coffee) {

        double additionalIngredientsCost = 0.0;
        if (!coffee.getCustomerMadeRecipe().getIngredientOnRecipe().isEmpty()) {
            for (IngredientOnRecipe ingredientOnRecipe : coffee.getCustomerMadeRecipe().getIngredientOnRecipe()) {
                additionalIngredientsCost +=
                        ingredientOnRecipe.getNumberOfShots() * ingredientOnRecipe.getIngredient().getIngredientCost();
            }
        }

        return additionalIngredientsCost;
    }

    public Double getCoffeeCost(CoffeeWithStandardRecipeBase coffee) {

        double ingredientsInStandardCoffeeCost = 0.0;
        if (!coffee.getStandardRecipe().getRecipe().getIngredientOnRecipe().isEmpty()) {
            for (IngredientOnRecipe ingredientOnRecipe : coffee.getStandardRecipe().getRecipe().getIngredientOnRecipe()) {
                ingredientsInStandardCoffeeCost +=
                        ingredientOnRecipe.getNumberOfShots() * ingredientOnRecipe.getIngredient().getIngredientCost();
            }
        }

        double extraIngredientsCost = 0.0;
        if (!coffee.getExtraIngredients().isEmpty()) {
            for (IngredientOnRecipe extraIngredientOnRecipe : coffee.getStandardRecipe().getRecipe().getIngredientOnRecipe()) {
                extraIngredientsCost +=
                        extraIngredientOnRecipe.getNumberOfShots() * extraIngredientOnRecipe.getIngredient().getIngredientCost();
            }
        }

        return ingredientsInStandardCoffeeCost + extraIngredientsCost;
    }

    public Double getStandardCoffeePrice(StandardRecipe standardRecipe) {

        double ingredientsInCoffeeTypePrice = 0.0;
        if (!standardRecipe.getRecipe().getIngredientOnRecipe().isEmpty()) {
            for (IngredientOnRecipe ingredientOnRecipe : standardRecipe.getRecipe().getIngredientOnRecipe()) {
                ingredientsInCoffeeTypePrice +=
                        ingredientOnRecipe.getNumberOfShots() * ingredientOnRecipe.getIngredient().getIngredientSellingPrice();
            }
        }

        return ingredientsInCoffeeTypePrice;
    }

    public List<IngredientOnRecipe> getAllIngredientsForCustomCoffee(CustomCoffee coffee) {

        return new ArrayList<>(coffee.getCustomerMadeRecipe().getIngredientOnRecipe());
    }

    public List<IngredientOnRecipe> getAllIngredientsForCoffeeWithStandardRecipeBase(CoffeeWithStandardRecipeBase coffee) {

        Map<Ingredient, IngredientOnRecipe> allIngredients = coffee.getStandardRecipe().getRecipe().getIngredientOnRecipe()
                .stream()
                .collect(Collectors.toMap(IngredientOnRecipe::getIngredient, i -> i));

        for (IngredientOnRecipe ingredientOnRecipe : coffee.getExtraIngredients()) {
            if (allIngredients.containsKey(ingredientOnRecipe.getIngredient())) {
                IngredientOnRecipe ingredient = allIngredients.get(ingredientOnRecipe.getIngredient());
                ingredient.setNumberOfShots(ingredient.getNumberOfShots() + ingredientOnRecipe.getNumberOfShots());
            } else {
                allIngredients.putIfAbsent(ingredientOnRecipe.getIngredient(), ingredientOnRecipe);
            }
        }

        return new ArrayList<>(allIngredients.values());
    }
}