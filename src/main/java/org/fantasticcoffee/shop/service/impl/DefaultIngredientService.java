package org.fantasticcoffee.shop.service.impl;

import org.fantasticcoffee.shop.model.Order;
import org.fantasticcoffee.shop.model.JoinClasses.CoffeeIngredient;
import org.fantasticcoffee.shop.model.Ingredient;
import org.fantasticcoffee.shop.repository.database.IngredientRepository;
import org.fantasticcoffee.shop.service.IngredientService;
import org.fantasticcoffee.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service("ingredientService")
public class DefaultIngredientService implements IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    OrderService orderService;

    public List<Ingredient> getAllIngredients() {

        Iterable<Ingredient> ingredients = this.ingredientRepository.findAll();
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredients.forEach(ingredientList::add);

        return ingredientList;
    }

    @Override
    public void decrementIngredient(Map<Ingredient, Integer> ingredientsToChange) {

        ingredientsToChange.forEach(
                (ingredientInOrder, shotsNumber) -> {
                    Ingredient ingredient = this.ingredientRepository.findByName(ingredientInOrder.getName());
                    ingredient.setNumberOfShots(ingredient.getNumberOfShots() - shotsNumber);
                    this.ingredientRepository.save(ingredient);
                });
    }

    @Override
    public Double getPriceForShots(CoffeeIngredient ingredient) {
        return ingredient.getNumberOfShots() * ingredient.getIngredient().getIngredientSellingPrice();
    }

    @Override
    public Map<Ingredient, Integer> checkIngredientsForOrder(Order order) {

        Map<Ingredient, Integer> allIngredientsForOrder = this.orderService.getAllIngredientsForOrder(order);
        StringBuilder message = new StringBuilder();

        allIngredientsForOrder.forEach(
                (ingredientInOrder, shotsInOrder) -> {
                    if (shotsInOrder > this.ingredientRepository
                            .findByName(ingredientInOrder.getName())
                            .getNumberOfShots()) {
                        message.append(ingredientInOrder.getName() + " ");
                    }
                }
        );

        if (message.length() != 0) {
            throw new IllegalArgumentException("We do not have enough " + message + "in stock. We apologize for the inconvenience.");
        }

        return allIngredientsForOrder;
    }

    @Override
    public Ingredient getByName(String ingredientToFind) {

        Ingredient ingredient = this.ingredientRepository.findByName(ingredientToFind);
        if (ingredient == null) {
            throw new NoSuchElementException("We do not have the ingredient `" + ingredientToFind + "`. " +
                    "Please check our ingredients and make sure the spelling is correct");
        }
        return ingredient;
    }
}