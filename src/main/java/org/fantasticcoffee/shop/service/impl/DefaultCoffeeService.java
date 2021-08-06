package org.fantasticcoffee.shop.service.impl;

import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.model.StandardRecipeIngredientInStock;
import org.fantasticcoffee.shop.model.ingredient.ChosenIngredientIngredientInStock;
import org.fantasticcoffee.shop.model.ingredient.IngredientInStock;
import org.fantasticcoffee.shop.model.recipe.StandardRecipeInStock;
import org.fantasticcoffee.shop.repository.database.IngredientInStockRepository;
import org.fantasticcoffee.shop.repository.database.StandardRecipeInStockRepository;
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
    @Autowired
    StandardRecipeInStockRepository standardRecipeInStockRepository;

    public Double getCoffeePrice(Coffee coffee) {

        double standardRecipePrice = 0.0;
        standardRecipePrice += coffee.getStandardRecipe().getIngredientOnRecipeList()
                .stream()
                .mapToDouble(ingredient ->
                        ingredient.getNumberOfShots() * ingredient.getIngredientInStock().getIngredientSellingPrice())
                .sum();

        double additionalIngredientsPrice = 0.0;
        additionalIngredientsPrice += coffee.getChosenIngredients()
                .stream()
                .mapToDouble(ingredient ->
                        ingredient.getNumberOfShots() * ingredient.getIngredient().getIngredientSellingPrice())
                .sum();

        return standardRecipePrice + additionalIngredientsPrice;
    }

    public Double getCoffeeCost(Coffee coffee) {

        double standardRecipeCost = 0.0;
        standardRecipeCost += coffee.getStandardRecipe().getIngredientOnRecipeList()
                .stream()
                .mapToDouble(ingredient ->
                        ingredient.getNumberOfShots() * ingredient.getIngredientInStock().getIngredientCost())
                .sum();

        double additionalIngredientsCost = 0.0;
        additionalIngredientsCost += coffee.getChosenIngredients()
                .stream()
                .mapToDouble(ingredient ->
                        ingredient.getNumberOfShots() * ingredient.getIngredient().getIngredientCost())
                .sum();

        return standardRecipeCost + additionalIngredientsCost;
    }

    public Map<IngredientInStock, Integer> getAllCoffeeIngredients(Coffee coffee) {

        Map<IngredientInStock, Integer> ingredientList = coffee.getStandardRecipe().getIngredientOnRecipeList()
                .stream()
                .collect(Collectors.toMap(StandardRecipeIngredientInStock::getIngredientInStock,
                        ingredient -> ingredient.getNumberOfShots()));

        for (ChosenIngredientIngredientInStock chosenIngredientIngredientInStock : coffee.getChosenIngredients()) {
            if (ingredientList.containsKey(chosenIngredientIngredientInStock.getIngredient())) {
                int existingNumberOfShots = ingredientList.get(chosenIngredientIngredientInStock.getIngredient());
                int moreShots = chosenIngredientIngredientInStock.getNumberOfShots();
                ingredientList.put(
                        chosenIngredientIngredientInStock.getIngredient(),
                        existingNumberOfShots + moreShots);
            } else {
                ingredientList.putIfAbsent(
                        chosenIngredientIngredientInStock.getIngredient(),
                        chosenIngredientIngredientInStock.getNumberOfShots());
            }
        }

        return ingredientList;
    }

    public List<StandardRecipeInStock> getStandardRecipeList() {

        Iterable<StandardRecipeInStock> recipes = this.standardRecipeInStockRepository.findAll();
        List<StandardRecipeInStock> recipeList = new ArrayList<>();
        recipes.forEach(recipeList::add);

        return recipeList;
    }

    public StandardRecipeInStock getCoffee(String standardCoffeeName) {
        return this.standardRecipeInStockRepository.findByName(standardCoffeeName);
    }

    public StandardRecipeInStock createStandardCoffee(String standardRecipeName) {

        if (standardRecipeName == null) {
            return null;
        }

        return switch (standardRecipeName.toUpperCase()) {
            case "ESPRESSO" -> this.standardRecipeInStockRepository.findByName("ESPRESSO");
            case "MACHIATTO" -> this.standardRecipeInStockRepository.findByName("MACHIATTO");
            case "CAFFEE_LATTE" -> this.standardRecipeInStockRepository.findByName("CAFFEE_LATTE");
            case "CAPPUCCINO" -> this.standardRecipeInStockRepository.findByName("CAPPUCCINO");
            case "CAFFEE_MIEL" -> this.standardRecipeInStockRepository.findByName("CAFFEE_MIEL");
            case "CUSTOM" -> this.standardRecipeInStockRepository.findByName("CUSTOM");
            default -> throw new IllegalStateException("Unexpected value: " + standardRecipeName);
        };
    }
}