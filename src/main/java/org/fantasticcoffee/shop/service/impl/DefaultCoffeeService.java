package org.fantasticcoffee.shop.service.impl;

import org.apache.log4j.Logger;
import org.fantasticcoffee.shop.model.Coffee;
import org.fantasticcoffee.shop.model.JoinClasses.StandardRecipeIngredient;
import org.fantasticcoffee.shop.model.JoinClasses.CoffeeIngredient;
import org.fantasticcoffee.shop.model.Ingredient;
import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.StandardRecipe;
import org.fantasticcoffee.shop.repository.database.CoffeeRepository;
import org.fantasticcoffee.shop.repository.database.IngredientRepository;
import org.fantasticcoffee.shop.repository.database.StandardRecipeRepository;
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
    private StandardRecipeRepository standardRecipeRepository;
    @Autowired
    private CoffeeRepository coffeeRepository;

    private static final Logger log = Logger.getLogger(DefaultCoffeeService.class.getName());

    public void save(Order order) {
        order.getCoffeeList().forEach(coffee -> {
            coffee.setOrder(order);
            this.coffeeRepository.save(coffee);
        });
    }

    public Double getCoffeePrice(Coffee coffee) {

        double standardRecipePrice = 0.0;
        standardRecipePrice += coffee.getStandardRecipe().getIngredientList()
                .stream()
                .mapToDouble(ingredient ->
                        ingredient.getNumberOfShots() * ingredient.getIngredient().getIngredientSellingPrice())
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
        standardRecipeCost += coffee.getStandardRecipe().getIngredientList()
                .stream()
                .mapToDouble(ingredient ->
                        ingredient.getNumberOfShots() * ingredient.getIngredient().getIngredientCost())
                .sum();

        double additionalIngredientsCost = 0.0;
        additionalIngredientsCost += coffee.getChosenIngredients()
                .stream()
                .mapToDouble(ingredient ->
                        ingredient.getNumberOfShots() * ingredient.getIngredient().getIngredientCost())
                .sum();

        return standardRecipeCost + additionalIngredientsCost;
    }

    public Map<Ingredient, Integer> getAllCoffeeIngredients(Coffee coffee) {

        Map<Ingredient, Integer> ingredientList = coffee.getStandardRecipe().getIngredientList()
                .stream()
                .collect(Collectors.toMap(StandardRecipeIngredient::getIngredient,
                        ingredient -> ingredient.getNumberOfShots()));

        for (CoffeeIngredient coffeeIngredient : coffee.getChosenIngredients()) {
            if (ingredientList.containsKey(coffeeIngredient.getIngredient())) {
                int existingNumberOfShots = ingredientList.get(coffeeIngredient.getIngredient());
                int moreShots = coffeeIngredient.getNumberOfShots();
                ingredientList.put(
                        coffeeIngredient.getIngredient(),
                        existingNumberOfShots + moreShots);
            } else {
                ingredientList.putIfAbsent(
                        coffeeIngredient.getIngredient(),
                        coffeeIngredient.getNumberOfShots());
            }
        }

        return ingredientList;
    }

    public List<StandardRecipe> getStandardRecipeList() {

        Iterable<StandardRecipe> recipes = this.standardRecipeRepository.findAll();
        List<StandardRecipe> recipeList = new ArrayList<>();
        recipes.forEach(recipeList::add);

        return recipeList;
    }

    public StandardRecipe getCoffee(String standardCoffeeName) {
        return this.standardRecipeRepository.findByName(standardCoffeeName);
    }

    public StandardRecipe createStandardCoffee(String standardRecipeName) {

        if (standardRecipeName == null) {
            return null;
        }

        return switch (standardRecipeName.toUpperCase()) {
            case "ESPRESSO" -> this.standardRecipeRepository.findByName("ESPRESSO");
            case "MACHIATTO" -> this.standardRecipeRepository.findByName("MACHIATTO");
            case "CAFFEE_LATTE" -> this.standardRecipeRepository.findByName("CAFFEE_LATTE");
            case "CAPPUCCINO" -> this.standardRecipeRepository.findByName("CAPPUCCINO");
            case "CAFFEE_MIEL" -> this.standardRecipeRepository.findByName("CAFFEE_MIEL");
            case "CUSTOM" -> this.standardRecipeRepository.findByName("CUSTOM");
            default -> {
                log.error("Unexpected value: " + standardRecipeName);
                throw new IllegalStateException("Unexpected value: " + standardRecipeName);
            }
        };
    }
}